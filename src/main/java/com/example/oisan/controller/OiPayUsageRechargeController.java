package com.example.oisan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.oisan.domain.OiPayUsage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.oisan.service.OiPayUsageService;

@Controller
@RequestMapping("/oipay/charge")
//@SessionAttributes("chargeReq")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OiPayUsageRechargeController {
	
	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public OiPayUsage recharge(@RequestBody OiPayUsageRechargeCommand chargeReq) {		
		System.out.println("charge Request :" + chargeReq);
		
		OiPayUsage response = oiPayUsageService.chargeOiPay(chargeReq.getCustomerId(), chargeReq.getAmount());
		
		return response; 
	}
	
	
}