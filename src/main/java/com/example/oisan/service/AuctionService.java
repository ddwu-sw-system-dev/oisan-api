package com.example.oisan.service;

import java.io.IOException;
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
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Furniture;
import com.example.oisan.repository.AuctionRepository;
import com.example.oisan.repository.BiddingRepository;
import com.example.oisan.repository.CustomerRepository;

@Service
public class AuctionService {
	
	@Autowired
	private S3FileService s3FileService;
	public void setS3FileService(S3FileService s3FileService) {
        this.s3FileService = s3FileService;
    }
	
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
	private CustomerRepository customerRepository;
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
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
	
	public List<Auction> getWinningAuctionListByCustomer(Customer customer) {
		return auctionRepository.findByCustomerAndStatus(customer, 0);
	}
	
	public Auction insertAuction(AuctionCommand auctionCom, int customerId) {
		
		Date date = new Date();	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1); // 하루 뒤에 마감
		
		Customer customer = customerRepository.findCustomerByCustomerId(customerId);
		String image_url = null;
		try {
			image_url = s3FileService.upload(auctionCom.getImage(), "auction/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Auction auction = new Auction(
				customer,
				auctionCom.getPrice(),
				auctionCom.getPrice(),
				date,
				calendar.getTime(),
				1,
				auctionCom.getTitle(),
				image_url,
				auctionCom.getCategId(),
				auctionCom.getDesc(),
				new Furniture(auctionCom.getWidth(), auctionCom.getDepth(), auctionCom.getHeight()));
		
		return auctionRepository.save(auction);
	}
	
	public void makeTaskScheduler(int auctionId) {
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				Date curTime = new Date();
				auctionRepository.closeAuction(curTime);
				
				Bidding bidding = biddingRepository.findTopByAuctionIdOrderByBiddingIdDesc(auctionId);
				if (bidding != null) {
					biddingRepository.updateWinner(bidding.getBiddingId());
				}
			}
		};

		Date date = new Date();	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		scheduler.schedule(updateTableRunner, calendar.getTime());
	}
	
	public Auction updateAuction(int auctionId, AuctionCommand auctionCom) {
		Auction curAuction = auctionRepository.findByAuctionId(auctionId);
// 		Auction auction = new Auction(
// 				auctionId,
// 				curAuction.getCustomer(),
// 				curAuction.getStartBid(),
// 				auctionCom.getPrice(),
// 				curAuction.getCreateAt(),
// 				curAuction.getClosingTime(),
// 				curAuction.getStatus(),
// 				auctionCom.getTitle(),
// 				auctionCom.getImageUrl(),
// 				auctionCom.getCategoryId(),
// 				auctionCom.getDesc(),
// 				new Furniture(auctionCom.getWidth(), auctionCom.getDepth(), auctionCom.getHeight())); 
// 		return auctionRepository.save(auction);
    return null;
	}

	public void deleteAuction(int auctionId) {
		auctionRepository.deleteById(auctionId);
	}
	
	public List<Auction> findAuctionByTitle(String word) {
		return auctionRepository.findByTitleContaining(word);
	}
	
}
