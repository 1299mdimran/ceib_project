package com.ceib.nein.app.services;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceib.nein.app.entities.Role;
import com.ceib.nein.app.entities.User;
import com.ceib.nein.app.repositories.UserRepository;

@Service
public class UserServiceInfo implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	@Autowired
    private HttpSession httpSession;
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = repo.findUserByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		/*
		 * if(requireActivation && !user.getToken().equals("1")) {
		 * Application.log.error("User [" + username +
		 * "] tried to login but is not activated"); throw new
		 * UsernameNotFoundException(username + " has not been activated yet");
		 * }
		 */
		// httpSession.setAttribute(user, user);
		// List<GrantedAuthority> auth =
		// AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
		// return new
		// org.springframework.security.core.userdetails.User(user.getUsername(),
		// user.getPassword(), grantedAuthorities);
		httpSession.setAttribute("user", user);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);// TODO Auto-generated method stub
		// return null;
		
		
		
	}

}
