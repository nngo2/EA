package edu.mum.cs544.volunteerproject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Resource;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class ProjectDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(Project project) {	
		entityManager.persist(project);
	}
	
	public void update(Project project) {
		entityManager.merge(project);
	}
	
	public void delete(Project project) {
		Project removed = entityManager.find(Project.class, project.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public Project findOne(int id) {
		return entityManager.find(Project.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByKeywords(String[] keywords) throws IllegalArgumentException {
		if (keywords != null && keywords.length > 0) {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < keywords.length; i++) {
				if (keywords[i] != null) {
					if (!keywords[i].matches("\\w*")) {
						throw new IllegalArgumentException("Illegal parameters");
					}
					b.append("p.description like ");
					b.append("'%");
					b.append(keywords[i]);
					b.append("%'");
					if (i < keywords.length - 1) {
						b.append(" or ");
					}
				}
			}
			
			Query q = entityManager.createQuery("select p from Project p "
					+ "left join p.tasks t "
					+ "left join t.resourceBookings rb "
					+ "left join rb.resource r "					
					+ "where  " + b.toString(), Project.class);
			
			return q.getResultList();
		}
		
		return new ArrayList<Project>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByLocation(String location) {
		Query q = entityManager.createQuery("select p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join rb.resource r "					
				+ "where p.location like :location", Project.class);
		q.setParameter("location", "%" + location + "%");
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByName(String name) {
		Query q = entityManager.createQuery("select p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join rb.resource r "					
				+ "where p.name like :name", Project.class);
		q.setParameter("name", "%" + name + "%");
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByStatus(Status status) {
		Query q = entityManager.createQuery("select p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join rb.resource r "					
				+ "where p.status = :status", Project.class);
		q.setParameter("status", status);
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByResource(Resource resource) {
		Query q = entityManager.createQuery("select distinct p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join rb.resource r "					
				+ "where r.id = :id", Project.class);
		q.setParameter("id", resource.getId());
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findBySkillSet(SkillSet skillset) {
		Query q = entityManager.createQuery("select distinct p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join treat(rb.resource as SkillSet) r "					
				+ "where r.description like :description "
				+ "and (:yoe is null or r.yearOfExperience = :yoe)", Project.class);
		q.setParameter("description", "%" + skillset.getDescription() + "%");
		q.setParameter("yoe", skillset.getYearOfExperience());
				
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findByAsset(Asset asset) {
		Query q = entityManager.createQuery("select distinct p from Project p "
				+ "left join p.tasks t "
				+ "left join t.resourceBookings rb "
				+ "left join treat(rb.resource as Asset) r "					
				+ "where (:assetType is null or r.assetType = :assetType) "
				+ "and (:model is null or r.model = :model) " 
				+ "and (:configuration is null or r.configuration = :configuration)"
				+ "and (:cost is null or r.cost = :cost)", Project.class);
		q.setParameter("assetType", asset.getAssetType());
		q.setParameter("model", asset.getModel());
		q.setParameter("configuration", asset.getConfiguration());
		q.setParameter("cost", asset.getCost());
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findAllProjectsWithVolunteers() {
		Query q = entityManager.createQuery("select distinct p from Project p "
				+ "left join p.tasks t "
				+ "left join t.volunteerTasks v "
				+ "where size(t.volunteerTasks) > 0 "
				+ "order by t.startDate", Project.class);
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> findAll() {
		Query query = entityManager.createQuery("select p from Project p", Project.class);
		return query.getResultList();
	}
}
