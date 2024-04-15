package com.webapp.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webapp.spring.models.LibraryItem;

//This is the Link for CRUD Operations of the database and Model
public interface LibraryItemRepository extends CrudRepository<LibraryItem, Integer>{

}
