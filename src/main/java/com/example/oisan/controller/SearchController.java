package com.example.oisan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Post;
import com.example.oisan.service.AuctionService;
import com.example.oisan.service.PostService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@Autowired
	private AuctionService auctionService;

	public void setAuctionService(AuctionService auctionService) {
		this.auctionService = auctionService;
	}
	
	@GetMapping("/post/{search_word}")
	public List<Post> searchPost(@PathVariable String search_word){
		return postService.findPostByTitle(search_word);
	}

	@GetMapping("/auction/{search_word}")
	public List<Auction> searchAuction(@PathVariable String search_word){
		return auctionService.findAuctionByTitle(search_word);
	}
	
}
