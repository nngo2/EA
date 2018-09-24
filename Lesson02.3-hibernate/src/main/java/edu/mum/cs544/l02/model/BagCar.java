package edu.mum.cs544.l02.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BagCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	private Collection<String> drivers = new ArrayList<String>();
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
	public Collection<String> getDrivers() {
		return drivers;
	}
	public void setDrivers(Collection<String> drivers) {
		this.drivers = drivers;
	}
	
}
