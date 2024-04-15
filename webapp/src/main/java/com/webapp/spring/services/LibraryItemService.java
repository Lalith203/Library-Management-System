package com.webapp.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webapp.spring.models.Category;
import com.webapp.spring.models.LibraryItem;
import com.webapp.spring.repositories.LibraryItemRepository;
import com.webapp.spring.utils.Utils;

@Service
public class LibraryItemService {

	@Autowired
	LibraryItemRepository libraryItemRepository;

	@Autowired
	CategoryService categoryService;
	
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	//Returns a list of library items
	public List<LibraryItem> getAllLibraryItems(){
		return (List<LibraryItem>)libraryItemRepository.findAll();
	}

	//Returns the view with the library items 
	public String getAllLibraryItems(Model m){

		//Get the list of libraryItems
		List<LibraryItem> libraryItems = (List<LibraryItem>)libraryItemRepository.findAll();
		
		//Getting the list of all categories
		List<Category> category = categoryService.getAllCategories();
		//Sending the list of categories and library items as attributes to the view
		m.addAttribute("categoryList", category);
		m.addAttribute("list", libraryItems);
		return "library";
	}

	//Add a library item
	public void addLibraryItem(LibraryItem libraryItem) {
		libraryItemRepository.save(libraryItem);
	}
	
