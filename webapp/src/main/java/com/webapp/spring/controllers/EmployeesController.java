package com.webapp.spring.controllers;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.spring.models.Employees;
import com.webapp.spring.services.EmployeesService;
//
//@Controller
//public class EmployeesController {
//
//	@Autowired
//	EmployeesService employeesService;
//
//	@RequestMapping("/employees")
//	public String getAllEmployees(Model m) {
//		//Returns the value from employeeService getAllEmployees method
//		return employeesService.getAllEmployees(m);
//	}
//
//	@RequestMapping(value="/employees", method=RequestMethod.POST)
//	public String addEmployees(@RequestParam Map<String, String> params,  Model m) {
//		//Returns the value from employeeService addEmployees method
//		return employeesService.addEmployees(params, m);
//	}
//
//	@RequestMapping(value="/employees/delete/{id}", method=RequestMethod.GET)
//	public String deleteEmployees(@PathVariable("id") Integer id, Model m){
//		//Returns the value from employeeService deleteEmployees method
//		return employeesService.deleteEmployees(id, m);
//	}
//
//	@RequestMapping(value="/employees/edit/{id}", method=RequestMethod.GET)
//	public String editEmployees(@PathVariable("id") int id, Model m) {
//		
//		//Getting the managers list for the dropdown
//		List<Employees> managerList = employeesService.getAllManagers();
//		//Sending the manager list as an attribute to the view
//		m.addAttribute("managerList", managerList);
//		m.addAttribute("id", id);
//		return "editemployees";
//	}
//
//	@RequestMapping(value="/employees/edit/employees/edit", method=RequestMethod.POST)
//	public String editEmployees(@RequestParam Map<String, String> params, Model m) {
//
//		//Returns the value from employeeService updateEmployees method
//		return employeesService.updateEmployees(params, m);
//	}
//
//
//
//}
@Controller
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @RequestMapping("/employees")
    public String getAllEmployees(Model m) {
        return employeesService.getAllEmployees(m);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String addEmployees(@RequestParam Map<String, String> params, Model m) {
        return employeesService.addEmployees(params, m);
    }

    @RequestMapping(value = "/employees/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployees(@PathVariable("id") Integer id, Model m) {
        return employeesService.deleteEmployees(id, m);
    }

    @RequestMapping(value = "/employees/edit/{id}", method = RequestMethod.GET)
    public String editEmployees(@PathVariable("id") int id, Model m) {
        List<Employees> managerList = employeesService.getAllManagers();
        m.addAttribute("managerList", managerList);
        m.addAttribute("id", id);
        return "editemployees";
    }

    @RequestMapping(value = "/employees/edit", method = RequestMethod.POST)
    public String updateEmployees(@RequestParam Map<String, String> params, Model m) {
        return employeesService.updateEmployees(params, m);
    }
}


