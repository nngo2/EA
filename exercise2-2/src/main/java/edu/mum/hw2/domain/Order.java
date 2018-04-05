package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="MyOrder")
public class Order {
	@Id @GeneratedValue 
	private long orderId;
	
	@Temporal(value = TemporalType.DATE)
	private Date date;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderLine> orderLines = new ArrayList<>();

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<OrderLine> getOrderLines() {
		return Collections.unmodifiableList(orderLines);
	}

	public void addOrderLine(OrderLine orderLine) {
		orderLine.setOrder(this);
		this.orderLines.add(orderLine);
	}

	public void removeOrderLine(OrderLine orderLine) {
		this.orderLines.remove(orderLine);
		orderLine.setOrder(null);
	}

	public void clearOrderLines() {
		for (OrderLine orderline : orderLines) {
			orderline.setOrder(null);
		}
		orderLines.clear();
	}
}
