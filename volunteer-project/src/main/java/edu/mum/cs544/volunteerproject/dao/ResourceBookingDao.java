package edu.mum.cs544.volunteerproject.dao;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.domain.ResourceBooking;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ResourceBookingDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public void create(Task task, ResourceBooking resourceBooking) {	
		task.addResourceBooking(resourceBooking);
		entityManager.persist(resourceBooking);
	}
	
	public void update(ResourceBooking resourceBooking) {
		entityManager.merge(resourceBooking);
	}
	
	public void delete(ResourceBooking resourceBooking) {
		resourceBooking.getTask().removeResourceBooking(resourceBooking);
		ResourceBooking removed = entityManager.find(ResourceBooking.class, resourceBooking.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public ResourceBooking findOne(int id) {
		return entityManager.find(ResourceBooking.class, id);
	}
}
