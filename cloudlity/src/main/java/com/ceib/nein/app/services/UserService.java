package com.ceib.nein.app.services;

import java.util.List;

import com.ceib.nein.app.entities.Role;
import com.ceib.nein.app.entities.User;

public interface UserService {
	User findUserByUserName(String userName);

	void saveUser(User user);

	List<Role> findAllRoles();

	List<User> allUserList();

}
