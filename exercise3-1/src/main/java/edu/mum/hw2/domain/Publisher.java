package edu.mum.hw2.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="publishSeq", sequenceName="PUBLISHER_SEQUENCE", allocationSize=50)
public class Publisher {
	@Id @GeneratedValue(generator = "publishSeq", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Basic(optional=false)
	private String name;
	
	public Publisher() {
		super();
	}

	public Publisher(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + "]";
	}
	
}
