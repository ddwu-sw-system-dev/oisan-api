package com.example.oisan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.oisan.domain.OiPayUsage;
import com.example.oisan.service.OiPayUsageService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OiPayUsageController {
	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}
	
//	@RequestMapping("/oipay/{userId}")
//	public String oipayDetail(@PathVariable int userId, Model model) {
//		List<OiPayUsage> myUsage = (List<OiPayUsage>) oiPayUsageService.findUserOiPay(userId);
//
//		if (myUsage == null) {
//			return "oipay/notFound";
//		} 
//		model.addAttribute("myUsage", myUsage);
//		return "oipay/myUsage" ;
//	}
	
	@RequestMapping("/oipay/{userId}")
	
	@ResponseBody
	public List<OiPayUsage> oipayDetail(@PathVariable int userId) {
		System.out.print(userId);
		List<OiPayUsage> myUsage = (List<OiPayUsage>) oiPayUsageService.findUserOiPay(userId);

		return myUsage;
	}
	
	
}