package edu.mum.cs544.volunteerproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.ProjectBeneficier;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class BeneficierDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(ProjectBeneficier projectBeneficer) {	
		projectBeneficer.getPerson().addProjectBeneficer(projectBeneficer);
		projectBeneficer.getProject().addProjectBeneficer(projectBeneficer);
		entityManager.persist(projectBeneficer);
	}
	
	public void delete(ProjectBeneficier projectBeneficier) {
		projectBeneficier.getProject().removeProjectBeneficer(projectBeneficier);
		projectBeneficier.getPerson().removeProjectBeneficer(projectBeneficier);
		ProjectBeneficier removed = entityManager.find(ProjectBeneficier.class, projectBeneficier.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public ProjectBeneficier findOne(int id) {
		return entityManager.find(ProjectBeneficier.class, id);
	}
	
	public ProjectBeneficier findByProjectAndBeneficier(Project project, Beneficier beneficier) {
		Query query = entityManager.createQuery("select b from ProjectBeneficier b "
				+ "where b.person.id = :beneficierId and b.project.id = :projectId");
		query.setParameter("beneficierId", beneficier.getId());
		query.setParameter("projectId", project.getId());
		
		return (ProjectBeneficier) query.getSingleResult();
	}
}
