package edu.mum.cs544.volunteerproject.service;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.BeneficierDao;
import edu.mum.cs544.volunteerproject.dao.ProjectDao;
import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.ProjectBeneficier;
import edu.mum.cs544.volunteerproject.domain.Resource;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ProjectService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private ProjectDao projectDao = new ProjectDao();
	private BeneficierDao beneficierDao = new BeneficierDao();
	
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
	
	public void addBeneficer(Project project, Beneficier beneficier) {
		if (project == null || beneficier == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		ProjectBeneficier projectBeneficer = new ProjectBeneficier(beneficier, project);
		beneficierDao.create(projectBeneficer);
		entityManager.getTransaction().commit();
	}
	
	public void removeBeneficer(Project project, Beneficier beneficier) {
		if (project == null || beneficier == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		ProjectBeneficier projectBeneficer = beneficierDao.findByProjectAndBeneficier(project, beneficier);
		if (projectBeneficer != null) {
			beneficierDao.delete(projectBeneficer);
		}
		entityManager.getTransaction().commit();
	}
	
	public void removeBeneficier(ProjectBeneficier projectBeneficier) {
		if (projectBeneficier != null) {
			entityManager.getTransaction().begin();
			beneficierDao.delete(projectBeneficier);
			entityManager.getTransaction().commit();
		}
	}
	
	public List<Project> findByResource(Resource resource) {
		return projectDao.findByResource(resource);
	}
	
	public List<Project> findByAsset(Asset asset) {
		return projectDao.findByAsset(asset);
	}	
	
	public List<Project> findBySkillSet(SkillSet skillset) {
		return projectDao.findBySkillSet(skillset);
	}
	
	public List<Project> findAllProjectsWithVolunteers() {
		return projectDao.findAllProjectsWithVolunteers();
	}
}
