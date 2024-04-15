package com.webapp.spring.repositories;

import com.webapp.spring.models.Category;
import org.springframework.data.repository.CrudRepository;


//This is the Link for CRUD Operations of the database and Model
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	

}
