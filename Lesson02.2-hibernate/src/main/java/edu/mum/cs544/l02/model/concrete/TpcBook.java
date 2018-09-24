package edu.mum.cs544.l02.model.concrete;

import javax.persistence.Entity;

@Entity
public class TpcBook extends TpcProduct {
	private String ISBN;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
}
