package edu.mum.cs544.l02.model;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Entity
public class SortedSetCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	@OrderBy
	private SortedSet<String> drivers = new TreeSet<String>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public SortedSet<String> getDrivers() {
		return drivers;
	}
	public void setDrivers(SortedSet<String> drivers) {
		this.drivers = drivers;
	}
	
}
