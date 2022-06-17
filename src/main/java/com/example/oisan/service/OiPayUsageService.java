package com.example.oisan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oisan.domain.Customer;
import com.example.oisan.domain.OiPayUsage;
import com.example.oisan.repository.OiPayUsageRepository;
import com.example.oisan.repository.CustomerRepository;

@Service
@Transactional
public class OiPayUsageService {

	@Autowired
	private OiPayUsageRepository oiPayUsageRepository;
	public void setOiPayUsageRepository(OiPayUsageRepository oiPayUsageRepository) {
		this.oiPayUsageRepository = oiPayUsageRepository;
	}

	@Autowired
	private CustomerRepository customerRepository;
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public OiPayUsageService() {}
	
	public List<OiPayUsage> findUserOiPay(int customerId) {
		// 마지막 사용내역이 가장 위로 오도록 내림차순 정렬
		return oiPayUsageRepository.findByCustomerIdOrderByOiPayIdDesc(customerId);
	}
	
	@Transactional
	public OiPayUsage chargeOiPay(int customerId, int amount) {
		System.out.println("charge Id =" + customerId);
		Date date = new Date();
		int remain = 0;
		
		OiPayUsage lastUsage = oiPayUsageRepository.findTopByCustomerIdOrderByOiPayIdDesc(customerId);

		if (lastUsage != null) { // 마지막 사용내역이 있다면
			remain = lastUsage.getRemain();
		}
		remain  = remain + amount;
		
		// OiPayUsage(int customerId, int oiPayId, int type, int amount, int remain, int auctionId, Date createAt);
		// OiPayUsage(int customerId, int type, int amount, int remain, Integer auctionId, Date createAt)
		OiPayUsage current = new OiPayUsage(customerId, 0, amount, remain, 0, date); //auction_id 수정
		int updateSuccess = customerRepository.updateOipayByCustomerId(remain, customerId);
		if (updateSuccess == 0) {
			return null;
		}
		
		return oiPayUsageRepository.save(current);
	}
	
	@Transactional
	public OiPayUsage useOiPay(int customerId, int amount,  int auctionId) {
		Date date = new Date();
		int remain = 0;
		
//		OiPayUsage lastUsage = oiPayUsageRepository.findTopByCustomerIdOrderByOiPayIdDesc(customerId);
		Customer customer = customerRepository.findCustomerByCustomerId(customerId);

//		if (lastUsage != null) { // 마지막 사용내역이 있다면
//			remain = lastUsage.getRemain();
//		}
		
		remain = customer.getOiPay();
		
		if (amount > remain) {
			//잔액보다 사용하고자 하는 양이 크면 예외 처리해야됨
			return null;
		}
		
		remain  = remain - amount;
		
		// 규칙: auctionId가 없으면 0으로
		OiPayUsage current = new OiPayUsage(customerId, 1, amount, remain, auctionId, date);//auction_id 수정
		int updateSuccess = customerRepository.updateOipayByCustomerId(remain, customerId);
		if (updateSuccess == 0) {
			return null;
		}
		
		return oiPayUsageRepository.save(current);
	}
	
	//TODO: auction과 연동하는 부분 추가
	//TODO: user의 oipay remain 바꿔주기 -> 트랜잭션 처리
}
