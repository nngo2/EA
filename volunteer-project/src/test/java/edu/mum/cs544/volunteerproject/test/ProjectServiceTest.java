package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.AssetType;
import edu.mum.cs544.volunteerproject.domain.Beneficier;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.ProjectBeneficier;
import edu.mum.cs544.volunteerproject.domain.ResourceBooking;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.service.PersonService;
import edu.mum.cs544.volunteerproject.service.ProjectService;
import edu.mum.cs544.volunteerproject.service.ResourceBookingService;
import edu.mum.cs544.volunteerproject.service.ResourceService;
import edu.mum.cs544.volunteerproject.service.TaskService;

public class ProjectServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private ProjectService projectService = new ProjectService();
	private TaskService taskService = new TaskService();	
	private PersonService personService = new PersonService();
	private ResourceService resourceService = new ResourceService();
	private ResourceBookingService resourceBookingService = new ResourceBookingService();
	private Project testProject;
	private Beneficier beneficier;
	private ProjectBeneficier projectBeneficier;
	
	
	@Before
	public void setUp() throws Exception {
		testProject = new Project("Test Project 1", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		String description = "<html><head><title>TEst<title></head><body> word1 word2 </body></html>";
		testProject.setDescription(description.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
		beneficier = new Beneficier("John", "Smith", df.parse("1/1/1990"));
	}

	@After
	public void tearDown() throws Exception {
		if (projectBeneficier != null && projectBeneficier.getId() > 0) {
			projectService.removeBeneficier(projectBeneficier);
		}
		if (beneficier != null && beneficier.getId() > 0) {
			personService.delete(beneficier);
		}
		if (testProject.getId() > 0) {
			projectService.delete(testProject);
		}
	}

	@Test
	public void testCreate() {
		projectService.create(testProject);
		
		List<Project> projects = projectService.findByName("Test Project 1");
		
		assertNotNull(projects);
		assertEquals("Test Project 1", projects.get(0).getName());
	}

	@Test
	public void testUpdate() {
		testProject.setLocation("Ohio");
		testProject.setName("Test Project 1 Editted");
		projectService.update(testProject);
		
		List<Project> projects = projectService.findByLocation("Ohio");
		
		assertNotNull(projects);
		assertEquals("Test Project 1 Editted", projects.get(0).getName());
	}

	@Test
	public void testDelete() {
		projectService.create(testProject);
		projectService.delete(testProject);
		
		List<Project> projects = projectService.findByName("Test Project 1");
		assertNotNull(projects);
		assertEquals(0, projects.size());
	}

	@Test
	public void testFindByKeywords() {
		projectService.create(testProject);
		
		List<Project> projects;
		try {
			projects = projectService.findByKeywords(new String[] {"word1" , "word2"});
			assertNotNull(projects);
			assertTrue(projects.size() > 0);
		} catch (Exception e) {
			fail("Keywords error");
		}	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindByKeywordsError() {
		projectService.findByKeywords(new String[] {";" , "?"});
	}
	
	@Test
	public void testFindByStatus() {
		testProject.setStatus(Status.COMPLETED);
		projectService.create(testProject);
		
		List<Project> projects = projectService.findByStatus(Status.COMPLETED);
		projects = projectService.findByStatus(Status.COMPLETED);
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
		assertTrue(Status.COMPLETED == projects.get(0).getStatus());
	}
	
	@Test
	public void testAddBeneficier() {
		projectService.create(testProject);
		personService.create(beneficier);
		projectService.addBeneficer(testProject, beneficier);
		
		projectBeneficier = testProject.getProjectBeneficers().get(0);
				
		assertTrue(projectBeneficier != null);		
	}
	
	@Test
	public void testRemoveBeneficier() throws ParseException {
		Project p = new Project("Test Project 2", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p);
		
		Beneficier b = new Beneficier("John", "Smith 2", df.parse("1/1/1990"));
		personService.create(b);
		
		projectService.addBeneficer(p, b);	
	
		projectService.removeBeneficer(p, b);
		
		assertTrue(p.getProjectBeneficers().size() == 0);		
		
		personService.delete(b);
		projectService.delete(p);
	}
	
	@Test
	public void findByAsset() throws ParseException {
		// arrange test data
		Project p1 = new Project("Test Project 11", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p1);
		Project p2 = new Project("Test Project 22", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p2);
		
		Task t1 = new Task("Test Task 11", "Test Task 11", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p1, t1);
		Task t2 = new Task("Test Task 22", "Test Task 22", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p2, t2);
		
		Asset r1 = new Asset("Laptop", 1000.00, "Lenovo T570", "Core I8, 8GB", AssetType.LAPTOP);
		resourceService.create(r1);
		Asset r2 = new Asset("Desktop", 1000.00, "Dell Server", "Core I8, 64GB", AssetType.DESTOP);
		resourceService.create(r2);
	
		ResourceBooking rb1 = new ResourceBooking(t1, r1, 2);
		resourceBookingService.create(t1, rb1);
		ResourceBooking rb2 = new ResourceBooking(t2, r2, 3);
		resourceBookingService.create(t2, rb2);
		
		// action
		List<Project> result =  projectService.findByAsset(r1);
		
		// verification
		assertTrue(result != null && result.size() == 1);
		
		// clean up
		projectService.delete(p1);
		projectService.delete(p2);
		resourceService.delete(r1);
		resourceService.delete(r2);
	}
	
	@Test
	public void findBySkillSet() throws ParseException {
		// arrange test data
		Project p1 = new Project("Test Project 11", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p1);
		Project p2 = new Project("Test Project 22", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p2);
		
		Task t1 = new Task("Test Task 11", "Test Task 11", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p1, t1);
		Task t2 = new Task("Test Task 22", "Test Task 22", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p2, t2);
		
		SkillSet r1 = new SkillSet("C++", 8.5);
		resourceService.create(r1);
		SkillSet r2 = new SkillSet("Java", 9.5);
		resourceService.create(r2);
	
		ResourceBooking rb1 = new ResourceBooking(t1, r1, 2);
		resourceBookingService.create(t1, rb1);
		ResourceBooking rb2 = new ResourceBooking(t2, r2, 3);
		resourceBookingService.create(t2, rb2);
		
		// action
		List<Project> result =  projectService.findBySkillSet(r1);
		
		// verification
		assertTrue(result != null && result.size() == 1);
		
		// clean up
		projectService.delete(p1);
		projectService.delete(p2);
		resourceService.delete(r1);
		resourceService.delete(r2);
	}
	
	@Test
	public void findByVolunteer() throws ParseException {
		// arrange test data
		Project p1 = new Project("Test Project 11", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p1);
		Project p2 = new Project("Test Project 22", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		projectService.create(p2);
		
		Task t1 = new Task("Test Task 11", "Test Task 11", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p1, t1);
		Task t2 = new Task("Test Task 22", "Test Task 22", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		taskService.create(p2, t2);
		
		Volunteer v1 = new Volunteer("NO SQL Developer, with 7+ Java yoe", "Nanny", "Chris", df.parse("01/01/1988"));
		personService.create(v1);
		taskService.addVolunteer(t1, v1);	
		
		Volunteer v2 = new Volunteer("Java EE Architect, with 7+ Java yoe", "Tommy", "White", df.parse("01/01/1988"));
		personService.create(v2);
		taskService.addVolunteer(t1, v2);		
		
		// action
		List<Project> result =  projectService.findAllProjectsWithVolunteers();
		
		// verification
		assertTrue(result != null && result.size() == 1);
		
		// clean up
		projectService.delete(p1);
		projectService.delete(p2);
	}
}
