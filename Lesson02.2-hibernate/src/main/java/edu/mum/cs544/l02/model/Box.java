package edu.mum.cs544.l02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

//===========================================================
// Notice that setters must be public
// Additional methods must be set to Transient
//============================================================

@Entity
public class Box {
	
	private int id;
	private int height;
	private int width;
	private int depth;
	
	Box () {
		// default constructor is required by Hibernate 
		// It is set to private so it is NOT ACCESSIBLE outside the package				
	}
	
	public Box(int height, int width, int depth) {
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	@Id @GeneratedValue 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Transient
	public long getVolume() {
		return height * width * depth; 
	}

}
