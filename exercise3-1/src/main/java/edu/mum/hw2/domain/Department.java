package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="deptSeq", sequenceName="DEPARTMENT_SEQUENCE", allocationSize=50)
public class Department {
	@Id 
	@GeneratedValue(generator = "deptSeq", strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="department", fetch = FetchType.EAGER)
	private List<Employee> employees = new ArrayList<Employee>();

	public Department() {
		super();
	}

	public Department(String name) {
		super();
		this.name = name;
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
	
	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public void addEmployee(Employee employee) {
		employee.setDepartment(this);
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employee.setDepartment(null);;
		employees.remove(employee);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
