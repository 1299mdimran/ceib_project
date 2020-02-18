package com.ceib.nein.app.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ceib.nein.app.entities.Role;
import com.ceib.nein.app.entities.User;
import com.ceib.nein.app.repositories.RoleRepository;
import com.ceib.nein.app.repositories.UserRepository;
import com.ceib.nein.app.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRespository;
	
	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);

	}

	@Override
	public void saveUser(User user) {
		ObjectMapper ob=new ObjectMapper();
		try {
			
			System.out.println(ob.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		 user.setPassword(encodeUserPassword(user.getPassword()));
		//user.setActive(1);
		Role userRole = roleRespository.findById(user.getRoleId());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRespository.findAll();
	}

	@Override
	public List<User> allUserList() {
		return userRepository.findAll();
	}

	
	 public String encodeUserPassword(String password) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        return passwordEncoder.encode(password);
	    }
}
