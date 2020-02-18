package com.ceib.nein.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ceib.nein.app.services.KayDocService;

@Controller
public class kayDocController {

	@Autowired
	KayDocService kayDocService;
	
	@GetMapping("/kaydoc-directory")
	public String root(Model model) {
		Object obj =kayDocService.getkayDocDirectorList();
		model.addAttribute("docList", obj);
		return "directoryList";
	}
	
	@GetMapping("/kaydoc-createFolder")
	public String createFolder() {
		kayDocService.createFolder();
		return "directoryList";
	}
	
}
