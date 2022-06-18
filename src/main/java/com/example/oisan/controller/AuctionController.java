package com.example.oisan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Auction;
import com.example.oisan.service.AuctionService;

@RestController
@RequestMapping("/auction")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class AuctionController {
	
	@Autowired
	private AuctionService auctionService;
	public void setAuctionService(AuctionService auctionService) {
		this.auctionService = auctionService;
	}
	
	@GetMapping("/list") 
	public List<Auction> getAuctionList(@RequestParam("categoryId") int categoryId, @RequestParam("minWidth") int minWidth, @RequestParam("maxWidth") int maxWidth,
			@RequestParam("minDepth") int minDepth, @RequestParam("maxDepth") int maxDepth, @RequestParam("minHeight") int minHeight, @RequestParam("maxHeight") int maxHeight, HttpServletResponse response) throws IOException {
		List<Auction> auctionList = null;
		AuctionFilterCommand filterCom = null;
		if (minWidth != -1) {
			filterCom = new AuctionFilterCommand(minWidth, maxWidth, minDepth, maxDepth, minHeight, maxHeight);
			
			System.out.println("filterCom: " + filterCom.toString());
		}
		
		if (categoryId == 0 && filterCom == null)	// 전체, 데이터 보낼 때 AuctionFilterCommand null로 만들어서 보내야 함
			auctionList = auctionService.getAuctions();
		else if (categoryId == 0)	// 사이즈만 필터링
			auctionList = auctionService.getAuctionsBySize(filterCom);
		else if (filterCom == null)	// 카테고리만 필터링
			auctionList = auctionService.getAuctionsByCategory(categoryId);
		else	// 사이즈, 카테고리 둘 다 필터링
			auctionList = auctionService.getAuctionsByCategoryAndSize(categoryId, filterCom);
		
		return auctionList;
	}
	
	// 카테고리 이름 조회
	@GetMapping("/category/{categId}")
	public String getCategoryName(@PathVariable("categId") int categId, HttpServletResponse response) throws IOException {
		return auctionService.getCategoryName(categId);
	}
	
	// 해당 유저가 올린 경매 중 종료된 경매 목록 조회
	@GetMapping("/list/closed")
	public List<Auction> getClosedAuctionListByCustomer(@RequestParam int customerId) throws IOException {
		List<Auction> auctionList = auctionService.getClosedAuctionListByCustomerId(customerId);
		return auctionList;
	}
	
	// 해당 유저가 낙찰받은 경매 목록 조회
	@GetMapping("/list/winning")
	public List<Auction> getWinningAuctionListByCustomer(@RequestParam int customerId) throws IOException {
		List<Auction> auctionList = auctionService.getWinningAuctionListByCustomerId(customerId, 1);
		return auctionList;
	}
	
	@GetMapping("/{auctionId}") // auction 하나의 상세 페이지를 띄움
	public Auction getAuction(@PathVariable("auctionId") int auctionId, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return auction;
	}
	
	@PostMapping("/create")
	public Auction createAuction(AuctionCommand auctionCom, @RequestParam int customerId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.insertAuction(auctionCom, customerId);
		auctionService.makeTaskScheduler(auction.getAuctionId());
		return auction;
	}
	
	@PutMapping("/{auctionId}")
	public Auction updateAuction(@PathVariable("auctionId") int auctionId, 
			AuctionCommand auctionCom, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		Auction updatedAuction = auctionService.updateAuction(auctionId, auctionCom);
		return updatedAuction;
	}
	
	@DeleteMapping("/{auctionId}")
	public void deleteAuction(@PathVariable("auctionId") int auctionId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		auctionService.deleteAuction(auctionId);
	}
}
