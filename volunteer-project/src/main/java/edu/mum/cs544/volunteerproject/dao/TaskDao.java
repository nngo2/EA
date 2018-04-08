package edu.mum.cs544.volunteerproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class TaskDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public void create(Project project, Task task) {	
		project.addTask(task);
		entityManager.persist(task);
	}
	
	public void update(Task task) {
		entityManager.merge(task);
	}
	
	public void delete(Task task) {
		task.getProject().removeTask(task);
		Task removed = entityManager.find(Task.class, task.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public Task findOne(int id) {
		return entityManager.find(Task.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> findByName(String name) {
		Query q = entityManager.createQuery("select t from Task t "
				+ "join t.project p "
				+ "left join t.resources r "
				+ "where t.name like :name", Task.class);
		q.setParameter("name", "%" + name + "%");
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> findByProject(Project project) {
		Query q = entityManager.createQuery("select t from Task t "
				+ "join t.project p "
				+ "left join t.resources r "
				+ "where t.project.id = :projectId", Task.class);
		q.setParameter("projectId", project.getId());
		
		return q.getResultList();
	}
}
