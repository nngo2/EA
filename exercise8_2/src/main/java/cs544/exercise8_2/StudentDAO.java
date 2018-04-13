package cs544.exercise8_2;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StudentDAO {
	private SessionFactory sessionFactory;
	
	public StudentDAO() {
		super();
	}

	public StudentDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	// CANNOT USE TRANSACTION HERE SINCE NO VIEW LOADED YET, OpenSessionInViewFilter has NO session yet!!!
	@PostConstruct
	//@Transactional(value=TxType.REQUIRED)
	private void createSampleData() {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
	
		Student student = new Student(11334, "Frank", "Brown");
		Course course1 = new Course(1101, "Java", "A");
		Course course2 = new Course(1102, "Math", "B-");
		student.addCourse(course1);
		student.addCourse(course2);

		session.persist(student);
		session.getTransaction().commit();;	
	}

	public Student load(long studentid) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, studentid);
	}
}
