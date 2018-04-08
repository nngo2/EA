package edu.mum.cs544.volunteerproject.service;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.ResourceBookingDao;
import edu.mum.cs544.volunteerproject.dao.TaskDao;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.ResourceBooking;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ResourceBookingService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private ResourceBookingDao resourceBookingDao = new ResourceBookingDao();
	
	public void create(Task task, ResourceBooking resourceBooking) {	
		if (task == null || resourceBooking == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceBookingDao.create(task, resourceBooking);
		entityManager.getTransaction().commit();
	}
	
	public void update(ResourceBooking resourceBooking) {
		if (resourceBooking == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceBookingDao.update(resourceBooking);
		entityManager.getTransaction().commit();		
	}
	
	public void delete(ResourceBooking resourceBooking) {
		if (resourceBooking == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceBookingDao.delete(resourceBooking);
		entityManager.getTransaction().commit();
	}
	
	public ResourceBooking findOne(int id) {
		return resourceBookingDao.findOne(id);
	}
}
