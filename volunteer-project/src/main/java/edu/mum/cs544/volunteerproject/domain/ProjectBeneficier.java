package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="projectBeneficerSeq", sequenceName="PROJECT_BENEFICER_SEQUENCE", allocationSize=50)
public class ProjectBeneficier {
	@Id @GeneratedValue(generator = "projectBeneficerSeq", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	public ProjectBeneficier() {
		super();
	}

	public ProjectBeneficier(Person person, Project project) {
		super();
		this.person = person;
		this.project = project;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
