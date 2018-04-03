package edu.mum.hw2.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Product {
	private String title;

	public Book() {
		super();
	}

	public Book(String name, String description, String title) {
		super(name, description);
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + "]";
	}
}
