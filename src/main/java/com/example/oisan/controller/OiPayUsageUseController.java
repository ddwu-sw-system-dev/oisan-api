package com.example.oisan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oisan.domain.OiPayUsage;
import com.example.oisan.service.OiPayUsageService;


@Controller
@RequestMapping("/oipay/use")
//@SessionAttributes("useReq")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OiPayUsageUseController {
	
	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000/oipay", allowedHeaders = "*")
	public OiPayUsage recharge(@RequestBody OiPayUsageUseCommand useReq, HttpServletResponse response) throws IOException {		
		System.out.println("oipay use Request :" + useReq);
		
		OiPayUsage useSuccess = oiPayUsageService.useOiPay(useReq.getCustomerId(), useReq.getAmount(), useReq.getAuctionId());
		
		if(useSuccess == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return useSuccess; 
	}
	
}
