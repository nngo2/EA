package edu.mum.hw2.control;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.hw2.domain.Book;
import edu.mum.hw2.domain.Course;
import edu.mum.hw2.domain.Customer;
import edu.mum.hw2.domain.Department;
import edu.mum.hw2.domain.Employee;
import edu.mum.hw2.domain.Office;
import edu.mum.hw2.domain.Publisher;
import edu.mum.hw2.domain.Reservation;
import edu.mum.hw2.domain.Student;

public class Application {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		// one2many bi
		create1();
		print1();
	
		// many2one uni
		create2();
		print2();
		
		// many2many bidirection
		create3();
		print3();
		remove3();
		print3();

		// one2many unidirection
		create4();
		print4();
		emf.close();
	}

	private static void print1() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Department> depts = em.createQuery("select d from Department d").getResultList();
			
			for (Department o : depts) {
				System.out.println("Date: " + o.getName());
				System.out.println("-- Employees: " + o.getEmployees().stream().map(s -> s.toString()).collect(Collectors.joining(",")));
			}
			
			@SuppressWarnings("unchecked")
			List<Office> offcies = em.createQuery("select o from Office o").getResultList();
			
			for (Office o : offcies) {
				System.out.println("Office: " + o.toString());
				System.out.println("-- Employees: " + o.getEmployees().stream().map(s -> s.toString()).collect(Collectors.joining(",")));
			}		
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void print2() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Book> books = em.createQuery("select b from Book b").getResultList();
			
			for (Book o : books) {
				System.out.println("Date: " + o.toString());
				System.out.println("-- Publisher: " + o.getPublisher().toString());
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void print3() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Student> students = em.createQuery("select distinct s from Student s left join fetch s.courses").getResultList();
			
			for (Student o : students) {
				System.out.println("Student: " + o.toString());
				System.out.println("-- Courses: " + o.getCourses().stream().map(s -> s.toString()).collect(Collectors.joining(",")));
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void print4() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Customer> customers = em.createQuery("select distinct c from Customer c").getResultList();
			
			for (Customer o : customers) {
				System.out.println("Customer: " + o.toString());
				System.out.println("-- Reservations: " + o.getReservations().stream().map(s -> s.toString()).collect(Collectors.joining(",")));
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	/*
	 * For both problem a & f
	 */
	private static void create1() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Office o1 = new Office("A111", "1400");
			em.persist(o1);
			Office o2 = new Office("B200", "1200");
			em.persist(o2);
			
			Employee e1 = new Employee("Jonathan Chris");
			o1.addEmployee(e1);
			
			Employee e2 = new Employee("John Chris");	
			o1.addEmployee(e2);
			
			Department d1 = new Department("IT");
			d1.addEmployee(e1);
			d1.addEmployee(e2);			
			
			em.persist(d1);
			em.persist(e1);
			em.persist(e2);
			
			Employee e3 = new Employee("Jonathan Chris II");
			o2.addEmployee(e3);
			
			Employee e4 = new Employee("John Chris II");		
			o2.addEmployee(e4);
			
			Department d2 = new Department("Account");
			d2.addEmployee(e3);
			d2.addEmployee(e4);
			
			em.persist(d2);
			em.persist(e3);
			em.persist(e4);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void create2() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Publisher p1 = new Publisher("Manson");
			em.persist(p1);
			
			Book b1 = new Book("11111111ISBN", "Book 1", "Conan Doyle");
			//b1.setAuthor(null);
			b1.setPublisher(p1);
			em.persist(b1);
			
			Book b2 = new Book("11111122ISBN", "Book 2", "Dr Watson");		
			b2.setPublisher(p1);
			em.persist(b2);

			Publisher p2 = new Publisher("Pearson");
			em.persist(p2);
			
			Book b3 = new Book("11111133ISBN", "Book 3", "Poor Dad Rich Dad");
			b3.setPublisher(p2);
			em.persist(b3);
			
			Book b4 = new Book("11111144ISBN", "Book 4", "Mar vs Moon");		
			b4.setPublisher(p2);
			em.persist(b4);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void create3() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Course c1 = new Course("cs554" , "Enterprise Architecture");
			Course c2 = new Course("cs545" , "Web Application Architecture");			
			Student s1 = new Student("John", "Smith");
			Student s2 = new Student("Adam", "Smith");			
			// uncomment those lines will duplicate the relationship!!!
//			c1.addStudent(s1);
//			c1.addStudent(s2);			
//			c2.addStudent(s1);
//			c2.addStudent(s2);
			s1.addCourse(c1);
			s1.addCourse(c2);
			s2.addCourse(c1);
			s2.addCourse(c2);
			
			em.persist(c1);
			em.persist(c2);
			em.persist(s1);
			em.persist(s2);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}	
	
	private static void remove3() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
	
			Student student = em.find(Student.class , 1); 
			List<Course> courses = (List<Course>) student.getCourses();
			student.removeCourse(courses.get(0));
			student.removeCourse(courses.get(0));
			em.merge(student);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}	

	/*
	 *  cover both problem d & e
	 */
	private static void create4() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Book> books = em.createQuery("select b from Book b").getResultList();
			
			Customer c = new Customer("John Smith"); 
			Reservation r1 = new Reservation(df.parse("01/01/2018"));
			r1.setBook(books.get(0));
			//em.persist(r1);
			c.getReservations().add(r1);
			
			Reservation r2 = new Reservation(df.parse("02/01/2018"));
			r2.setBook(books.get(0));			
			//em.persist(r2);			
			c.getReservations().add(r2);
			em.persist(c);
			
			Customer c2 = new Customer("John Doe"); 
			Reservation r3 = new Reservation(df.parse("04/01/2018"));
			r3.setBook(books.get(0));			
			//em.persist(r3);
			c2.getReservations().add(r3);
			
			Reservation r4 = new Reservation(df.parse("03/01/2018"));
			r4.setBook(books.get(0));			
			//em.persist(r4);
			c2.getReservations().add(r4);
			em.persist(c2);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}

}
