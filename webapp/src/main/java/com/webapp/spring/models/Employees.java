package com.webapp.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id = 0;

	private String firstName;
	private String lastName;
	private double salary; 
	//private boolean isCEO = false;
	private boolean isManager = false;
	
	@Column(nullable = true)
	private Integer managerId;
	
	public Employees() {
		
	}
	//boolean isCEO add this in the Employees constructor if things don't work
	public Employees(Integer id, String firstName, String lastName, double salary, boolean isManager,
			int managerId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		//this.isCEO = isCEO;
		this.isManager = isManager;
		this.managerId = managerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
//	public boolean getIsCEO() {
//		return isCEO;
//	}
//	public void setIsCEO(boolean isCEO) {
//		this.isCEO = isCEO;
//	}
	public boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	


}
