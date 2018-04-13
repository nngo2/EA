package cs544.exercise8;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Book {
	private int id;
	
	@NotBlank
	private String title;
	
	@Pattern(regexp="\\d{3}-\\d{10}")
	private String ISBN;
	
	@NotBlank
	private String author;
	
	@DecimalMin(value = "1.0")
	private double price;
	
	@Past
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date publishedDate;

	public Book() {
		super();
	}

	public Book(String title, String iSBN, String author, double price) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
	}
	
	public Book(String title, String iSBN, String author, double price, Date publishedDate) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}
