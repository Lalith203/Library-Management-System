package com.webapp.spring.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "library_item")
public class LibraryItem {

	
	/*@Id declares the entity identifier.

	@OneToOne defines a one-to-one relationship between 2 entities.

	mappedBy indicates the inverse of the relationship.

	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id = 0;
	

	@ManyToOne
    @JoinColumn
	private Category category;
	
	private String title;
	private String author;
	
	@Column(nullable = true)
	private Integer pages;
	
	@Column(nullable = true)
	private Integer runTimeMinutes;
	private boolean isBorrowable = false;
	private String borrower;
	
	@Column(nullable = true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy, timezone = UTC")
	private Date borrowDate;
	private String type;
	
	public LibraryItem(){
		
	}
	
	//Constructors for DVD and Audio Book
	//Constructors for Adding items
	public LibraryItem(String title, Category category, Integer runTimeMinutes, boolean isBorrowable, String borrower, Date borrowDate, String type) {
		super();
		this.category = category;
		this.title = title;
		this.runTimeMinutes = runTimeMinutes;
		this.isBorrowable = isBorrowable;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.type = type;
	}
	public LibraryItem(String title2, Category category2, int runTimeMinutes2, boolean borrowable, String type2) {
		this.title = title2;
		this.category = category2;
		this.runTimeMinutes = runTimeMinutes2;
		this.isBorrowable = borrowable;
		this.type = type2;
	}
	
	//Constructors for Updating items
		public LibraryItem(Integer id, String title, Category category, Integer runTimeMinutes, boolean isBorrowable, String borrower, Date borrowDate, String type) {
			super();
			this.id = id;
			this.category = category;
			this.title = title;
			this.runTimeMinutes = runTimeMinutes;
			this.isBorrowable = isBorrowable;
			this.borrower = borrower;
			this.borrowDate = borrowDate;
			this.type = type;
		}
		public LibraryItem(Integer id,String title2, Category category2, int runTimeMinutes2, boolean borrowable, String type2) {
			super();
			this.id = id;
			this.title = title2;
			this.category = category2;
			this.runTimeMinutes = runTimeMinutes2;
			this.isBorrowable = borrowable;
			this.type = type2;
		}
	
	//Constructors for Book and Reference books
		//Constructors for Adding items
	public LibraryItem(String title, String author,Category category, Integer pages, boolean isBorrowable, String type) {
		super();
		this.category = category;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.isBorrowable = isBorrowable;
		this.type = type;
	}
	
	public LibraryItem(String title, String author,Category category, Integer pages, boolean isBorrowable, String borrower, Date borrowDate, String type) {
		super();
		
		this.category = category;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.isBorrowable = isBorrowable;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.type = type;
	}
	
	//Constructors for Updating items
	public LibraryItem(Integer id, String title, String author,Category category, Integer pages, boolean isBorrowable, String type) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.isBorrowable = isBorrowable;
		this.type = type;
	}
	
	public LibraryItem(Integer id, String title, String author,Category category, Integer pages, boolean isBorrowable, String borrower, Date borrowDate, String type) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.isBorrowable = isBorrowable;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.type = type;
	}
	
	//The normal constructor
	public LibraryItem(Integer id, Category category, String title, String author, Integer pages, Integer runTimeMinutes,
			boolean isBorrowable, String borrower, Date borrowDate, String type) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.runTimeMinutes = runTimeMinutes;
		this.isBorrowable = isBorrowable;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.type = type;
	}
	



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getRunTimeMinutes() {
		return runTimeMinutes;
	}
	public void setRunTimeMinutes(Integer runTimeMinutes) {
		this.runTimeMinutes = runTimeMinutes;
	}
	public boolean getIsBorrowable() {
		return isBorrowable;
	}
	public void setIsBorrowable(boolean isBorrowable) {
		this.isBorrowable = isBorrowable;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
