package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="courseSeq", sequenceName="COURSE_SEQUENCE", allocationSize=50)
public class Course {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSeq")
	private int id;
	
	private String courseNumber;
	
	private String name;
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<Student>();
	
	public Course() {
		super();
	}

	public Course(String courseNumber, String name) {
		super();
		this.courseNumber = courseNumber;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseNumber=" + courseNumber + ", name=" + name + "]";
	}
	
	public List<Student> getStudents() {
		return Collections.unmodifiableList(students);
	}

	public void addStudent(Student student) {
		students.add(student);
		if (!student.getCourses().contains(this)) {
			student.addCourse(this);
		}
	}

	public void removeStudent(Student student) {
		students.remove(student);
		if (student.getCourses().contains(this)) {
			student.removeCourse(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Course other = (Course) obj;
		if (courseNumber == null) {
			if (other.courseNumber != null)
				return false;
		} else if (!courseNumber.equals(other.courseNumber))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
