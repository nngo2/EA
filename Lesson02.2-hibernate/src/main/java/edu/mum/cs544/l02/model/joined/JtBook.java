package edu.mum.cs544.l02.model.joined;

import javax.persistence.Entity;

@Entity
public class JtBook extends JtProduct {
	private String ISBN;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
}
