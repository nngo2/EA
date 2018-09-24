package edu.mum.cs544.l02.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class IndexedCar {
	@Id
	@GeneratedValue
	private int id;
	
	private String plate;
	@ElementCollection
	@OrderColumn(name = "Position")
	private List<String> drivers = new ArrayList<String>();
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
	public List<String> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<String> drivers) {
		this.drivers = drivers;
	}
	
}
