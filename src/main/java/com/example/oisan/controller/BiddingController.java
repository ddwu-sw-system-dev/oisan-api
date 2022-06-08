package com.example.oisan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Bidding;
import com.example.oisan.domain.Customer;
import com.example.oisan.service.BiddingService;

@RestController
@RequestMapping("/bidding")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class BiddingController {
	private BiddingService biddingService;
	
	@Autowired
	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}
	
	@GetMapping(value="/{auctionId}")
	public List<Bidding> getBiddingList(@PathVariable("auctionId") int auctionId, HttpServletResponse response) throws IOException {
		List<Bidding> biddingList = biddingService.getBiddings(auctionId);
		return biddingList;  // convert list of orders to JSON text in response body
	}
	
	@GetMapping(value="/customer/{customerId}")
	public List<Bidding> getBiddingListByCustomerId(@PathVariable("customerId") int customerId, HttpServletResponse response) throws IOException {
		List<Bidding> biddingList = biddingService.getBiddingsByCustomerId(customerId);
		return biddingList;
	}

	@PostMapping(value="/{auctionId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Bidding createBidding(@RequestBody BiddingCommand biddingCom, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 현재 로그인한 유저 가져와서 넣어야 함
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		Bidding bidding = biddingService.insertBidding(biddingCom, customer.getCustomerId());
		
		return bidding;
	}
	
}
