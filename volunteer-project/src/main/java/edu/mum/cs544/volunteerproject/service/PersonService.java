package edu.mum.cs544.volunteerproject.service;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.cs544.volunteerproject.dao.PersonDao;
import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Person;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.util.JpaUtil;

public class PersonService {
	private EntityManager entityManager = JpaUtil.getEntityManager();
	private PersonDao personDao = new PersonDao();
	
	public void create(Person person) {	
		if (person == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		personDao.create(person);
		entityManager.getTransaction().commit();
	}
	
	public void update(Person person) {
		if (person == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		personDao.update(person);
		entityManager.getTransaction().commit();		
	}
	
	public void delete(Person person) {
		if (person == null) {
			throw new IllegalArgumentException();
		}
		entityManager.getTransaction().begin();
		personDao.delete(person);
		entityManager.getTransaction().commit();
	}
	
	public Person findOne(int id) {
		return personDao.findOne(id);
	}
	
	public List<Person> findAll() {
		return personDao.findAll();
	}

	public List<Beneficier> findAllBeneficiers() {
		return personDao.findAllBeneficiers();
	}
	
	public List<Volunteer> findAllVolunteers() {
		return personDao.findAllVolunteers();
	}
}
