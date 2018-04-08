package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="resourceSeq", sequenceName="RESOURCE_SEQUENCE", allocationSize=50)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Resource {
	@Id @GeneratedValue(generator = "resourceSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;

	public Resource() {
		super();
	}

	public Resource(String description) {
		super();
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
