package com.example.oisan.controller;

import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Post;
import com.example.oisan.domain.PostLike;
import com.example.oisan.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/new")
    public Post createPost(@RequestBody PostCommand postCom) {
        Post post = postService.createPost(postCom);
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

    @GetMapping("/post/edit")
    public Post editPost(@RequestParam("postId") int postId) {
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
    
    //test
//    private final S3Uploader s3Uploader;
//
//    @PostMapping("/images")
//    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
//        s3Uploader.upload(multipartFile, "static");
//        return "test";
//    }
}
