package com.example.oisan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.oisan.domain.OiPayUsage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.oisan.service.OiPayUsageService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/oipay/charge")
@SessionAttributes("chargeReq")
public class OiPayUsageRechargeController {
	private static final String OIPAY_RECHARGE_FORM = "oipay/rechargeForm";
	
	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}
		
	@ModelAttribute("chargeReq") // chargeInfo 객체의 이름 지정
	public OiPayUsageRechargeCommand formBacking(HttpServletRequest request) {
		OiPayUsageRechargeCommand chargeInfo = new OiPayUsageRechargeCommand();
		// chargeInfo.setType(0); //charge - 0, use - 1 : service 에서 수행?

		return chargeInfo; // session에 “chargeReq” 이름으로 저장됨
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return OIPAY_RECHARGE_FORM;
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String recharge(
//			@ModelAttribute("chargeReq") OiPayUsageRechargeCommand chargeReq, Model model,
//			BindingResult bindingResult, SessionStatus status) {		
//		
//		if (bindingResult.hasErrors()) {
//			return OIPAY_RECHARGE_FORM;
//		}
//		
//		oiPayUsageService.chargeOiPay(chargeReq.getUserId(), chargeReq.getAmount());
//		
//		List<OiPayUsage> allOipay = oiPayUsageService.getOiPayUsage();
//		model.addAttribute("alloipay", allOipay);
//		
//		status.setComplete(); 
//		
//		return "oipay/result"; //redirect oipay/userId 로 가야할듯??
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public List<OiPayUsage> recharge(@RequestBody OiPayUsageRechargeCommand chargeReq) {		
		System.out.println(chargeReq);
		oiPayUsageService.chargeOiPay(chargeReq.getUserId(), chargeReq.getAmount());
		
		List<OiPayUsage> myUsage = (List<OiPayUsage>) oiPayUsageService.findUserOiPay(chargeReq.getUserId());
		System.out.println(myUsage);
		return myUsage; 
	}
	
	
}