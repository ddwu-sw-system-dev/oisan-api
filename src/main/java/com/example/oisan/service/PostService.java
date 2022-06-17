package com.example.oisan.service;

import com.example.oisan.controller.PostCommand;
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Post;
import com.example.oisan.repository.CustomerRepository;
import com.example.oisan.domain.PostLike;
import com.example.oisan.repository.PostLikeRepository;
import com.example.oisan.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

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
        
        Post post = new Post(customer, postCom.getCategId(), new Date(), postCom.getTitle(),
                postCom.getDesc(), postCom.getImageUrl(), postCom.getWidth(), postCom.getHeight(), postCom.getDepth(), 1, postCom.getPrice());
        return postRepository.save(post);
    }

    public Post updatePost(PostCommand postCom) {
        Customer customer = customerRepository.findCustomerByCustomerId(postCom.getCustomerId());

        Post post = new Post(postCom.getPostId(), customer, postCom.getCategId(), new Date(), postCom.getTitle(),
                postCom.getDesc(), postCom.getImageUrl(), postCom.getWidth(), postCom.getHeight(), postCom.getDepth(), 1, postCom.getPrice());
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
    	Post post = postRepository.findPostByPostId(postId);
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

}
