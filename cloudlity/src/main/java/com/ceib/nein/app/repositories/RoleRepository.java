package com.ceib.nein.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ceib.nein.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);

	
	Role findById(Long roleId);
	
	//@Query("Select rol from Role where rol.name=:name")
	//List<Role> findRoles(@Param("name") String name);
	
}
