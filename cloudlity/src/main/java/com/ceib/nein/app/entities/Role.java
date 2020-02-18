package com.ceib.nein.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	//@SequenceGenerator(sequenceName = "role_seq", allocationSize = 1, name = "role_seq")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role")
	private String role;
	
	private String roleName;

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
