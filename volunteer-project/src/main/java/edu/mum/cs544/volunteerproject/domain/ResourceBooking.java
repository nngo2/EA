package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="resourceBookingSeq", sequenceName="RESOURCE_BOOKING_SEQUENCE", allocationSize=50)
public class ResourceBooking {
	@Id @GeneratedValue(generator = "resourceBookingSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private int quality;

	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="resoucre_id")
	private Resource resource;

	public ResourceBooking() {
		super();
	}

	public ResourceBooking(Task task, Resource resource, int quality) {
		super();
		this.task = task;
		this.resource = resource;
		this.quality = quality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
}
