package edu.mum.cs544.volunteerproject.service;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.ProjectDao;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ProjectService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private ProjectDao projectDao = new ProjectDao();
	
	public void create(Project project) {
		if (project == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		projectDao.create(project);
		entityManager.getTransaction().commit();
	}
	
	public void update(Project project) {
		if (project == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		projectDao.update(project);
		entityManager.getTransaction().commit();
	}
	
	public void delete(Project project) {
		if (project == null) {
			throw new IllegalArgumentException();
		}
		Project removed = entityManager.find(Project.class, project.getId());
		if (removed != null) {
			entityManager.getTransaction().begin();
			projectDao.delete(project);
			entityManager.getTransaction().commit();
		}
	}
	
	public Project findOne(int id) {
		return projectDao.findOne(id);
	}
	
	public List<Project> findAll() {
		return projectDao.findAll();
	}
	
	public List<Project> findByLocation(String location) {
		return projectDao.findByLocation(location);
	}
	
	public List<Project> findByKeywords(String[] keywords) throws IllegalArgumentException {
		try {
			return projectDao.findByKeywords(keywords);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Project> findByName(String name) {
		return projectDao.findByName(name);
	}
	
	public List<Project> findByStatus(Status status) {
		return projectDao.findByStatus(status);
	}
}
