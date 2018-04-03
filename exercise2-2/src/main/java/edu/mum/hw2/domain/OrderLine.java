package edu.mum.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderLine {
	@Id @GeneratedValue
	private long lineId;
	
	private int quality;
	
	@ManyToOne
	private Order order;
	
	@OneToOne
	private Product product;

	public OrderLine() {
		super();
	}

	public OrderLine(int quality, Product product) {
		super();
		this.quality = quality;
		this.product = product;
	}

	public long getLineId() {
		return lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderLine [quality=" + quality + ", product=" + product + "]";
	}

}
