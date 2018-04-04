package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "studentSeq", sequenceName = "STUDENT_SEQUENCE", allocationSize = 50)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeq")
	private int studentId;

	private String firstName;

	private String lastName;

	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "student_id"), 
		inverseJoinColumns = @JoinColumn(name = "course_id"))
	@OrderBy(value="courseNumber")
	private List<Course> courses = new ArrayList<Course>();

	public Student() {
		super();
	}

	public Student(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	public void addCourse(Course course) {
		courses.add(course);
		if (!course.getStudents().contains(this)) {
			course.addStudent(this);
		}
	}

	public void removeCourse(Course course) {
		courses.remove(course);
		if (course.getStudents().contains(this)) {
			course.removeStudent(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}
}
