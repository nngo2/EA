package edu.mum.cs544.volunteerproject.service;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.ResourceDao;
import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.Resource;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ResourceService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private ResourceDao resourceDao = new ResourceDao();
	
	public void create(Resource resource) {	
		if (resource == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceDao.create(resource);
		entityManager.getTransaction().commit();
	}
	
	public void update(Resource resource) {
		if (resource == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceDao.update(resource);
		entityManager.getTransaction().commit();		
	}
	
	public void delete(Resource resource) {
		if (resource == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		resourceDao.delete(resource);
		entityManager.getTransaction().commit();
	}
	
	public Resource findOne(int id) {
		return resourceDao.findOne(id);
	}
	
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	public List<Asset> findAllAssets() {
		return resourceDao.findAllAssets();
	}
	
	public List<SkillSet> findAllSkillSets() {
		return resourceDao.findAllSkillSets();
	}
}
