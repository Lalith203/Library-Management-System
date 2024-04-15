package com.webapp.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webapp.spring.models.Employees;

//This is the Link for CRUD Operations of the database and Model
public interface EmployeesRepository extends CrudRepository<Employees, Integer>{

}
