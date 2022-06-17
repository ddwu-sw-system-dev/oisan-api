package com.example.oisan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/auction")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class AuctionController {
	
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
	
//	@GetMapping(value="/list")
//	public List<Auction> getAuctionList(HttpServletResponse response) throws IOException {
//		List<Auction> auctionList = auctionService.getAuctions();
//		if (auctionList == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auctionList;  // convert list of orders to JSON text in response body
//	}
	
//	@GetMapping(value="/list/{categoryId}")
//	public List<Auction> getAuctionListByCategory(@PathVariable("categoryId") String categoryId, HttpServletResponse response) throws IOException {
//		List<Auction> auctionList = auctionService.getAuctionsByCategory(categoryId);
//		if (auctionList == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auctionList;
//	}
//	
//	@PostMapping(value="/list") // 사이즈 입력했는데 카테고리 선택 안 했을 때 
//	public List<Auction> getAuctionListBySize(@RequestBody FurSizeCommand furSizeCom, HttpServletResponse response) throws IOException {
//		List<Auction> auctionList = auctionService.getAuctionsBySize(furSizeCom);
//		if (auctionList == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auctionList;
//	}
//
//	@PostMapping(value="/list/{categoryId}") // 사이즈 입력했는데 카테고리 선택한 상태일 때
//	public List<Auction> getAuctionListByCategoryAndSize(@PathVariable("categoryId") String categoryId, @RequestBody FurSizeCommand furSizeCom, HttpServletResponse response) throws IOException {
//		List<Auction> auctionList = auctionService.getAuctionsByCategoryAndSize(categoryId, furSizeCom);
//		if (auctionList == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return auctionList;
//	}
	
	@GetMapping(value="/list") 
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
	@GetMapping(value="/category/{categId}")
	public String getCategoryName(@PathVariable("categId") int categId, HttpServletResponse response) throws IOException {
		return auctionService.getCategoryName(categId);
	}
	
	// 해당 유저가 낙찰받은 옥션 목록 조회
	@GetMapping(value="/list/winning/{customerId}")
	public List<Auction> getWinningAuctionListByCustomerId(@PathVariable("customerId") int customerId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 현재 로그인한 유저와 비교해서 일치하면 진행
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		//일치하는지 확인
		if (!(customer.getCustomerId() == customer.getCustomerId())) {
			return null;
		}
		
		List<Auction> auctionList = auctionService.getWinningAuctionListByCustomer(customer);
		return auctionList;
	}
	
	@GetMapping(value="/{auctionId}") // auction 하나의 상세 페이지를 띄움
	public Auction getAuction(@PathVariable("auctionId") int auctionId, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return auction;
	}
	
	@PostMapping(value="/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Auction createAuction(@RequestBody AuctionCommand auctionCom, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 현재 로그인한 유저 가져와서 넣어야 함
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		Auction auction = auctionService.insertAuction(auctionCom, customer);
		
		// 입찰한 금액만큼 차감
		oiPayUsageService.useOiPay(auction.getCategoryId(), auctionCom.getPrice(), auction.getAuctionId()); 
		
		// 직전 입찰 환불
		Bidding lastBidding = biddingService.getLastBidding(auction.getAuctionId());
		oiPayUsageService.chargeOiPay(lastBidding.getCustomerId(), lastBidding.getPrice());
		
		return auction;
	}
	
	@PutMapping(value = "/{auctionId}")
	@ResponseStatus(HttpStatus.OK)
	public Auction updateAuction(@PathVariable("auctionId") int auctionId, 
			@RequestBody AuctionCommand auctionCom, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		// 현재 로그인한 유저와 비교해서 일치하면 진행
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");
		
		//일치하는지 확인
		if (!(customer.getCustomerId() == auction.getCustomer().getCustomerId())) {
			return null;
		}
		
		Auction updatedAuction = auctionService.updateAuction(auctionId, auctionCom);
		return updatedAuction;
	}
	
	@DeleteMapping(value="/{auctionId}")
	public void deleteAuction(@PathVariable("auctionId") int auctionId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Auction auction = auctionService.findAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// 현재 로그인한 유저와 비교해서 일치하면 진행
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("Customer");

		if (!(customer.getCustomerId() == auction.getCustomer().getCustomerId())) {
			return;
		}
		
		auctionService.deleteAuction(auctionId);
	}
}
