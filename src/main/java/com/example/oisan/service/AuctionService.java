package com.example.oisan.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.example.oisan.controller.AuctionCommand;
import com.example.oisan.controller.AuctionFilterCommand;
import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Bidding;
import com.example.oisan.domain.Furniture;
import com.example.oisan.repository.AuctionRepository;
import com.example.oisan.repository.BiddingRepository;

@Service
public class AuctionService {
	
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
	
	@Autowired
	private TaskScheduler scheduler;
	
	public AuctionService() {}
	
	public Auction findAuctionById(int auctionId) {
		return auctionRepository.findByAuctionId(auctionId);
	}
	
	public List<Auction> getAuctions() {
		return auctionRepository.findAll();
	}
	
	public List<Auction> getAuctionsByCategory(int categoryId) {
		return auctionRepository.findByCategoryId(categoryId);
	}

	public List<Auction> getAuctionsBySize(AuctionFilterCommand filterCom) {
		return auctionRepository.findByFurnitureWidthBetweenAndFurnitureDepthBetweenAndFurnitureHeightBetween(
				filterCom.getMinWidth(), filterCom.getMaxWidth(), filterCom.getMinDepth(), filterCom.getMaxDepth(), filterCom.getMinHeight(), filterCom.getMaxHeight());
	}
	
	public List<Auction> getAuctionsByCategoryAndSize(int categoryId, AuctionFilterCommand filterCom) {
		return auctionRepository.findByCategoryIdAndFurnitureWidthBetweenAndFurnitureDepthBetweenAndFurnitureHeightBetween(
				categoryId, filterCom.getMinWidth(), filterCom.getMaxWidth(), filterCom.getMinDepth(), filterCom.getMaxDepth(), filterCom.getMinHeight(), filterCom.getMaxHeight());
	}
	
	public String getCategoryName(int categId) {
		return auctionRepository.findCategoryName(categId);
	}
	
	public List<Auction> getWinningAuctionListByCustomerId(int customerId) {
		return auctionRepository.findByCustomerIdAndStatus(customerId, 1);
	}
	
	public Auction insertAuction(AuctionCommand auctionCom, int customerId) {
		
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				Date curTime = new Date();
				Auction auction = auctionRepository.closeAuction(curTime);
				
				Bidding bidding = biddingRepository.findTopByAuctionIdOrderByBiddingIdDesc(auction.getAuctionId());
				if (bidding != null) {
					biddingRepository.updateWinner(bidding.getBiddingId());
				}
			}
		};
		
		Date date = new Date();	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 2); // 이틀 뒤에 마감
		
		Auction auction = new Auction(
				customerId,
				auctionCom.getPrice(),
				auctionCom.getPrice(),
				date,
				calendar.getTime(),
				1,
				auctionCom.getTitle(),
				auctionCom.getDesc(),
				auctionCom.getCategoryId(),
				auctionCom.getImageUrl(),
				new Furniture(auctionCom.getWidth(), auctionCom.getDepth(), auctionCom.getHeight()));

		scheduler.schedule(updateTableRunner, calendar.getTime());
		
		return auctionRepository.save(auction);
	}
	
	public Auction updateAuction(int auctionId, AuctionCommand auctionCom) {
		Auction curAuction = auctionRepository.findByAuctionId(auctionId);
		Auction auction = new Auction(
				auctionId,
				curAuction.getCustomerId(),
				curAuction.getStartBid(),
				curAuction.getCreateAt(),
				curAuction.getClosingTime(),
				auctionCom.getPrice(),
				auctionCom.getTitle(),
				auctionCom.getDesc(),
				auctionCom.getCategoryId(),
				auctionCom.getImageUrl(),
				new Furniture(auctionCom.getWidth(), auctionCom.getDepth(), auctionCom.getHeight())); 
		return auctionRepository.save(auction);
		
	}

	public void deleteAuction(int auctionId) {
		auctionRepository.deleteById(auctionId);
	}
	
}
