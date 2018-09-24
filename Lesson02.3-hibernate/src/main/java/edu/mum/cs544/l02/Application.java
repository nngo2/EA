package edu.mum.cs544.l02;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.mum.cs544.l02.model.BagCar;
import edu.mum.cs544.l02.model.IndexedCar;
import edu.mum.cs544.l02.model.MapCar;
import edu.mum.cs544.l02.model.SetCar;
import edu.mum.cs544.l02.model.SortedMapCar;
import edu.mum.cs544.l02.model.SortedSetCar;

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

			// Create new instance of Car and set values to it

			// --- SET ---
			SetCar car1 = new SetCar();
			car1.setPlate("123ABC");
			car1.getDrivers().add("Joe Smith");
			car1.getDrivers().add("Jane Doe");
			car1.getDrivers().add("Joe Smith");

			SetCar car2 = new SetCar();
			car2.setPlate("567XYZ");
			car2.getDrivers().add("Karl Sagan");

			// --- BAG ---
			BagCar car3 = new BagCar();
			car3.setPlate("123ABC");
			car3.getDrivers().add("Joe Smith");
			car3.getDrivers().add("Jane Doe");
			car3.getDrivers().add("Joe Smith");

			BagCar car4 = new BagCar();
			car4.setPlate("567XYZ");
			car4.getDrivers().add("Karl Sagan");

			// --- MAP ---
			MapCar car5 = new MapCar();
			car5.setPlate("123ABC");
			car5.getDrivers().put("PILOT", "Joe Smith");
			car5.getDrivers().put("SHOTGUN", "Jane Doe");
			car5.getDrivers().put("COPILOT", "Val Draco");
			car5.getDrivers().put("PILOT", "Joe Smith");

			MapCar car6 = new MapCar();
			car6.setPlate("567XYZ");
			car6.getDrivers().put("PILOT", "Karl Sagan");

			// --- SORTED SET ---
			SortedSetCar car7 = new SortedSetCar();
			car7.setPlate("123ABC");
			car7.getDrivers().add("Joe Smith");
			car7.getDrivers().add("Jane Doe");
			car7.getDrivers().add("Joe Smith");

			SortedSetCar car8 = new SortedSetCar();
			car8.setPlate("567XYZ");
			car8.getDrivers().add("Karl Sagan");

			// --- SORTED MAP ---
			SortedMapCar car9 = new SortedMapCar();
			car9.setPlate("123ABC");
			car9.getDrivers().put("PILOT", "Joe Smith");
			car9.getDrivers().put("SHOTGUN", "Jane Doe");
			car9.getDrivers().put("COPILOT", "Val Draco");
			car9.getDrivers().put("PILOT", "Joe Smith");

			SortedMapCar car10 = new SortedMapCar();
			car10.setPlate("567XYZ");
			car10.getDrivers().put("PILOT", "Karl Sagan");

			// --- INDEXED LIST ---
			IndexedCar car11 = new IndexedCar();
			car11.setPlate("123ABC");
			car11.getDrivers().add("Joe Smith");
			car11.getDrivers().add("Jane Doe");
			car11.getDrivers().add("Joe Smith");

			IndexedCar car12 = new IndexedCar();
			car12.setPlate("567XYZ");
			car12.getDrivers().add("Karl Sagan");

			// --------------------------------------------------
		    // STORE OBJECTS
			// --------------------------------------------------
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			// save the car
			session.persist(car1);
			session.persist(car2);
			session.persist(car3);
			session.persist(car4);
			session.persist(car5);
			session.persist(car6);
			session.persist(car7);
			session.persist(car8);
			session.persist(car9);
			session.persist(car10);
			session.persist(car11);
			session.persist(car12);
			
			tx.commit();
			output("get ID from detached beans : " 
					+ "\n\tCar 1 : " + car1.getId()
					+ "\n\tCar 2 : " + car2.getId()
					+ "\n\tCar 3 : " + car3.getId()
					+ "\n\tCar 4 : " + car4.getId()
					+ "\n\tCar 5 : " + car5.getId()
					+ "\n\tCar 6 : " + car6.getId()
					+ "\n\tCar 7 : " + car7.getId()
					+ "\n\tCar 8 : " + car8.getId()
					+ "\n\tCar 9 : " + car9.getId()
					+ "\n\tCar 10 : " + car10.getId()
					+ "\n\tCar 11 : " + car10.getId()
					+ "\n\tCar 12 : " + car10.getId()
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
			List<SetCar> setCars = 
					session.createQuery("from SetCar").list();

			for (SetCar c : setCars) {
				String message ="Set Plate =" + c.getPlate();
				for (String driver: c.getDrivers()) {

					message += "\n\tdriver = " + driver;
				}
				output(message);
			}
			
			@SuppressWarnings("unchecked")
			List<BagCar> bagCars = 
					session.createQuery("from BagCar").list();

			for (BagCar c : bagCars) {
				String message ="Bag Plate =" + c.getPlate();
				for (String driver: c.getDrivers()) {

					message += "\n\tdriver = " + driver;
				}
				output(message);
			}

			@SuppressWarnings("unchecked")
			List<MapCar> mapCars = 
					session.createQuery("from MapCar").list();

			for (MapCar c : mapCars) {
				String message ="Map Plate =" + c.getPlate();
				for (Map.Entry<String, String> driver: c.getDrivers().entrySet()) {
					message += "\n\tdriver KEY = " + driver.getKey() + " VALUE = "+driver.getValue();
				}
				output(message);
			}
			
			@SuppressWarnings("unchecked")
			List<SortedSetCar> sortedSetCars = 
					session.createQuery("from SortedSetCar").list();

			for (SortedSetCar c : sortedSetCars) {
				String message ="Sorted Set Plate =" + c.getPlate();
				for (String driver: c.getDrivers()) {

					message += "\n\tdriver = " + driver;
				}
				output(message);
			}

			@SuppressWarnings("unchecked")
			List<SortedMapCar> sortedMapCars = 
					session.createQuery("from SortedMapCar").list();

			for (SortedMapCar c : sortedMapCars) {
				String message ="Sorted Map Plate =" + c.getPlate();
				for (Map.Entry<String, String> driver: c.getDrivers().entrySet()) {
					message += "\n\tdriver KEY = " + driver.getKey() + " VALUE = "+driver.getValue();
				}
				output(message);
			}

			@SuppressWarnings("unchecked")
			List<IndexedCar> indexedCars = 
					session.createQuery("from IndexedCar").list();

			for (IndexedCar c : indexedCars) {
				String message ="Indexed List Plate =" + c.getPlate();
				for (String driver: c.getDrivers()) {

					message += "\n\tdriver = " + driver;
				}
				output(message);
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
