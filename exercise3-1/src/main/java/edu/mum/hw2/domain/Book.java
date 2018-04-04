package edu.mum.hw2.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="bookSeq", sequenceName="BOOK_SEQUENCE", allocationSize=50)
public class Book {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
	private int id;
	
	@Basic(optional = false)
	private String isbn;

	@Basic(optional = false)
	private String title;
	
	@Basic(optional = false)
	private String author;
	
	@ManyToOne
	@JoinColumn(name="publisher_id")
	private Publisher publisher;

	public Book() {
		super();
	}

	public Book(String isbn, String title, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
	}

}
