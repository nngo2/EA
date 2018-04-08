package edu.mum.cs544.volunteerproject.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs544");
	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

	public static EntityManager getEntityManager() {
		EntityManager entityManager = threadLocal.get();
		
		if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
            threadLocal.set(entityManager);
        }
		
		return entityManager;
	}

	public static void releaseResources() {
		if (emf != null) {
			emf.close();
		}
	}
}