package com.example.oisan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.oisan.domain.Auction;
import com.example.oisan.domain.Customer;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {

	Auction findByAuctionId(int auctionId);
	
	List<Auction> findByCategoryId(int categoryId);
	
	List<Auction> findByFurnitureWidthBetweenAndFurnitureDepthBetweenAndFurnitureHeightBetween(int minWidth, int maxWidth, int minDepth, int maxDepth, int minHeight, int maxHeight);

	List<Auction> findByCategoryIdAndFurnitureWidthBetweenAndFurnitureDepthBetweenAndFurnitureHeightBetween(int categoryId, int minWidth, int maxWidth, int minDepth, int maxDepth, int minHeight, int maxHeight);
	
	List<Auction> findByCustomerAndStatus(Customer customer, int status);
	
	@Query(value="SELECT NAME FROM CATEGORY WHERE CATEG_ID = ?1", nativeQuery=true)
	String findCategoryName(int categId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE AUCTION SET WINNING_BID = ?1 WHERE AUCTION_ID = ?2", nativeQuery=true)
	int updateWinningBid(int price, int auctionId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE AUCTION SET STATUS = 0 WHERE CLOSING_TIME <= ?1", nativeQuery=true)
	int closeAuction(Date curTime);
	
	List<Auction> findByTitleContaining(String word);
	
}
