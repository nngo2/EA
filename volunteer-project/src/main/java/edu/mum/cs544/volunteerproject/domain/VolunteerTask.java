package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="volunteerTaskSeq", sequenceName="VOLUNTEER_TASK_SEQUENCE", allocationSize=50)
public class VolunteerTask {
	@Id @GeneratedValue(generator = "volunteerTaskSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	
	public VolunteerTask() {
		super();
	}

	public VolunteerTask(Person person, Task task) {
		super();
		this.person = person;
		this.task = task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
