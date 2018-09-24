package edu.mum.cs544.l02.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PagePk implements Serializable{

	private static final long serialVersionUID = -398113836629604160L;

	private int bookId;
	private int pageId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + pageId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagePk other = (PagePk) obj;
		if (bookId != other.bookId)
			return false;
		if (pageId != other.pageId)
			return false;
		return true;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	@Override
	public String toString() {
		return "{book="+bookId+", page="+pageId+"}";
	}
	
}
