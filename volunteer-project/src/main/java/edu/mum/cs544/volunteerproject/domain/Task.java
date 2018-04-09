package edu.mum.cs544.volunteerproject.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="taskSeq", sequenceName="TASK_SEQUENCE", allocationSize=50)
public class Task {
	@Id @GeneratedValue(generator = "taskSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private Status status = Status.NOT_STARTED;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	@OneToMany(mappedBy="task", fetch = FetchType.EAGER,  cascade = {CascadeType.REMOVE})
	private List<ResourceBooking> resourceBookings = new ArrayList<>();
	
	@OneToMany(mappedBy="task", cascade = {CascadeType.REMOVE})
	private List<VolunteerTask> volunteerTasks = new ArrayList<>();

	public Task() {
		super();
	}

	public Task(String name, String description, Date startDate, Date endDate, Status status) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<ResourceBooking> getResourceBookings() {
		return Collections.unmodifiableList(resourceBookings);
	}

	public void addResourceBooking(ResourceBooking resourceBooking) {
		resourceBooking.setTask(this);
		resourceBookings.add(resourceBooking);
	}

	public void removeResourceBooking(ResourceBooking resourceBooking) {
		resourceBooking.setTask(null);
		resourceBookings.remove(resourceBooking);
	}
	
	public List<VolunteerTask> getVolunteerTasks() {
		return Collections.unmodifiableList(volunteerTasks);
	}
	
	public void addVolunteerTask(VolunteerTask volunteerTask) {
		volunteerTask.setTask(this);
		volunteerTasks.add(volunteerTask);
	}

	public void removeVolunteerTask(VolunteerTask volunteerTask) {
		volunteerTask.setTask(null);
		volunteerTasks.remove(volunteerTask);
	}

}
