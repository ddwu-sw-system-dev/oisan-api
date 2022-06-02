package com.example.oisan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.example.oisan.domain.OiPayUsage;

@Service
public class OiPayUsageService {

	private Map<Integer, OiPayUsage> oiPayMap = new HashMap<Integer, OiPayUsage>();
	private int oiPayIdSeq = 0;
	
	public OiPayUsageService() {
		Date date = new Date();
		oiPayMap.put(oiPayIdSeq++, new OiPayUsage(1, oiPayIdSeq, 0, 1000, 0, 0, date));
		oiPayMap.put(oiPayIdSeq++, new OiPayUsage(1, oiPayIdSeq, 0, 2000, 0, 0, date));
		oiPayMap.put(oiPayIdSeq, new OiPayUsage(1, oiPayIdSeq, 1, 1500, 0, 0, date));
		oiPayIdSeq++;
	}
	
	public void createOiPay(OiPayUsage oiPay) {
		oiPay.setCreateAt(new Date());
		oiPay.setRemain(0);
		oiPayMap.put(1, oiPay);
	}
	
	public List<OiPayUsage> getOiPayUsage() {
		return new ArrayList<OiPayUsage>(oiPayMap.values());
	}
	
	public List<OiPayUsage> findUserOiPay(int userId) {
		List<OiPayUsage> myUsage = new ArrayList<OiPayUsage>();
		
		for (OiPayUsage oiPay : oiPayMap.values()) {
			if (oiPay.getCustomerId() == userId) { 
				//이 로직으로 어떻게 마지막 걸 가져오지여?
				myUsage.add(oiPay);
			}
		}
		return myUsage;
	}
	
	public void deleteOiPay(int oiPayId) {
		oiPayMap.remove(oiPayId);
	}
	
	public void chargeOiPay(int userId, int amount) {
//		OiPayUsage oiPay = findUserOiPay(userId);
//		int remain = oiPay.getRemain();
		int remain = 0;
		Date date = new Date();
		
//		oiPay.setType(0);
//
//		oiPay.setAmount(amount);
//		oiPay.setRemain(remain + oiPay.getAmount());
		// user의 remain? 도 바꿔야되는데
		oiPayMap.put(oiPayIdSeq, new OiPayUsage(userId, oiPayIdSeq, 0, amount, remain, 0, date));
		oiPayIdSeq++;
	}
	
	public void useOiPay(int userId, int amount) {
//		OiPayUsage oiPay = findUserOiPay(userId);
		Date date = new Date();
//		int remain = oiPay.getRemain();
//		if (amount > remain) //잔액보다 사용하고자하는 양이 크면 안됨!
//			throw exception;¨
//		oiPay.setType(1);

//		oiPay.setAmount(amount);
//		oiPay.setRemain(remain - oiPay.getAmount());
		oiPayMap.put(oiPayIdSeq, new OiPayUsage(4, oiPayIdSeq, 1, amount, 0, 0, date));
		oiPayIdSeq++;
	}
}
