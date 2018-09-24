package edu.mum.cs544.l02.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MapCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	private Map<String, String> drivers = new HashMap<String, String>();
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
	public Map<String, String> getDrivers() {
		return drivers;
	}
	public void setDrivers(Map<String, String> drivers) {
		this.drivers = drivers;
	}
	
}
