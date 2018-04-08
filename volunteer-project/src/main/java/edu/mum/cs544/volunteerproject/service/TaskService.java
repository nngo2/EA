package edu.mum.cs544.volunteerproject.service;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.TaskDao;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class TaskService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private TaskDao taskDao = new TaskDao();
	
	public void create(Project project, Task task) {	
		if (task == null || project == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		taskDao.create(project, task);
		entityManager.getTransaction().commit();
	}
	
	public void update(Task task) {
		if (task == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		taskDao.update(task);
		entityManager.getTransaction().commit();		
	}
	
	public void delete(Task task) {
		if (task == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		taskDao.delete(task);
		entityManager.getTransaction().commit();
	}
	
	public Task findOne(int id) {
		return taskDao.findOne(id);
	}
	
	public List<Task> findByName(String name) {
		return taskDao.findByName(name);
	}
	
	public List<Task> findByProject(Project project) {
		if (project == null) {
			throw new IllegalArgumentException();
		}
		return taskDao.findByProject(project);
	}
}
