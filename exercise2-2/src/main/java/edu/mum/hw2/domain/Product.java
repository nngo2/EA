package edu.mum.hw2.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Product {
	@Id @GeneratedValue
	private long id;
	private String name;
	private String description;
	
	public Product() {
		super();
	}	
	public Product(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + "]";
	}
	
}
