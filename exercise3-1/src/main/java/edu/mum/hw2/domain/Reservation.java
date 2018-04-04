package edu.mum.hw2.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="reservationSeq", sequenceName="RESERVATION_SEQUENCE", allocationSize=50)
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationSeq")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	public Reservation() {
		super();
	}

	public Reservation(Date date) {
		super();
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", date=" + date + ", book=" + book + "]";
	}
}
