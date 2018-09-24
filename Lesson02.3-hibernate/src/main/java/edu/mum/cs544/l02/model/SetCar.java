package edu.mum.cs544.l02.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SetCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	private Set<String> drivers = new HashSet<String>();
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
	public Set<String> getDrivers() {
		return drivers;
	}
	public void setDrivers(Set<String> drivers) {
		this.drivers = drivers;
	}
	
}
