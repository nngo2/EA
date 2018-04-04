package edu.mum.hw2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="empSeq", sequenceName="EMPLOYEE_SEQUENCE", allocationSize=50)
public class Employee {
	@Id 
	@GeneratedValue(generator = "empSeq", strategy = GenerationType.SEQUENCE)
	private int employeeNumber;
	
	private String name;
	
	@ManyToOne	
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne	
	@JoinColumn(name="office_id")
	private Office office;

	public Employee() {
		super();
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", name=" + name + ", office=" + office + "]";
	}
}
