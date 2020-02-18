package com.ceib.nein.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaseEntryController {
	
	@GetMapping("/user/newentry")
	public String root() {
		return "user/case_registration";
	}

	

}
