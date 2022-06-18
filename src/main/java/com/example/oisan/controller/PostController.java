package com.example.oisan.controller;

import com.example.oisan.domain.Moodtag;
import com.example.oisan.domain.Post;
import com.example.oisan.domain.TagPost;
import com.example.oisan.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

//	public static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/new")
    public Post createPost(PostCommand postCom) {
        Post post = postService.createPost(postCom);
        
        for (String s : postCom.getTagList()) {
        	Moodtag moodtag = postService.findByName(s.trim());
        	if (moodtag == null) {
        		moodtag = postService.saveMoodtag(s.trim());
        	}
        	postService.saveTagPost(moodtag.getMoodtagId(), post.getPostId());
        }
        return post;
    }

    @GetMapping("/post/list")
    public List<Post> getPostList() {
        List<Post> posts = postService.findPosts();
        return posts;
    }

    @GetMapping("/post/list/{customerId}")
    public List<Post> getPostListByCustomerId(@PathVariable("customerId") int customerId) {
        List<Post> posts = postService.findPostByCustomerId(customerId);
        return posts;
    }

    @GetMapping("/post")
    public Post getPost(@RequestParam("postId") int postId) {
        Post post = postService.findPost(postId).get();
        return post;
    }

    @PutMapping("/post/edit")
    public String updatePost(@RequestBody PostCommand postCom) {
        postService.updatePost(postCom);
        return "redirect:/";
    }

    @DeleteMapping("/post/delete")
    public String deletePost(@RequestParam("postId") int postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
    
    @GetMapping("/post/like/{postId}/{customerId}")
    public Post likePost(@PathVariable String postId, @PathVariable String customerId) {
    	int pId = Integer.parseInt(postId);
    	int cId = Integer.parseInt(customerId);
    	return postService.likePost(pId, cId);
    }
    
    @SuppressWarnings("null")
	@GetMapping("/post/tag/list") // post에 포함된 tag list
    public List<Moodtag> findTagPostsByPostId(@RequestParam("postId") int postId) {
        List<Moodtag> tagList = null;
        List<TagPost> tagPostList = postService.findTagPostsByPostId(postId);

        if (tagPostList != null) {
        	tagList = new ArrayList<>();
            for (TagPost t : tagPostList) {
            	tagList.add(t.getMoodtag());
            }
        }
        return tagList;
    }

    @SuppressWarnings("null")
    @GetMapping("/tag/list") // tag에 포함된 post list
    public List<Post> findTagPostsByMoodtagId(@RequestParam("moodtagId") int moodtagId) {
        List<Post> postList = null;
        List<TagPost> tagPostList = postService.findTagPostsByMoodtagId(moodtagId);

        if (tagPostList != null) {
        	postList = new ArrayList<>();
            for (TagPost p : tagPostList) {
            	postList.add(p.getPost());
            }
        }
    	return postList;
    }
    
    @GetMapping("/post/tag/delete")
    public Post deleteTagPost(@RequestParam("postId") int postId, @RequestParam("tags") String tags) {
        String[] tagList = tags.split(",");

        for (String s : tagList) {
        	Moodtag moodtag = postService.findByName(s.trim());
        	postService.deleteTagPost(moodtag.getMoodtagId(), postId);
        }
        return postService.findPost(postId).get();
    }
    
    @PutMapping("/post/complete") // 거래 완료 상태로 업데이트
    public Post updatePostComplete(@RequestParam("postId") int postId) {
    	postService.updateStatusByPostId(0, postId);
    	return postService.findPost(postId).get();
    }

    @PutMapping("/post/open") // 거래중 상태로 업데이트
    public Post updatePostOpen(@RequestParam("postId") int postId) {
        postService.updateStatusByPostId(1, postId);
        return postService.findPost(postId).get();
    }
}
