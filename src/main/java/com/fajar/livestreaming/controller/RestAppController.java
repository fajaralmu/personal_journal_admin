package com.fajar.livestreaming.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fajar.livestreaming.dto.WebResponse;

@CrossOrigin
@RestController
public class RestAppController extends BaseController {
	Logger log = LoggerFactory.getLogger(RestAppController.class);

	 
	public RestAppController() {
		log.info("------------------RestAppController #1-----------------");
	}

	@PostConstruct
	public void init() {
//		LogProxyFactory.setLoggers(this);
	}
	
	@GetMapping("/app/api/get")
	public WebResponse apiPrivate() {
		return new WebResponse();
	}
	@GetMapping("/public/api/get")
	public WebResponse apiPublic() {
		return new WebResponse();
	}

	 
}
