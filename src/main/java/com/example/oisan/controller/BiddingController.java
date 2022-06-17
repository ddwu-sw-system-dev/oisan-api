package com.example.oisan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Bidding;
import com.example.oisan.domain.Customer;
import com.example.oisan.service.AuctionService;
import com.example.oisan.service.BiddingService;
import com.example.oisan.service.OiPayUsageService;

@RestController
@RequestMapping("/bidding")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class BiddingController {

	@Autowired
	private AuctionService auctionService;
	public void setAuctionService(AuctionService auctionService) {
		this.auctionService = auctionService;
	}
	
	@Autowired
	private BiddingService biddingService;
	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}
	
	@GetMapping(value="/{auctionId}")
	public List<Bidding> getBiddingList(@PathVariable("auctionId") int auctionId, HttpServletResponse response) throws IOException {
		List<Bidding> biddingList = biddingService.getBiddings(auctionId);
		return biddingList;  // convert list of orders to JSON text in response body
	}
	
	@GetMapping(value="/customer")
	public List<Bidding> getBiddingListByCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		List<Bidding> biddingList = biddingService.getBiddingsByCustomerId(customer.getCustomerId());
		return biddingList;
	}

	@PostMapping(value="/{auctionId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Bidding createBidding(@PathVariable("auctionId") int auctionId, @RequestParam("price") int price, @RequestParam("customerId") int customerId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 입찰한 금액만큼 차감
		Auction auction = auctionService.findAuctionById(auctionId);
		Bidding bidding = biddingService.insertBidding(price, auctionId, customerId);
		oiPayUsageService.useOiPay(auction.getCustomer().getCustomerId(), auction.getWinningBid(), auction.getAuctionId()); 
		
		// 직전 입찰 환불
		Bidding lastBidding = biddingService.getLastBidding(auction.getAuctionId());
		oiPayUsageService.chargeOiPay(lastBidding.getCustomerId(), lastBidding.getPrice());
		
		return bidding;
	}
	
}
