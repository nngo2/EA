package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Asset")
public class Asset extends Resource{
	private Double cost;
	
	private String model;
	
	private String configuration;
	
	private AssetType assetType;

	public Asset() {
		super();
	}
	
	public Asset(String description, Double cost, String model, String configuration) {
		super(description);
		this.cost = cost;
		this.model = model;
		this.configuration = configuration;
	}

	public Asset(String description, Double cost, String model, String configuration, AssetType assetType) {
		super();
		this.cost = cost;
		this.model = model;
		this.configuration = configuration;
		this.assetType = assetType;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
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

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
}
