package cs544.exercise8_2;

import org.hibernate.Hibernate;

public class StudentService {

	private StudentDAO studentDao;
	
	public StudentService() {
		super();
	}

	public StudentService(StudentDAO studentDao) {
		super();
		this.studentDao = studentDao;
	}

	public Student getStudent(long studentid) {
		Student student = studentDao.load(studentid);
		if (student != null) {
			Hibernate.initialize(student.getCourselist());
		}
		return student;
	}
}
