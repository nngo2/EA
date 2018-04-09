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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="projectSeq", sequenceName="PROJECT_SEQUENCE", allocationSize=50)
public class Project {
	@Id @GeneratedValue(generator = "projectSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	
	@Lob
	private Character[] description;
	
	private String location;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private Status status = Status.NOT_STARTED;
	
	@OneToMany(mappedBy="project", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
	private List<Task> tasks = new ArrayList<>();
	
	@OneToMany(mappedBy="project", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
	private List<ProjectBeneficier> projectBeneficers = new ArrayList<>();
	
	public Project() {
		super();
	}

	public Project(String name, String location, Date startDate, Date endDate, Status status) {
		super();
		this.name = name;
		this.location = location;
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

	public Character[] getDescription() {
		return description;
	}

	public void setDescription(Character[] description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
	
	public List<Task> getTasks() {
		return Collections.unmodifiableList(tasks);
	}

	public void addTask(Task task) {
		task.setProject(this);
		tasks.add(task);
	}

	public void removeTask(Task task) {
		task.setProject(null);
		tasks.remove(task);
	}
	
	public List<ProjectBeneficier> getProjectBeneficers() {
		return Collections.unmodifiableList(projectBeneficers);
	}
	
	public void addProjectBeneficer(ProjectBeneficier projectBeneficer) {
		projectBeneficer.setProject(this);
		projectBeneficers.add(projectBeneficer);
	}

	public void removeProjectBeneficer(ProjectBeneficier projectBeneficer) {
		projectBeneficer.setProject(null);
		projectBeneficers.remove(projectBeneficer);
	}

}
