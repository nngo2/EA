package cs544.lab04_1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import cs544.lab04_1.model.Airline;
import cs544.lab04_1.model.Airplane;
import cs544.lab04_1.model.Airport;
import cs544.lab04_1.model.Flight;

public class App {
	private static Logger logger = Logger.getLogger(App.class);;

	private static final EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		EntityManager em = null;
		EntityTransaction tx = null;

		// fill the database
		fillDataBase();

		// prob-a: Flights leaving USA capacity > 500
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			@SuppressWarnings("unchecked")
			List<Flight> flights = em.createQuery(
					"select distinct f from Flight f "
					+ "join f.airplane a "
					+ "join f.origin o "
					+ "join f.destination d "
					+ "where a.capacity > 500 and o.country = 'USA' and d.country != 'USA'").getResultList();
			logger.trace("List all the flights - Flights leaving USA capacity > 500:");
			logger.trace(String.format("%-9s%-31s%-31s", "Flight:", "Departs:", "Arrives:"));
			for (Flight flight : flights) {
				logger.trace(String.format("%-7s %-12s %16s %7s %8s %-12s %16s %7s %8s", flight.getFlightnr(),
						flight.getOrigin().getCity(), flight.getOrigin().getCountry(), flight.getDepartureDate(), flight.getDepartureTime(),
						flight.getDestination().getCity(), flight.getDestination().getCountry(), flight.getArrivalDate(), flight.getArrivalTime()));
			}
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				logger.error("Rolling back:", e);
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		// prob-b: All airlines that use A380 (model) airplanes
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			@SuppressWarnings("unchecked")
			List<Airline> airlines = em.createQuery(
					"select distinct a from Airline a "
					+ "join a.flights f "
					+ "join f.airplane p "
					+ "where p.model ='A380'").getResultList();
			logger.trace(String.format("%s", "All airlines that use A380 (model) airplanes"));
			for (Airline airline : airlines) {
				logger.trace(String.format("%s", airline.getName()));
			}
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				logger.error("Rolling back:", e);
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		// prob-c: All fights using 747 planes that don't belong to 'Star Alliance'
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			@SuppressWarnings("unchecked")
			List<Flight> flights = em.createQuery(
					"select distinct f from Flight f "
					+ "join f.airplane p "
					+ "join f.airline a "
					+ "where p.model = '747' and a.name != 'Star Alliance'").getResultList();
			logger.trace("List all the flights -  All fights using 747 planes that don't belong to 'Star Alliance':");			
			logger.trace(String.format("%-31s%-31s%-31s", "Flight:", "Departs:", "Arrives:"));
			for (Flight flight : flights) {
				logger.trace(String.format("%16s %6s %-7s %-12s %16s %7s %8s %-12s %16s %7s %8s", 
						flight.getAirline().getName(), flight.getAirplane().getModel(), flight.getFlightnr(),
						flight.getOrigin().getCity(), flight.getOrigin().getCountry(), flight.getDepartureDate(), flight.getDepartureTime(),
						flight.getDestination().getCity(), flight.getDestination().getCountry(), flight.getArrivalDate(), flight.getArrivalTime()));
			}
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				logger.error("Rolling back:", e);
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		// prob-d: All flights leaving before 12pm on 08/07/2009 
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			@SuppressWarnings("unchecked")
			List<Flight> flights = em.createQuery(
					"select f from Flight f "
					+ "where f.departureDate = '2009-08-07' and f.departureTime < '12:00:00' ").getResultList();
			logger.trace("List all the flights - All flights leaving before 12pm on 08/07/2009 :");			
			logger.trace(String.format("%-9s%-31s%-31s", "Flight:", "Departs:", "Arrives:"));
			for (Flight flight : flights) {
				logger.trace(String.format("%-7s %-12s %16s %7s %8s %-12s %16s %7s %8s", flight.getFlightnr(),
						flight.getOrigin().getCity(), flight.getOrigin().getCountry(), flight.getDepartureDate(), flight.getDepartureTime(),
						flight.getDestination().getCity(), flight.getDestination().getCountry(), flight.getArrivalDate(), flight.getArrivalTime()));
			}
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				logger.error("Rolling back:", e);
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public static void fillDataBase() {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {

			Airport mco = new Airport("MCO", "Orlando", "Florida", "USA");
			Airport mia = new Airport("MIA", "Miami", "Florida", "USA");

			Airplane airplane1 = new Airplane("56789", "A380", 519);

			Airline airline1 = new Airline("Delta");

			Flight flight1 = new Flight("DE 36", "08/06/2009", "7:10 pm", "06/25/2015", "9:00 am", airline1, mia, mco,
					airplane1);

			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.persist(flight1);

			tx.commit();

		} catch (PersistenceException e) {
			if (tx != null) {
				logger.error("Rolling back", e);
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
