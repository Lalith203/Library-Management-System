package com.webapp.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.webapp.spring.models.Category;
import com.webapp.spring.models.LibraryItem;
import com.webapp.spring.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	LibraryItemService libraryItemService;
	
	
	//Show all categories
	public String getAllCategories(Model m){
		//Getting all categories
				List<Category> allCategories = (List<Category>)categoryRepository.findAll();
				//Sending the list of categories to View
				m.addAttribute("list", allCategories);
				//Calling the view
				return "category";
	}
	
	//Get all categories
	public List<Category> getAllCategories(){
		return (List<Category>) categoryRepository.findAll();
	}
	//Show category by id if present
	public Optional<Category> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}
	
	
	//Add or Update a category
	public String addCategory(String categoryName, Model m) {
		try {
		//Create a category object with just category name
		Category category = new Category(categoryName);
		//Using the service method to add the new category
		categoryRepository.save(category);
		
			
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		//redirect to '/category' page
		return "redirect:categories";
	}
	
	//Delete a category
	public String deleteCategory(Integer id, Model m) {
		try {
			//Checking if the category is referenced in a library item
			List<LibraryItem> listLibraryItems = libraryItemService.getAllLibraryItems();
			for(LibraryItem libraryItem: listLibraryItems) {
				if(libraryItem.getCategory().getId() == id) {
					m.addAttribute("status", "The Category cannot be deleted as it is referenced in a Library Item!");
					//Returning the success page
					return "success";
				}
			}
			
			categoryRepository.deleteById(id);
			m.addAttribute("status", "Category Successfully Deleted!");
		}catch(Exception e) {
			m.addAttribute("status", "There was an error deleting category!");
		}
		
		//returning success page
		return "success";
	}

	//Updating a category
	public String updateCategory( Integer categoryId, String categoryName, Model m) {
		try {
			//Create a category object with just category name
			Category category = new Category(categoryId, categoryName);
			//Using the service method to add the new category
			categoryRepository.save(category);
			//the value of 'status' variable if the category updated successfully
			m.addAttribute("status", "Category updated successfully!");
			}catch(Exception e){
				System.out.print(e.getMessage());
				//the value of 'status' variable if there will be an error
				m.addAttribute("status", "There was an error updating category");
			}
			
			//returning the success page
			return "success";
		
	}
}
