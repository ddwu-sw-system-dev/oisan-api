package com.example.oisan.controller;

import com.example.oisan.domain.Post;
import com.example.oisan.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public Post create(@RequestBody PostForm form, HttpServletRequest request) {

        // 현재 로그인한 유저와 비교해서 일치하면 진행
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        //일치하는지 확인
        if (!(customer.getCustomerId() == auction.getCustomerId())) {
            return null;
        }

        Post post = postService.save(form, customer.getCustomerId());
        return post;
    }

    @GetMapping("/post/list")
    public List<Post> list() {
        List<Post> posts = postService.findPosts();
        return posts;
    }

    @GetMapping("/post")
    public Post detail(@RequestParam("postId") int postId) {
        Post post = postService.findPost(postId).get();
        return post;
    }

    @GetMapping("/post/edit")
    public Post edit(@RequestParam("postId") int postId) {
        Post post = postService.findPost(postId).get();
        return post;
    }

    @PutMapping("/post/edit")
    public String update(@RequestBody PostForm form) {

        // 현재 로그인한 유저와 비교해서 일치하면 진행
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("Customer");

        //일치하는지 확인
        if (!(customer.getCustomerId() == auction.getCustomerId())) {
            return null;
        }

        postService.save(form, customer.getCustomerId());
        return "redirect:/";
    }

    @DeleteMapping("/post")
    public String delete(@RequestParam("postId") int postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
}
