package com.webapp.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.spring.services.*;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	LibraryItemService libraryItemService;
	
	
	/* It provides list of employees in model object */  
	@RequestMapping("/categories")
	public String getAllCategories(Model m){
		//Return the view with all the categories
		return categoryService.getAllCategories(m);
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String addCategories(@RequestParam("categoryName") String categoryName, Model m){

		return categoryService.addCategory(categoryName, m);
	}
	
	@RequestMapping(value="/categories/delete/{id}", method=RequestMethod.GET)
	public String deleteCategories(@PathVariable("id") Integer id, Model m){
		//Deleting a category and returns the success page
		return categoryService.deleteCategory(id, m);
	}
	
	//Send to the Edit category page
	@RequestMapping(value="/categories/edit/{id}", method=RequestMethod.GET)
	public String editCategories(@PathVariable("id") Integer id, Model m){
		
		m.addAttribute("id", id);
		return "editcategory";
	}
	
	
	//Updating the category
	@RequestMapping(value="/categories/edit/categories/edit", method=RequestMethod.POST)
	public String editCategories(@RequestParam("categoryId") Integer categoryId, @RequestParam("categoryName") String categoryName, Model m){
		return categoryService.updateCategory(categoryId, categoryName, m);	
	}
	
	
	

}
