package com.example.oisan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.oisan.domain.OiPayUsage;
import com.example.oisan.service.OiPayUsageService;
import com.example.oisan.controller.OiPayUsageUseCommand;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/oipay/use")
//@SessionAttributes("useReq")
public class OiPayUsageUseController {
	private static final String OIPAY_USE_FORM = "oipay/useForm";
	
	@Autowired
	private OiPayUsageService oiPayUsageService;
	public void setOiPayUsageService(OiPayUsageService oiPayUsageService) {
		this.oiPayUsageService = oiPayUsageService;
	}

	@ModelAttribute("useReq") // chargeInfo 객체의 이름 지정
	public OiPayUsageUseCommand formBacking(HttpServletRequest request) {
		OiPayUsageUseCommand useInfo = new OiPayUsageUseCommand();
		// useInfo.setType(1); //charge - 0, use - 1 : service 에서 수행?

		return useInfo; // session에 “useReq” 이름으로 저장됨
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return OIPAY_USE_FORM;
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String recharge(
//			@ModelAttribute("useReq") OiPayUsageUseCommand useReq, Model model,
//			BindingResult bindingResult, SessionStatus status) {		
//		
//		if (bindingResult.hasErrors()) {
//			return OIPAY_USE_FORM;
//		}
//		
//		oiPayUsageService.useOiPay(useReq.getUserId(), useReq.getAmount());
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
	public List<OiPayUsage> recharge(@RequestBody OiPayUsageUseCommand useReq) {		
		System.out.println(useReq);
		
		oiPayUsageService.useOiPay(useReq.getUserId(), useReq.getAmount());
		
		List<OiPayUsage> myUsage = (List<OiPayUsage>) oiPayUsageService.findUserOiPay(useReq.getUserId());
		
		return myUsage; 
	}
	
}
