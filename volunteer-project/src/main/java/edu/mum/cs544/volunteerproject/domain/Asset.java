package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Asset")
public class Asset extends Resource{
	private double cost;
	
	private String model;
	
	private String configuration;

	public Asset() {
		super();
	}
	
	public Asset(String description, double cost, String model, String configuration) {
		super(description);
		this.cost = cost;
		this.model = model;
		this.configuration = configuration;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
}
