package com.example.oisan.service;

import com.example.oisan.controller.PostCommand;
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Moodtag;
import com.example.oisan.domain.Post;
import com.example.oisan.repository.CustomerRepository;
import com.example.oisan.domain.PostLike;
import com.example.oisan.repository.PostLikeRepository;
import com.example.oisan.domain.TagPost;
import com.example.oisan.repository.MoodtagRepository;
import com.example.oisan.repository.PostRepository;
import com.example.oisan.repository.TagPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
	
	@Autowired
	private S3FileService s3FileService;
	public void setS3FileService(S3FileService s3FileService) {
        this.s3FileService = s3FileService;
    }
    
    @Autowired
    private TagPostRepository tagPostRepository;
    public void setTagPostRepository(TagPostRepository tagPostRepository) {
        this.tagPostRepository = tagPostRepository;
    }
    
    @Autowired
    private MoodtagRepository moodtagRepository;
    public void setMoodtagRepository(MoodtagRepository moodtagRepository) {
        this.moodtagRepository = moodtagRepository;
    }

    private PostRepository postRepository;
    private PostLikeRepository postLikeRepository;

    private CustomerRepository customerRepository;

    public PostService() {}

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    @Autowired
    public void setPostLikeRepository(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Post createPost(PostCommand postCom) {
        Customer customer = customerRepository.findCustomerByCustomerId(postCom.getCustomerId());
        String image_url = null;
		try {
			image_url = s3FileService.upload(postCom.getImage(), "post/");
			image_url = "post/"+image_url;
		} catch (IOException e) {
			e.printStackTrace();
		}
        Post post = new Post(customer, postCom.getCategId(), new Date(), postCom.getTitle(),
                postCom.getDesc(), image_url, postCom.getWidth(), postCom.getHeight(), postCom.getDepth(), 1, postCom.getPrice());
        return postRepository.save(post);
    }

    public Post updatePost(PostCommand postCom) {
        Customer customer = customerRepository.findCustomerByCustomerId(postCom.getCustomerId());
        String image_url = null;
        if (postCom.getImage() == null) {
        	image_url = postRepository.findByPostId(postCom.getPostId()).getImageUrl();
        }else {
			try {
				image_url = s3FileService.upload(postCom.getImage(), "post/");
				s3FileService.deleteFile(image_url, "post/");
				image_url = "post/"+image_url;
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        Post post = new Post(postCom.getPostId(), customer, postCom.getCategId(), new Date(), postCom.getTitle(),
                postCom.getDesc(), image_url, postCom.getWidth(), postCom.getHeight(), postCom.getDepth(), 1, postCom.getPrice());
        return postRepository.save(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public List<Post> findPostByCustomerId(int customerId) {
        return postRepository.findByCustomerCustomerId(customerId);
    }

    public Optional<Post> findPost(int postId) {
        return postRepository.findById(postId);
    }

    public void deletePost(int postId) {
    	Post post = postRepository.findByPostId(postId);
    	String image_url = post.getImageUrl();
    	s3FileService.deleteFile(image_url, "post/");
        postRepository.deleteById(postId);
    }

    public List<Post> findPostByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
    
    public boolean checkIfLikePost(int postId, int customerId) {
    	if (postLikeRepository.findPostLikeByPostIdAndCustomerId(postId, customerId) == null)
    		return false;
    	return true;
    }
    
    public Post likePost(int postId, int customerId) {
    	Post post = postRepository.findByPostId(postId);
    	if (this.checkIfLikePost(postId, customerId) == true) {
    		PostLike postLike = new PostLike();
    		postLike = postLikeRepository.findPostLikeByPostIdAndCustomerId(postId, customerId);
    		postLikeRepository.delete(postLike);
    	}
    	else {
    		PostLike newPostLike = new PostLike();
    		newPostLike.setCustomerId(customerId);
    		newPostLike.setPostId(postId);
    		postLikeRepository.save(newPostLike);
    	}
    	return post;
    }
    
    public void saveTagPost(int moodtagId, int postId) {
    	Moodtag moodtag = moodtagRepository.findByMoodtagId(moodtagId);
    	Post post = postRepository.findByPostId(postId);
    	tagPostRepository.save(new TagPost(moodtag, post));
    }
    
    public Moodtag saveMoodtag(String name) {
    	Moodtag moodtag = new Moodtag(name);
    	return moodtagRepository.save(moodtag);
    }
    
    public List<TagPost> findTagPostsByPostId(int postId) {
    	return tagPostRepository.findByPostId(postId);
    }
    
    public List<TagPost> findTagPostsByMoodtagId(int moodtagId) {
    	return tagPostRepository.findByMoodtagMoodtagId(moodtagId);
    }
    
    public Moodtag findByName(String name) {
    	return moodtagRepository.findByName(name);
    }
    
    public void deleteTagPost(int moodtagId, int postId) {
//    	Moodtag moodtag = moodtagRepository.findByMoodtagId(moodtagId);
//    	Post post = postRepository.findByPostId(postId);
//    	tagPostRepository.delete(new TagPost(moodtag, post));
    	int tagPostId = tagPostRepository.findByMoodtagIdAndByPostId(moodtagId, postId);
    	tagPostRepository.deleteById(tagPostId);
    }
    
    public int updateStatusByPostId(int status, int postId) {
    	return postRepository.updateStatusByPostId(status, postId);
    }

}
