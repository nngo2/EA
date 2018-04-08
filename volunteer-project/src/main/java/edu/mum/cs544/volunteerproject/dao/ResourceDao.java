package edu.mum.cs544.volunteerproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.Resource;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ResourceDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	
	public void create(Resource resource) {	
		entityManager.persist(resource);
	}
	
	public void update(Resource resource) {
		entityManager.merge(resource);
	}
	
	public void delete(Resource resource) {
		Resource removed = entityManager.find(Resource.class, resource.getId());
		if (removed != null) { // through FK exception if resource in used
			entityManager.remove(removed);
		}
	}
	
	public Resource findOne(int id) {
		return entityManager.find(Resource.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> findAll() {
		Query query = entityManager.createQuery("select r from Resource r");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Asset> findAllAssets() {
		Query query = entityManager.createQuery("select a from Asset a");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SkillSet> findAllSkillSets() {
		Query query = entityManager.createQuery("select s from SkillSet s");
		return query.getResultList();
	}
}
