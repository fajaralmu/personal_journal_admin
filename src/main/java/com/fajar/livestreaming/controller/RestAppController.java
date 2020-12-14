package com.fajar.livestreaming.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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

	 
}
