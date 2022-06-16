package com.example.oisan.controller;

import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Post;
import com.example.oisan.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/new")
    public Post createPost(@RequestBody PostCommand postCom, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        Post post = postService.save(postCom, customer);
        return post;
    }

    @GetMapping("/post/list")
    public List<Post> getPostList() {
        List<Post> posts = postService.findPosts();
        return posts;
    }

    @GetMapping("/post")
    public Post getPost(@RequestParam("postId") int postId) {
        Post post = postService.findPost(postId).get();
        return post;
    }

    @GetMapping("/post/edit")
    public Post editPost(@RequestParam("postId") int postId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        Post post = postService.findPost(postId).get();

        if (!(customer.getCustomerId() == post.getCustomer().getCustomerId())) {
            return null;
        }
        return post;
    }

    @PutMapping("/post/edit")
    public String updatePost(@RequestBody PostCommand postCom, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        postService.save(postCom, customer);
        return "redirect:/";
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam("postId") int postId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        Post post = postService.findPost(postId).get();

        if (!(customer.getCustomerId() == post.getCustomer().getCustomerId())) {
            return null;
        }

        postService.deletePost(postId);
        return "redirect:/";
    }
}
