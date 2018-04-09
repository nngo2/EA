package edu.mum.cs544.volunteerproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.domain.VolunteerTask;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class VolunteerDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(VolunteerTask volunteerTask) {	
		volunteerTask.getPerson().addVolunteerTask(volunteerTask);
		volunteerTask.getTask().addVolunteerTask(volunteerTask);
		entityManager.persist(volunteerTask);
	}
	
	public void delete(VolunteerTask volunteerTask) {
		volunteerTask.getTask().removeVolunteerTask(volunteerTask);
		volunteerTask.getPerson().removeVolunteerTask(volunteerTask);
		VolunteerTask removed = entityManager.find(VolunteerTask.class, volunteerTask.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public VolunteerTask findOne(int id) {
		return entityManager.find(VolunteerTask.class, id);
	}
	
	public VolunteerTask findByTaskAndVolunteer(Task task, Volunteer volunteer) {
		Query query = entityManager.createQuery("select v from VolunteerTask v "
				+ "where v.person.id = :volunteerId and v.task.id = :taskId");
		query.setParameter("volunteerId", volunteer.getId());
		query.setParameter("taskId", task.getId());
		
		return (VolunteerTask) query.getSingleResult();
	}
}
