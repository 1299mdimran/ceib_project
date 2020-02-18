package com.ceib.nein.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceib.nein.app.entities.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	
	User findUserByUserName(String userName);
	
	/*@Query("select user ,role ,dep from User as user "
			+ "Left join Role as role on role.id = user.role")
	Object[] findUserRoleAndDeprtment(@Param("userName")String userName);*/
	

}
