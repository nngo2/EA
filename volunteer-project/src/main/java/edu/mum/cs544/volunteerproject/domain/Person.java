package edu.mum.cs544.volunteerproject.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="personSeq", sequenceName="PERSON_SEQUENCE", allocationSize=50)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="person_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Person {
	@Id @GeneratedValue(generator = "personSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@OneToMany(mappedBy="person", cascade = {CascadeType.REMOVE})
	private List<ProjectBeneficer> projectBeneficers = new ArrayList<>();
	
	@OneToMany(mappedBy="person", cascade = {CascadeType.REMOVE})
	private List<VolunteerTask> volunteerTasks = new ArrayList<>();
	
	public Person() {
		super();
	}

	public Person(String firstName, String lastName, Date dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public List<ProjectBeneficer> getProjectBeneficers() {
		return Collections.unmodifiableList(projectBeneficers);
	}
	
	public void addProjectBeneficer(ProjectBeneficer projectBeneficer) {
		projectBeneficer.setPerson(this);
		projectBeneficers.add(projectBeneficer);
	}

	public void removeProjectBeneficer(ProjectBeneficer projectBeneficer) {
		projectBeneficer.setPerson(null);
		projectBeneficers.remove(projectBeneficer);
	}
	
	public List<VolunteerTask> getVolunteerTasks() {
		return Collections.unmodifiableList(volunteerTasks);
	}
	
	public void addVolunteerTask(VolunteerTask volunteerTask) {
		volunteerTask.setPerson(this);
		volunteerTasks.add(volunteerTask);
	}

	public void removeVolunteerTask(VolunteerTask volunteerTask) {
		volunteerTask.setPerson(null);
		volunteerTasks.remove(volunteerTask);
	}
	
}
