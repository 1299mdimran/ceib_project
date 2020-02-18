package com.ceib.nein.app.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceib.nein.app.services.KayDocService;

@RestController
public class KayDocRestController {

	
	@Autowired
	KayDocService docService;
	
	
	@RequestMapping(value ="/getDataList" , method=RequestMethod.GET)
	public Object getData() {
		System.out.println("get data list ===");
		return docService.getData();
	}
}
