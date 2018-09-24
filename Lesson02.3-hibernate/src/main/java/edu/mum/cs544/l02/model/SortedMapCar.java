package edu.mum.cs544.l02.model;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Entity
public class SortedMapCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	@OrderBy
	private SortedMap<String, String> drivers = new TreeMap<String, String>();
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
	public SortedMap<String, String> getDrivers() {
		return drivers;
	}
	public void setDrivers(SortedMap<String, String> drivers) {
		this.drivers = drivers;
	}
	
}
