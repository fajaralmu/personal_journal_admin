package com.fajar.livestreaming.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fajar.livestreaming.annotation.CustomRequestInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author fajar
 *
 */
@Slf4j
@RequestMapping("public")
@Controller 
public class MvcPublicController extends BaseController{  
	
	public MvcPublicController() {
		log.info("-----------------Mvc App Controller------------------");
	}
	@RequestMapping(value = { "/main"})
	@CustomRequestInfo(pageUrl = "pages/main-menu")
	public String index(Model model,
			HttpServletRequest request, HttpServletResponse response)  {
		model.addAttribute("title", bindedValues.getApplicationHeaderLabel());
		return basePage;
	}
	 
	
	
	
	 

}