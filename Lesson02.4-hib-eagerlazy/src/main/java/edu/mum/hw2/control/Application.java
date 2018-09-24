package edu.mum.hw2.control;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.mum.hw2.domain.Artist;
import edu.mum.hw2.domain.Movie;

public class Application {

	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		addMovies();

		
		printMoviesReport();
		sessionFactory.close();
	}

	private static void printMoviesReport() {
		// TODO Auto-generated method stub
		
	}

	private static void addMovies() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			
			//
			// Transaction 1 Persist
			//
			System.out.println("======== TRANSACTION 1 =======");
			tx.begin();

			Movie movie1 = new Movie();
			movie1.setTitle("Mr. & Mrs. Smith");
			movie1.getArtists().add(new Artist("Brad Pitt"));
			movie1.getArtists().add(new Artist("Angelina Jolie"));

			System.out.println("Movie 1[id](before persist GeneratedValue): "+movie1.getId());
			session.persist(movie1);
			System.out.println("Movie 1[id](after persist GeneratedValue): "+movie1.getId());

			Movie movie2 = new Movie();
			movie2.setTitle("Meet Joe Black");
			movie2.getArtists().add(new Artist("Brad Pitt"));

			System.out.println("Movie 2[id](before persist GeneratedValue): "+movie2.getId());
			session.persist(movie2);
			System.out.println("Movie 2[id](after persist GeneratedValue): "+movie2.getId());

			System.out.println("======== CALLING COMMIT TRANSACTION 1 =======");
			tx.commit();
			
			System.out.println("Movie 1[id](after commit): "+movie1.getId());
			System.out.println("Movie 2[id](after commit): "+movie2.getId());
			
			//
			// Transaction 2 Save
			//
			System.out.println("======== TRANSACTION 2 =======");
			tx = session.getTransaction();
			tx.begin();
			{
			Movie movieB1 = new Movie();
			movieB1.setTitle("Big Game ");
			movieB1.getArtists().add(new Artist("Samuel L. Jakson"));

			System.out.println("Movie B1[id](before save GeneratedValue): "+movieB1.getId());
			session.save(movieB1);
			System.out.println("Movie B1[id](after save GeneratedValue): "+movieB1.getId());

			Movie movieB2 = new Movie();
			movieB2.setTitle("Jurasic Park");
			movieB2.getArtists().add(new Artist("Chris Pratt"));

			System.out.println("Movie 2B[id](before save GeneratedValue): "+movieB2.getId());
			session.save(movieB2);
			System.out.println("Movie 2B[id](after save GeneratedValue): "+movieB2.getId());

			System.out.println("======== CALLING COMMIT TRANSACTION 2 =======");
			tx.commit();
			
			System.out.println("Movie 1B[id](after commit): "+movieB1.getId());
			System.out.println("Movie 2B[id](after commit): "+movieB2.getId());
			
			session.clear();		

			//
			// Transaction 3 find vs getReference
			//
			System.out.println("======== TRANSACTION 3 =======");
			tx = session.getTransaction();
			tx.begin();

			System.out.println("Movie 3 GET NOT in context");
			Movie movie3 = (Movie) session.get(Movie.class, 1L);			
			System.out.println("Movie 3 name:"+ movie3.getTitle());

			System.out.println("Movie 4 GET already in context");
			Movie movie4 = (Movie) session.get(Movie.class, 1L);
			System.out.println("Movie 4 name:"+ movie4.getTitle());
			

			System.out.println("Movie 5 LOAD already in context");
			Movie movie5 = (Movie) session.load(Movie.class, 1L);
			System.out.println("Movie 5 name:"+ movie5.getTitle());

			System.out.println("Movie 6 LOAD NOT in context");
			Movie movie6 = (Movie) session.load(Movie.class, 2L);
			System.out.println("Movie 6 name:"+ movie6.getTitle());
			}
			System.out.println("======== CALLING COMMIT TRANSACTION 3 =======");
			tx.commit();					
			
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx != null)  tx.rollback();
		} finally {
			if ((session != null) && (session.isOpen())) session.close();
		}
	}

}
