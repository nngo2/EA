package edu.mum.cs544.l02;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.mum.cs544.l02.model.Box;
import edu.mum.cs544.l02.model.Person;
import edu.mum.cs544.l02.model.concrete.TpcBook;
import edu.mum.cs544.l02.model.concrete.TpcCompactDisk;
import edu.mum.cs544.l02.model.concrete.TpcProduct;
import edu.mum.cs544.l02.model.joined.JtBook;
import edu.mum.cs544.l02.model.joined.JtCompactDisk;
import edu.mum.cs544.l02.model.joined.JtProduct;
import edu.mum.cs544.l02.model.single.StBook;
import edu.mum.cs544.l02.model.single.StCompactDisk;
import edu.mum.cs544.l02.model.single.StProduct;

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

			// Create new instance of Person and set values to it
			Person person = new Person("George", "Washington");
			
			// Create new Box
			Box box = new Box(10, 12, 6);
			
			// CREATE PRODUCTS

			// -- Single Table Per Hierarchy
			// Book
			StBook stBook = new StBook();
			stBook.setName("Lord of the Rings");
			stBook.setDescription("Best Seller");
			stBook.setISBN("123454321");
			stBook.setPrice(15.00);
			
			// CD
			StCompactDisk stCompactDisk = new StCompactDisk();
			stCompactDisk.setName("White");
			stCompactDisk.setDescription("White Album");
			stCompactDisk.setPrice(35.00);
			stCompactDisk.setArtist("The Beatles");
			
			// -- Joined Tables
			// Book
			JtBook jtBook = new JtBook();
			jtBook.setName("Harry Potter");
			jtBook.setDescription("Best Seller");
			jtBook.setISBN("103050300");
			jtBook.setPrice(10.00);
			
			// CD
			JtCompactDisk jtCompactDisk = new JtCompactDisk();
			jtCompactDisk.setName("19");
			jtCompactDisk.setDescription("Great songs");
			jtCompactDisk.setPrice(20.00);
			jtCompactDisk.setArtist("Taylor Swift");

			// -- Table Per Concrete Class
			// Book
			TpcBook tpcBook = new TpcBook();
			tpcBook.setName("The Hobbit");
			tpcBook.setDescription("Before the Lord of the Rings");
			tpcBook.setISBN("103050301");
			tpcBook.setPrice(18.00);
			
			// CD
			TpcCompactDisk tpcCompactDisk = new TpcCompactDisk();
			tpcCompactDisk.setName("Boston");
			tpcCompactDisk.setDescription("Classic Rock");
			tpcCompactDisk.setPrice(25.00);
			tpcCompactDisk.setArtist("Boston");

			// --------------------------------------------------
		    // STORE OBJECTS
			// --------------------------------------------------
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			// save the person
			session.persist(person);
			// save the box
			session.persist(box);
			// save products
			session.persist(stBook);
			session.persist(stCompactDisk);
			session.persist(jtBook);
			session.persist(jtCompactDisk);
			session.persist(tpcBook);
			session.persist(tpcCompactDisk);			
			
			tx.commit();
			output("get ID from detached beans : " 
					+ "\n\tPerson : " + person.getId()
					+ "\n\tBox    : " + box.getId()
					+ "\n\tSingle Table Book: " + stBook.getProductId()
					+ "\n\tSingle Table CD  : " + stCompactDisk.getProductId()
					+ "\n\tJoined Tables Book: " + jtBook.getProductId()
					+ "\n\tJoined Tables CD  : " + jtCompactDisk.getProductId()
					+ "\n\tConcrete Classes Book: " + tpcBook.getProductId()
					+ "\n\tConcrete Classes CD  : " + tpcCompactDisk.getProductId()
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
			List<StProduct> st = 
					session.createQuery("from StProduct").list();

			for (StProduct p : st) {
					output("Product ID=" + p.getProductId()
							+ ", Product name= " + p.getName());
			}

			@SuppressWarnings("unchecked")
			List<JtProduct> jt = 
					session.createQuery("from JtProduct").list();

			for (JtProduct p : jt) {
					output("Product ID=" + p.getProductId()
							+ ", Product name= " + p.getName());
			}

			@SuppressWarnings("unchecked")
			List<TpcProduct> tpc = 
				session.createQuery("from TpcProduct").list();

			for (TpcProduct p : tpc) {
				output("Product ID=" + p.getProductId()
						+ ", Product name= " + p.getName());
			}

			output("Only TpcBooks");
			@SuppressWarnings("unchecked")
			List<TpcBook> books = 
					session.createQuery("from TpcBook").list();

				for (TpcBook p : books) {
					output("Product ID=" + p.getProductId()
							+ ", Product name= " + p.getName());
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