	//Adds a library item
	public String addLibraryItem(Map<String, String> params,  Model m){
		LibraryItem libraryItem;
		String type = params.get("type");
		try {
		//For books
			if(type.equals("book")) {
			//Getting the values from the requrest params
			String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			String author = params.get("author");
			int categoryId = Integer.parseInt(params.get("category"));
			int pages = Integer.parseInt(params.get("pages"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());
			if(!borrowable)
			{
				String borrower = params.get("borrower");
				Date borrowDate = (Date) formatter.parse(params.get("borrowDate"));
				libraryItem = new LibraryItem(title, author, category, pages, borrowable, borrower, borrowDate, type);
			}
			else
				libraryItem = new LibraryItem(title, author, category, pages, borrowable, type);
			try {
				//Adding the library item to the database
				addLibraryItem(libraryItem);
			}catch(Exception e) {
				m.addAttribute("status", "There was an error adding Library Item!");
				System.out.print(e.getMessage());
				return "success";
			}
			
			//for DVD and Audio book
		}else if(type.equals("dvd") || type.equals("audiobook")) {
			//Getting the values from the request params
			String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			int categoryId = Integer.parseInt(params.get("category"));
			int runTimeMinutes = Integer.parseInt(params.get("runTimeMinutes"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());

			if(!borrowable)
			{
				String borrower = params.get("borrower");
				Date borrowDate = (Date) formatter.parse(params.get("borrowDate"));
				libraryItem = new LibraryItem(title, category, runTimeMinutes, borrowable, borrower, borrowDate, type);
			}
			else
				libraryItem = new LibraryItem(title,category, runTimeMinutes, borrowable, type);
			try {
				//Adding the library Item
				addLibraryItem(libraryItem);
			}catch(Exception e) {
				m.addAttribute("status", "There was an error adding Library Item!");
				System.out.print(e.getMessage());
				return "success";
			}
			
			//For reference books
		}else if(type.equals("referencebook")) {
			//Getting values from request params
			String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			String author = params.get("author");
			int categoryId = Integer.parseInt(params.get("category"));
			int pages = Integer.parseInt(params.get("pages"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());
			
			libraryItem = new LibraryItem(title, author, category, pages, borrowable, type);
			try {
				//Adding the library item in the database
				addLibraryItem(libraryItem);
			}catch(Exception e) {
				m.addAttribute("status", "There was an error adding Library Item!");
				System.out.print(e.getMessage());
				return "success";
			}
		}
		}catch(Exception e) {
			m.addAttribute("status", "There was an error adding Library Item!");
			return "success";
		}

		//Redirecting to the library page
		return "redirect:library";
	}

	//Deletes a library Item by id
	public String deleteLibraryItemById( Integer id, Model m) {
				//Deleting a libraryItem
				try {
					//
					libraryItemRepository.deleteById(id);
					m.addAttribute("status", "Library Item Successfully Deleted!");
				}catch(Exception e) {
					m.addAttribute("status", "There was an error deleting Library Item!");
				}
				
				//returning success page
				return "success";
	}

	
	//Get a Library Item by id if it exists
	public Optional<LibraryItem> getLibraryItemById(Integer id){
		return libraryItemRepository.findById(id);
	}

	
	//Update library item
	public String updateLibraryItem(Map<String, String> params,  Model m) {
		LibraryItem libraryItem;
		int id = Integer.parseInt(params.get("id"));
		String type = params.get("type");
		try {
		//For books
			if(type.equals("book")) {
				String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			String author = params.get("author");
			int categoryId = Integer.parseInt(params.get("category"));
			int pages = Integer.parseInt(params.get("pages"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());

			if(!borrowable)
			{
				String borrower = params.get("borrower");
				Date borrowDate = (Date) formatter.parse(params.get("borrowDate"));
				libraryItem = new LibraryItem(id, title, author, category, pages, borrowable, borrower, borrowDate, type);
			}
			else
				libraryItem = new LibraryItem(id, title, author, category, pages, borrowable, type);
			try {
				addLibraryItem(libraryItem);
				m.addAttribute("status", "Library Item updated successfully!");
			}catch(Exception e) {
				m.addAttribute("status", "There was an error updating Library Item!");
				System.out.print(e.getMessage());
				
			}
			
			//for DVD and Audio book
		}else if(type.equals("dvd") || type.equals("audiobook")) {
			String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			int categoryId = Integer.parseInt(params.get("category"));
			int runTimeMinutes = Integer.parseInt(params.get("runTimeMinutes"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());

			if(!borrowable)
			{
				String borrower = params.get("borrower");
				Date borrowDate = (Date) formatter.parse(params.get("borrowDate"));
				libraryItem = new LibraryItem(id, title, category, runTimeMinutes, borrowable, borrower, borrowDate, type);
			}
			else
				libraryItem = new LibraryItem(id, title,category, runTimeMinutes, borrowable, type);
			try {
				addLibraryItem(libraryItem);
				m.addAttribute("status", "Library Item updated successfully!");
			}catch(Exception e) {
				m.addAttribute("status", "There was an error updating Library Item!");
				System.out.print(e.getMessage());
				
			}
			
			//for reference books
		}else if(type.equals("referencebook")) {
			String title = params.get("title") +" ("+Utils.getAcronym(params.get("title"))+")";
			String author = params.get("author");
			int categoryId = Integer.parseInt(params.get("category"));
			int pages = Integer.parseInt(params.get("pages"));
			boolean borrowable = Boolean.parseBoolean(params.get("borrowable"));
			Category category = categoryService.getCategoryById(categoryId).orElse(new Category());
			
			libraryItem = new LibraryItem(id, title, author, category, pages, borrowable, type);
			try {
				addLibraryItem(libraryItem);
				m.addAttribute("status", "Library Item updated successfully!");
			}catch(Exception e) {
				m.addAttribute("status", "There was an error updating Library Item!");
				System.out.print(e.getMessage());
				
			}
		}
		}catch(Exception e) {
			m.addAttribute("status", "There was an error updating Library Item!");
			
		}

		return "success";
	}

	//Check in a library item
	public String checkinLibraryItem(Integer id, Model m) {
		//Checking in a libraryItem
				try {
					//Getting the data of the library item to update the borrowable, borrower, borrowDate fields
					Optional<LibraryItem> libraryItem = getLibraryItemById(id);
					libraryItem.get().setIsBorrowable(true);
					libraryItem.get().setBorrower(null);
					libraryItem.get().setBorrowDate(null);
					//Adding to the database
					addLibraryItem(libraryItem.get());
					m.addAttribute("status", "Library Item checked in successfully!");
				}catch(Exception e) {
					m.addAttribute("status", "There was an error checking in the Library Item!");
				}
				
				//returning success page
				return "success";
	}
	
	//Check out library item
	public String checkOutLibraryItem(Map<String, String> params,  Model m) {
		try {
			int id = Integer.parseInt(params.get("id"));
			String borrower = params.get("borrower");
			Date borrowDate = (Date) formatter.parse(params.get("borrowDate"));
			//Getting the data of the library item to update the borrowable, borrower, borrowDate fields
			Optional<LibraryItem> libraryItem = getLibraryItemById(id);
			libraryItem.get().setIsBorrowable(false);
			libraryItem.get().setBorrower(borrower);
			libraryItem.get().setBorrowDate(borrowDate);
			//Adding to the database
			addLibraryItem(libraryItem.get());
			m.addAttribute("status", "Library Item checked out successfully!");
		}catch(Exception e) {
			m.addAttribute("status", "There was an error checking out the Library Item!");
		}
		
		//returning success page
		return "success";
		
	}

}
