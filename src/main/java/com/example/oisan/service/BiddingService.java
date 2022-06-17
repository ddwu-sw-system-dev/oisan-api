package com.example.oisan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oisan.domain.Bidding;
import com.example.oisan.repository.AuctionRepository;
import com.example.oisan.repository.BiddingRepository;

@Service
public class BiddingService {

	@Autowired
	private AuctionRepository auctionRepository;
	public void setAuctionRepository(AuctionRepository auctionRepository) {
		this.auctionRepository = auctionRepository;
	}
	
	@Autowired
	private BiddingRepository biddingRepository;
	public void setBiddingRepository(BiddingRepository biddingRepository) {
		this.biddingRepository = biddingRepository;
	}
	
	public BiddingService() {}
	
	public List<Bidding> getBiddingsByAuctionId(int auctionId) {
		return biddingRepository.findByAuctionIdOrderByBiddingIdDesc(auctionId);
	}
	
	public List<Bidding> getBiddingsByCustomerId(int customerId) {
		return biddingRepository.findByCustomerId(customerId);
	}
	
	public Bidding insertBidding(int price, int auctionId, int customerId) {
		Bidding bidding = new Bidding(
				customerId,
				auctionId,
				price,
				new Date(),
				0);
		Bidding savedBidding = biddingRepository.save(bidding);
		auctionRepository.updateWinningBid(bidding.getPrice(), bidding.getAuctionId());
		return savedBidding;
	}
	
	public Bidding getLastBidding(int auctionId) {
		return biddingRepository.findTopByAuctionIdOrderByBiddingIdDesc(auctionId);
	}

}
