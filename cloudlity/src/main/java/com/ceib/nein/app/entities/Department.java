package com.ceib.nein.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
	// @SequenceGenerator(sequenceName = "dept_seq", allocationSize = 1, name =
	// "dept_seq")

	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String departmentName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
