package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="officeSeq", sequenceName="OFFICE_SEQUENCE", allocationSize=50)
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "officeSeq")
	private int id;
	
	private String roomNumber;
	
	private String buidling;
	
	@OneToMany(mappedBy="office")
	private List<Employee> employees = new ArrayList<>();

	public Office() {
		super();
	}

	public Office(String roomNumber, String buidling) {
		super();
		this.roomNumber = roomNumber;
		this.buidling = buidling;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getBuidling() {
		return buidling;
	}

	public void setBuidling(String buidling) {
		this.buidling = buidling;
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public void addEmployee(Employee employee) {
		employee.setOffice(this);
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employee.setOffice(null);;
		employees.remove(employee);
	}
	
	@Override
	public String toString() {
		return "Office [id=" + id + ", roomNumber=" + roomNumber + ", buidling=" + buidling + "]";
	}

}
