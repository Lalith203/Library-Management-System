package com.webapp.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webapp.spring.models.Employees;
import com.webapp.spring.repositories.EmployeesRepository;

@Service
public class EmployeesService {
	
	//Coefficients for generating salary
	private final double EMPLOYEE_SALARY_COEFFICIENT = 1.125;
	private final double MANAGER_SALARY_COEFFICIENT = 1.125;
	//private final double CEO_SALARY_COEFFICIENT = 1.125;
	
	//Linking the employees Repository
	@Autowired
	EmployeesRepository employeesRepository;
	
	//Returns the view with the Employees list and manager list added as an attribute
	public String getAllEmployees(Model m){
		List<Employees> employees = (List<Employees>) employeesRepository.findAll();
		List<Employees> managerList = getAllManagers();

		m.addAttribute("managerList", managerList);
		m.addAttribute("list", employees);
		return "employees";
	}
	
	//Returns list of manager
	public List<Employees> getAllManagers() {
		
		List<Employees> employees = (List<Employees>)employeesRepository.findAll();
		List<Employees> managers = new ArrayList<Employees>();
		//Adding managers in managers list
		for(Employees emp: employees) {
			if(emp.getIsManager())
			{
				managers.add(emp);
			}
//			else if(emp.getIsCEO()) {
//				managers.add(emp);
//			}
		}
		return managers;
	}

	//Find employee by id
	public Optional<Employees> getEmployeesById(Integer id){
		return employeesRepository.findById(id);
	}
	
	
	//Adding a employee
	public String addEmployees(Map<String, String> params,  Model m){
		List<Employees> employees = (List<Employees>)employeesRepository.findAll();

		try {
			//Creating the employees object from the values got in the request
			Employees employee = new Employees();
			employee.setFirstName(params.get("firstName"));
			employee.setLastName(params.get("lastName"));
			//employee.setIsCEO(Boolean.parseBoolean(params.get("isCeo")));
			employee.setIsManager(Boolean.parseBoolean(params.get("isManager")));
			employee.setManagerId(!params.get("managerId").equals("") ? Integer.parseInt(params.get("managerId")) : null); //Add the manager id null if its an empty string
			//Storing salary using the calculate salary function
			double salary = calculateSalary(Integer.parseInt(params.get("rank")), employee);
			employee.setSalary(salary);

//			Checking conditions for the CEO
//			if(employee.getIsCEO()) {
//				if(employee.getManagerId() != null)
//				{	m.addAttribute("status", "CEO cannot be managed by any manager!");
//				return "success";
//				}
//				for(Employees emp: employees) {
//					if(emp.getIsCEO())
//					{
//						m.addAttribute("status", "There can be only one CEO at a time!");
//						return "success";
//					}
//				}
//
//			}
			
			//Checking conditions for the manager
			if(employee.getManagerId() != null) {
				Optional<Employees> manager = getEmployeesById(employee.getManagerId()); 

				//!manager.get().getIsCEO() && add this in the below if statement if not work
				if(!manager.get().getIsManager()) {
					m.addAttribute("status", "Employee cannot manage other employees!");
					return "success";
				}

//				if(manager.get().getIsCEO() && !employee.getIsManager()) {
//					m.addAttribute("status", "CEO can manage, managers only!");
//					return "success";
//				}	
			}
			//Adding the employee to database
			employeesRepository.save(employee);
		}catch(Exception e) {
			m.addAttribute("status", "There was an error in adding the Employee!");
			return "success";
		}
		//Redirecting to the employees page after adding the employee
		return "redirect:employees";
	}
		
	
	//Delete a employee
	public String deleteEmployees(Integer id, Model m) {
		//Getting list of all employees		
		List<Employees> employees = (List<Employees>) employeesRepository.findAll();
		//Checking that if it is referenced as a manager or not		
		for(Employees emp: employees) {
					if(emp.getManagerId() == id) {
						//If the condition is true it will return the success page 
						m.addAttribute("status", "Employee is referenced as a Manager for another employee!");
						return "success";
					}
				}
				
				try {
					//Deleting the employee
					employeesRepository.deleteById(id);
					m.addAttribute("status", "Employee successfully deleted!");
					
				}catch(Exception e) {
					m.addAttribute("status", "There was an error deleting Employee!");
				}
				
				//returning success page
				return "success";
	}
	
	//Updating a employee
	public String updateEmployees(Map<String, String> params,  Model m) {
		//Getting the list of employees
		List<Employees> employees = (List<Employees>)employeesRepository.findAll(); 
		Optional<Employees> emp = employeesRepository.findById(Integer.parseInt(params.get("id"))); //Employee being updated
		
		try {
			//Creating the update object of employee
			Employees employee = new Employees(); //Updated Values of the employee being updated
			employee.setId(Integer.parseInt(params.get("id"))); //For update we need to add id
			employee.setFirstName(params.get("firstName"));
			employee.setLastName(params.get("lastName"));
			//employee.setIsCEO(Boolean.parseBoolean(params.get("isCeo")));
			employee.setIsManager(Boolean.parseBoolean(params.get("isManager")));
			employee.setManagerId(!params.get("managerId").equals("") ? Integer.parseInt(params.get("managerId")) : null);
			//Calculating the salary
			double salary = calculateSalary(Integer.parseInt(params.get("rank")), employee);
			employee.setSalary(salary);

			//Checking conditions for the CEO
//			if(employee.getIsCEO()) {
//				if(employee.getManagerId() != null)
//				{	m.addAttribute("status", "CEO cannot be managed by any manager!");
//				return "success";
//				}
//				
//				if(!emp.get().getIsCEO()) //Only check for the CEO condition if the employee being updated is not a CEO
//				{
//					for(Employees e: employees) {
//					if(e.getIsCEO())
//					{
//						m.addAttribute("status", "There can be only one CEO at a time!");
//						return "success";
//					}
//				}
//				}
//			}
			
			//Checking conditions for the manager
			if(employee.getManagerId() != null) {
				Optional<Employees> manager = getEmployeesById(employee.getManagerId()); 

				//!manager.get().getIsCEO() && add this in the if statement
				if(!manager.get().getIsManager()) {
					m.addAttribute("status", "Employee cannot manage other employees!");
					return "success";
				}
				//manager.get().getIsCEO() && add this in the if statement
				if(!employee.getIsManager()) {
					m.addAttribute("status", "CEO can manage, managers only!");
					return "success";
				}	
			}
			
			//This will update the employee as the id is the same 
			employeesRepository.save(employee);
			m.addAttribute("status", "Employee updated successfully!");
		}catch(Exception e) {
			m.addAttribute("status", "There was an error in updating the Employee!");
			return "success";
		}
		
		
		return "success"; //for update we need to return the success page
	}
	
	//Calculating the salary of employee according to rank and salary coefficient
	public double calculateSalary(Integer rank, Employees employee) {
		
		double salary = 0.0;
		
//		if(employee.getIsCEO())
//			salary = rank * CEO_SALARY_COEFFICIENT;
		//else 
		if(employee.getIsManager())
			salary = rank * MANAGER_SALARY_COEFFICIENT;
		else
			salary = rank * EMPLOYEE_SALARY_COEFFICIENT;
		
		return salary;
	}
	
	
}
