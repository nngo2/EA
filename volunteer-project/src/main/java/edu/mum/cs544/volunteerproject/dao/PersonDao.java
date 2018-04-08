package edu.mum.cs544.volunteerproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Person;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class PersonDao {
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(Person person) {	
		entityManager.persist(person);
	}
	
	public void update(Person person) {
		entityManager.merge(person);
	}
	
	public void delete(Person person) {
		Person removed = entityManager.find(Person.class, person.getId());
		if (removed != null) {
			entityManager.remove(removed);
		}
	}
	
	public Person findOne(int id) {
		return entityManager.find(Person.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		Query query = entityManager.createQuery("select p from Person p", Person.class);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Volunteer> findAllVolunteers() {
		Query query = entityManager.createQuery("select p from Volunteer p", Volunteer.class);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Beneficier> findAllBeneficiers() {
		Query query = entityManager.createQuery("select p from Beneficier p", Beneficier.class);
		return query.getResultList();
	}
}
