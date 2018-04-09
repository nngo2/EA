package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Person;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.service.PersonService;

public class PersonServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private PersonService personService = new PersonService();
	private Beneficier beneficier;
	private Volunteer volunteer;

	@Before
	public void setUp() throws Exception {
		beneficier = new Beneficier("John", "Smith", df.parse("1/1/1990"));
		volunteer = new Volunteer("Very goood C++ developer", "Adam", "Eastmoreland", df.parse("1/1/1990"));
	}

	@After
	public void tearDown() throws Exception {
		if (beneficier.getId() > 0) {
			personService.delete(beneficier);
		}
		if (volunteer.getId() > 0) {
			personService.delete(volunteer);
		}
	}

	@Test
	public void testCreate() {
		personService.create(volunteer);
		personService.create(beneficier);
		
		List<Person> list = personService.findAll();
		
		assertNotNull(list);
		assertTrue(list.size() == 2);
	}

	@Test
	public void testUpdate() {
		personService.create(volunteer);
		String newName = volunteer.getFirstName() + " Jr";
		volunteer.setFirstName(newName);
		personService.update(volunteer);
		
		Volunteer v = (Volunteer)personService.findOne(volunteer.getId());
		assertTrue(newName == v.getFirstName());
	}

	@Test
	public void testDelete() throws ParseException {
		Beneficier ben = new Beneficier("John", "Smith", df.parse("1/1/1990"));
		personService.delete(ben);
		
		ben = (Beneficier)personService.findOne(ben.getId());
		
		assertTrue(ben == null);
	}

	@Test
	public void testFindAllBeneficiers() {
		personService.create(volunteer);
		personService.create(beneficier);
		
		List<Beneficier> list = personService.findAllBeneficiers();
		
		assertNotNull(list);
		assertTrue(list.size() == 1);
	}

	@Test
	public void testFindAllVolunteers() {
		personService.create(volunteer);
		personService.create(beneficier);
		
		List<Volunteer> list = personService.findAllVolunteers();
		
		assertNotNull(list);
		assertTrue(list.size() == 1);
	}

}
