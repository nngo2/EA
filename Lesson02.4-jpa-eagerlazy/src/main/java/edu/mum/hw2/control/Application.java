package edu.mum.hw2.control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.hw2.domain.Artist;
import edu.mum.hw2.domain.Movie;

public class Application {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		addMovies();

		
		printMoviesReport();
		emf.close();
	}

	private static void printMoviesReport() {
		// TODO Auto-generated method stub
		
	}

	private static void addMovies() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
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
			em.persist(movie1);
			System.out.println("Movie 1[id](after persist GeneratedValue): "+movie1.getId());

			Movie movie2 = new Movie();
			movie2.setTitle("Meet Joe Black");
			movie2.getArtists().add(new Artist("Brad Pitt"));

			System.out.println("Movie 2[id](before persist GeneratedValue): "+movie2.getId());
			em.persist(movie2);
			System.out.println("Movie 2[id](after persist GeneratedValue): "+movie2.getId());

			System.out.println("======== CALLING COMMIT TRANSACTION 1 =======");
			tx.commit();
			
			System.out.println("Movie 1[id](after commit): "+movie1.getId());
			System.out.println("Movie 2[id](after commit): "+movie2.getId());
			
			em.clear();		

			//
			// Transaction 2 find vs getReference
			//
			System.out.println("======== TRANSACTION 2 =======");
			tx.begin();

			System.out.println("Movie 3 find NOT in context");
			Movie movie3 = em.find(Movie.class, 1L);			
			System.out.println("Movie 3 name:"+ movie3.getTitle());

			System.out.println("Movie 4 find already in context");
			Movie movie4 = em.find(Movie.class, 1L);
			System.out.println("Movie 4 name:"+ movie4.getTitle());
			

			System.out.println("Movie 5 getReference already in context");
			Movie movie5 = em.getReference(Movie.class, 1L);
			System.out.println("Movie 5 name:"+ movie5.getTitle());

			System.out.println("Movie 6 getReference NOT in context");
			Movie movie6 = em.getReference(Movie.class, 2L);
			System.out.println("Movie 6 name:"+ movie6.getTitle());

			System.out.println("======== CALLING COMMIT TRANSACTION 2 =======");
			tx.commit();
						
		} catch (Throwable e) {
			e.printStackTrace();
			if ((tx != null) && (tx.isActive())) tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}

}
