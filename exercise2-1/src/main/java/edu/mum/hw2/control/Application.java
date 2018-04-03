package edu.mum.hw2.control;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public static void main(String[] args) {

		addMovies();
		printMoviesReport();
		emf.close();
	}

	private static void printMoviesReport() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Movie> movies = em.createQuery("select m from Movie m").getResultList();
			
			for (Movie m : movies) {
				System.out.println("Movie: " + m.getName());
				System.out.println("-- Actors: " + m.getArtists().stream().map(a -> a.getName()).collect(Collectors.joining(",")));
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}

	private static void addMovies() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			// sample image
			byte[] image = null;
			try {
				image = getImage();
			} catch(Exception ex) {
				
			}
			
			Movie movie = new Movie();
			movie.setName("Movie 1");
			movie.setRating(4);
			
			movie.getArtists().add(new Artist("Tom Cruise", 8, "Main actor"));
			movie.getArtists().add(new Artist("Julia Robert", 8, "Main actor 2"));
			
			movie.getComments().add("Comment 1");
			movie.getComments().add("Comment 2");
			
			movie.getCategories().add("Action");
			movie.getCategories().add("Drama");
			
			movie.setCover(image);
			
			em.persist(movie);
			
			Movie movie2 = new Movie();
			movie2.setName("Movie 2");
			movie2.setRating(4);
			
			movie2.getArtists().add(new Artist("Harison Ford", 8, "Main actor"));
			movie2.getArtists().add(new Artist("Naomi Rose", 8, "Main actor 2"));
			
			movie2.getComments().add("Comment 1");
			movie2.getComments().add("Comment 2");
			
			movie2.getCategories().add("Action");
			movie2.getCategories().add("Drama");
			
			movie2.setCover(image);
			
			em.persist(movie2);

			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static byte[] getImage() throws URISyntaxException, IOException {
		//Path path = FileSystems.getDefault().getPath("", "movie.jpg");
		Path path = Paths.get(ClassLoader.getSystemResource("movie.jpg").toURI());
		return Files.readAllBytes(path);
	}

}
