package com.ceib.nein.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceib.nein.app.entities.User;
import com.ceib.nein.app.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
    private HttpSession httpSession;


	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "hello imran";
	}

	@GetMapping("/user")
	public String userIndex(Model model) {
		User user=(User)httpSession.getAttribute("user");
		System.out.println(user.getUserName());
		model.addAttribute("user", userService.findUserByUserName(user.getUserName()));
		return "user/userhome";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute(new User());
		return "login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

}
