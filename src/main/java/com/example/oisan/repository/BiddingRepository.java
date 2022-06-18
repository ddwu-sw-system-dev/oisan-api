package com.example.oisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.oisan.domain.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Integer> {

	Bidding findTopByAuctionIdOrderByBiddingIdDesc(int auctionId);
	
	List<Bidding> findByAuctionIdOrderByBiddingIdDesc(int auctionId);
	
	List<Bidding> findByCustomerId(int customerId);
	
	List<Bidding> findByCustomerIdAndWinner(int customerId, int winner);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE BIDDING SET WINNER = 1 WHERE BIDDING_ID = ?1", nativeQuery=true)
	void updateWinner(int biddingId);
	
}
