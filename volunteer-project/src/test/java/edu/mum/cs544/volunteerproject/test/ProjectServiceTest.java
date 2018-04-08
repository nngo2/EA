package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.service.ProjectService;

public class ProjectServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private ProjectService projectService = new ProjectService();
	private Project testProject;
	
	@Before
	public void setUp() throws Exception {
		testProject = new Project("Test Project 1", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		String description = "<html><head><title>TEst<title></head><body> word1 word2 </body></html>";
		testProject.setDescription(description.chars().mapToObj(c -> (char)c).toArray(Character[]::new));
	}

	@After
	public void tearDown() throws Exception {
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
}
