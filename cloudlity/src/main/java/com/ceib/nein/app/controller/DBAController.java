package com.ceib.nein.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ceib.nein.app.entities.User;
import com.ceib.nein.app.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DBAController {

	@Autowired
	private UserService userService;
	@Autowired
    private HttpSession httpSession;

	@GetMapping("/dba/signup")
	public String userSignup(Model model) {
		model.addAttribute(new User());
		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("userList", userService.allUserList());
		return "dba/user_registration";
	}

	@GetMapping("/dba")
	public String dbaHome( Model model) {
		User user=(User)httpSession.getAttribute("user");
		//System.out.println(user.getUserName());
		//model.addAttribute("user", userService.findUserByUserName(user.getUserName()));
		return "dba/dbahome";
	}

	@PostMapping("/signup")
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		ObjectMapper ob = new ObjectMapper();
		try {

			System.out.println("controller====" + ob.writeValueAsString(user));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		User userExists = userService.findUserByUserName(user.getUserName());
		System.out.println("userExists :" + userExists);
		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user", "Username already exist !");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("dba/user_registration");
		} else {
			try {
				userService.saveUser(user);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("roles", userService.findAllRoles());
			model.setViewName("dba/user_registration");
		}
		return model;

	}

	@GetMapping("/dba/userlist")
	public String userList(Model model) {
		model.addAttribute(new User());
		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("userList", userService.allUserList());
		return "dba/user_list";
	}

}
