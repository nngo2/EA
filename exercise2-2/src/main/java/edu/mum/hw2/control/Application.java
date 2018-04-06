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
import edu.mum.hw2.domain.CD;
import edu.mum.hw2.domain.DVD;
import edu.mum.hw2.domain.Order;
import edu.mum.hw2.domain.OrderLine;
import edu.mum.hw2.domain.Product;

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
		//createProducts();
		createSubProducts();
		addOrders();
		printOrderReport();
		printBook();
		emf.close();
	}

	private static void printOrderReport() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Order> orders = em.createQuery("select o from MyOrder o").getResultList();
			System.out.println("Print all orders: ");
			for (Order o : orders) {
				System.out.println("Date: " + o.getDate());
				System.out.println("-- Order lines: " + o.getOrderLines().stream().map(l -> l.toString()).collect(Collectors.joining(",")));
			}
			
			@SuppressWarnings("unchecked")
			List<Order> order2 = em.createQuery("select distinct o from MyOrder o "
					+ "join o.orderLines l "
					+ "join l.product p "
					+ "where p.genre like 'Fiction%'").getResultList();
			System.out.println("Filter orders by product names: ");
			for (Order o : order2) {
				System.out.println("Date: " + o.getDate());
				System.out.println("-- Order lines: " + o.getOrderLines().stream().map(l -> l.toString()).collect(Collectors.joining(",")));
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
	private static void printBook() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			@SuppressWarnings("unchecked")
			List<Book> books = em.createQuery("select p from Product p "
					+ "where type(p) = Book").getResultList();
			
			System.out.println("Print all books: ");
			for (Book o : books) {
				System.out.println("Book: " + o.toString());
			}
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
	
//	private static void createProducts() {
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		try {
//			tx.begin();
//			
//			Product p1 = new Product("Apple", "Big apple");
//			em.persist(p1);
//			Product p2 = new Product("Orrange", "Big orranage");
//			em.persist(p2);
//			
//			tx.commit();
//		} catch (Throwable e) {
//			if ((tx != null) && (tx.isActive())) tx.rollback();
//			e.printStackTrace();
//		} finally {
//			if ((em != null) && (em.isOpen())) em.close();
//		}
//	}
	
	private static void createSubProducts() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			CD p1 = new CD("Back Street Boys Top Hit", "Back Street Boys Top Hit", "Back Street Boys");
			em.persist(p1);
			CD p2 = new CD("Top 1980s Disco", "Top 1980s Disco", "Modern Talking");
			em.persist(p2);
			
			DVD p3  = new DVD("Harry Porter 1", "Harry Porter 1", "Fiction");
			em.persist(p3);
			DVD p4  = new DVD("Harry Porter 2", "Harry Porter 2", "Fiction");
			em.persist(p4);
			
			Book p5  = new Book("The last Jedi", "The last Jedi", "The last Jedi");
			em.persist(p5);
			
			Book p6  = new Book("The empire strikes back", "The empire strikes back", "The empire strikes back");
			em.persist(p6);
			
			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}

	private static void addOrders() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			@SuppressWarnings("unchecked")
			List<Product> products = em.createQuery("select p from Product p").getResultList();
			
			Order order0 = new Order();
			order0.setDate(df.parse("3/31/2018"));
			order0.addOrderLine(new OrderLine(2, products.get(0)));
			order0.addOrderLine(new OrderLine(2, products.get(1)));					
			em.persist(order0);
	
			Order order1 = new Order();
			order1.setDate(df.parse("4/1/2018"));
			order1.addOrderLine(new OrderLine(2, products.get(0)));
			order1.addOrderLine(new OrderLine(2, products.get(1)));		
			order1.addOrderLine(new OrderLine(2, products.get(2)));				
			em.persist(order1);
			
			Order order2 = new Order();
			order2.setDate(df.parse("4/2/2018"));
			order2.addOrderLine(new OrderLine(3, products.get(3)));
			order2.addOrderLine(new OrderLine(3, products.get(4)));		
			order2.addOrderLine(new OrderLine(3, products.get(5)));				
			em.persist(order2);

			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			e.printStackTrace();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}
}
