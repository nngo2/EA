package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.dao.ProjectDao;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.service.TaskService;

public class TaskServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private ProjectDao projectDao = new ProjectDao();
	private TaskService taskService = new TaskService();	
	private Project testProject;
	private Task testTask;
	
	@Before
	public void setUp() throws Exception {
		testProject = new Project("Test Project 1", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		testTask = new Task("Test Task 1", "Test Task 1", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.COMPLETED);
		projectDao.create(testProject);
	}

	@After
	public void tearDown() throws Exception {
		projectDao.delete(testProject);
		taskService.delete(testTask);
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

//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindByName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindByProject() {
//		fail("Not yet implemented");
//	}

}
