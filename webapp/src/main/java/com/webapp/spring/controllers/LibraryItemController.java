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

import com.webapp.spring.models.Category;
import com.webapp.spring.services.CategoryService;
import com.webapp.spring.services.LibraryItemService;

@Controller
public class LibraryItemController {

	@Autowired
	LibraryItemService libraryItemService;

	@Autowired
	CategoryService categoryService;


	//Showing all library items
	@RequestMapping("/library")
	public String getAllLibraryItems(Model m) {
		return libraryItemService.getAllLibraryItems(m);
	}
	
	//Adding a library item
	@RequestMapping(value="/library", method= RequestMethod.POST)
	public String addLibraryItem(@RequestParam Map<String, String> params,  Model m) {
		return libraryItemService.addLibraryItem(params, m);
	}
	
	//Delete a Library Item
	@RequestMapping(value="/library/delete/{id}", method=RequestMethod.GET)
	public String deleteLibraryItem(@PathVariable("id") Integer id, Model m){
		return libraryItemService.deleteLibraryItemById(id, m);
	}
	
	//Returns the edit Library Item page
	@RequestMapping(value="/library/edit/{id}", method=RequestMethod.GET)
	public String editLibraryItem(@PathVariable("id") Integer id, Model m){

		List<Category> category = categoryService.getAllCategories();
		m.addAttribute("categoryList", category);
		m.addAttribute("id", id);
		return "editlibraryitem";
	}
	
	
	
	//Edit the Library Item
	@RequestMapping(value="/library/edit/library/edit", method=RequestMethod.POST)
	public String editLibraryItem(@RequestParam Map<String, String> params,  Model m){
		return libraryItemService.updateLibraryItem(params, m);
	}
	
	//Check in a library item
	@RequestMapping(value="/library/checkin/{id}", method=RequestMethod.GET)
	public String checkinLibraryItem(@PathVariable("id") Integer id, Model m){
		return libraryItemService.checkinLibraryItem(id, m);
	}
	
	
	//Returns the checkout Library Item page
	@RequestMapping(value="/library/checkout/{id}", method=RequestMethod.GET)
	public String checkoutLibraryItem(@PathVariable("id") Integer id, Model m){
			m.addAttribute("id", id);
			return "checkoutlibraryitem";
		}
		
	
	//Checking out a library item
	@RequestMapping(value="/library/checkout/library/checkout", method=RequestMethod.POST)
	public String checkoutLibraryItem(@RequestParam Map<String, String> params,  Model m){
		return libraryItemService.checkOutLibraryItem(params, m);
		}
}
