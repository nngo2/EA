package edu.mum.cs544.l02;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.mum.cs544.l02.model.Address;
import edu.mum.cs544.l02.model.Driver;
import edu.mum.cs544.l02.model.Page;
import edu.mum.cs544.l02.model.PageNote;
import edu.mum.cs544.l02.model.PageNotePk;
import edu.mum.cs544.l02.model.PagePk;
import edu.mum.cs544.l02.model.Person;
import edu.mum.cs544.l02.model.Rating;

public class Application {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();
			
			// This step will read hibernate.cfg.xml
			sessionFactory = configuration.configure().buildSessionFactory(); 

		} catch (Throwable ex) {
			System.err.println(ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		
		try {

			Address home = new Address();
			home.setStreet("100 Broadway");
			home.setCity("Fairfield");
			home.setState("IA");
			home.setZipCode(53433);
			
			// Create new instance of Person/Employee and set values to it
			
			Person person = new Person();
			person.setFirstname("George");
			person.setLastname("Washington");
			person.setShippingAddress(home);
			person.setBillingAddress(home);
			
			// Create new instance of Driver and set values to it
			Driver driver = new Driver();
			driver.setName("George Washington");
			driver.setRating(Rating.EXCELENT);
			driver.setHomeAddress(home);

			// --------------------------------------------------
			// --------------------------------------------------
			// Create Page		    
		    PagePk pageKey = new PagePk();
		    pageKey.setBookId(1);
		    pageKey.setPageId(1);
		    
		    Page page = new Page();
		    page.setPageKey(pageKey);
		    page.setBody("The first page");

			// Create Note
		    PageNotePk noteKey = new PageNotePk();
		    noteKey.setPageKey(pageKey);
		    noteKey.setNoteId(1);
		    
		    PageNote note = new PageNote();
		    note.setNoteKey(noteKey);
		    note.setNote("Some comment here");
		    
		    // assign the note
		    page.getPageNotes().add(note);
		    
			// --------------------------------------------------
		    // STORE OBJECTS
			// --------------------------------------------------
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			// save the employee
			session.persist(person);
			// save the driver
			session.persist(driver);
			// save the pages+notes
			session.persist(page);
			session.persist(note);
			
			tx.commit();
			output("get ID from detached beans : " 
					+ "\n\tPerson :" + person.getId()
					+ "\n\tDriver :" + driver.getId()
					+ "\n\tPage   :" + page.getPageKey()
					);

		} catch (HibernateException e) {
			System.err.println(e);
			if (tx != null) tx.rollback();
		} finally {
			if (session != null) session.close();
		}
		
		// ANOTHER SESSION
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// retrieve all
			@SuppressWarnings("unchecked")
			List<Person> persons = 
				session.createQuery("from Employee e order by e.lastname desc").list();

			for (Person p : persons) {
				output("First name=" + p.getFirstname()
						+ ", Last name= " + p.getLastname());
			}

			tx.commit();

		} catch (HibernateException e) {
			System.err.println(e);
			if (tx != null)  tx.rollback();
		} finally {
			if (session != null) session.close();
		}
		
		if (!sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}

	private static void output(String output) {
		System.out.println("================= OUTPUT =================");
		System.out.println(output);		
		System.out.println("==========================================");
	}
}
