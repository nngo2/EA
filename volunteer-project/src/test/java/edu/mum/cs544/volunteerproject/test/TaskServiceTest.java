package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.domain.Volunteer;
import edu.mum.cs544.volunteerproject.domain.VolunteerTask;
import edu.mum.cs544.volunteerproject.service.PersonService;
import edu.mum.cs544.volunteerproject.service.ProjectService;
import edu.mum.cs544.volunteerproject.service.TaskService;

public class TaskServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private ProjectService projectService = new ProjectService();
	private TaskService taskService = new TaskService();
	private PersonService personService = new PersonService();	
	private Project testProject;
	private Task testTask;
	
	@Before
	public void setUp() throws Exception {
		testProject = new Project("Test Project 1", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		testTask = new Task("Test Task 1", "Test Task 1", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.COMPLETED);
		projectService.create(testProject);
	}

	@After
	public void tearDown() throws Exception {
		if (testTask.getId() > 0) {
			taskService.delete(testTask);
		}
		if (testProject.getId() > 0) {
			projectService.delete(testProject);			
		}
	}

	@Test
	public void testCreate() {
		taskService.create(testProject, testTask);
		
		List<Task> tasks = taskService.findByName("Test Task 1");
	
		assertNotNull(tasks);
		assertEquals("Test Task 1", tasks.get(0).getName());
	}

	@Test
	public void testUpdate() {
		taskService.create(testProject, testTask);
		testTask.setDescription("New Description");
		taskService.update(testTask);
		
		List<Task> tasks = taskService.findByName("Test Task 1");
		
		assertNotNull(tasks);
		assertEquals("New Description", tasks.get(0).getDescription());
	}

	@Test
	public void testDelete() {
		Task task = null;
		try {
			task = new Task("Test Task 2", "Test Task 2", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.COMPLETED);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		taskService.create(testProject, task);
		taskService.delete(task);
		
		List<Task> tasks = taskService.findByName("Test Task 2");
		
		assertNotNull(tasks);
		assertTrue(tasks.size() == 0);
	}

	@Test
	public void testFindByName() {
		testTask.setName("New Test Name");
		taskService.create(testProject, testTask);
		
		List<Task> tasks = taskService.findByName("New Test Name");
		
		assertNotNull(tasks);
		assertTrue(tasks.size() == 1);
	}

	@Test
	public void testFindByProject() {
		taskService.create(testProject, testTask);
		
		List<Task> tasks = taskService.findByProject(testProject);
		
		assertNotNull(tasks);
		assertTrue(tasks.size() == 1);
	}

	private Volunteer addVolunteerToTask(Task task) throws ParseException {
		Volunteer volunteer = new Volunteer("NO SQL Developer, with 7+ Java yoe", "Nanny", "Chris", df.parse("01/01/1988"));
		personService.create(volunteer);
		taskService.addVolunteer(task, volunteer);
		return volunteer;
	}
	
	@Test
	public void testAddVolunteer() throws ParseException  {
		taskService.create(testProject, testTask);
		
		Volunteer volunteer = addVolunteerToTask(testTask);
		
		Task task = taskService.findOne(testTask.getId());
		
		assertNotNull(task);
		
		VolunteerTask vt = task.getVolunteerTasks().get(0);
		
		assertNotNull(vt);
		assertTrue(vt.getTask().getName().equals(testTask.getName()));
		
		taskService.removeVolunteer(vt);
		personService.delete(volunteer);
	}
	
	@Test
	public void testRemoveVolunteer() throws ParseException {
		taskService.create(testProject, testTask);
		
		Volunteer volunteer = addVolunteerToTask(testTask);
		
		taskService.removeVolunteer(testTask, volunteer);
		
		Task task = taskService.findOne(testTask.getId());
		
		assertNotNull(task);
		assertTrue(task.getVolunteerTasks().size() == 0);

		personService.delete(volunteer);
	}
	
}
