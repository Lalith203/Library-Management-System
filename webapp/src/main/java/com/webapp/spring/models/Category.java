package com.webapp.spring.models;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {


	/*
	 * @Id declares the entity identifier.

	@Column maps the entity's field with the table's column. If @Column is omitted, the field name of the entity will be used as column name by default.

	@OneToOne defines a one-to-one relationship between 2 entities.

	@JoinColumn defines foreign key column and indicates the owner of the relationship.

	mappedBy indicates the inverse of the relationship.

	unique = true enforces the unique constraint, 1 address belongs to only 1 library.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer id = 0;
	
	@Column(unique=true)
	private String categoryName;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<LibraryItem> libraryItems;
	
	public Category() {
		
	}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	public Category(Integer id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


}
