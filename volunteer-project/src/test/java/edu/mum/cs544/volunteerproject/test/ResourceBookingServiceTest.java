package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.Project;
import edu.mum.cs544.volunteerproject.domain.ResourceBooking;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.domain.Status;
import edu.mum.cs544.volunteerproject.domain.Task;
import edu.mum.cs544.volunteerproject.service.ProjectService;
import edu.mum.cs544.volunteerproject.service.ResourceBookingService;
import edu.mum.cs544.volunteerproject.service.ResourceService;
import edu.mum.cs544.volunteerproject.service.TaskService;

public class ResourceBookingServiceTest {
	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private ProjectService projectService = new ProjectService();
	private TaskService taskService = new TaskService();
	private ResourceService resourceService = new ResourceService();
	private ResourceBookingService resourceBookingService = new ResourceBookingService();
	private Asset testResource1;
	private SkillSet testResource2; 
	private ResourceBooking resourceBooking1;
	private ResourceBooking resourceBooking2;
	private Project testProject;
	private Task testTask;
	
	@Before
	public void setUp() throws Exception {
		testProject = new Project("Test Project 1", "Moon", df.parse("01/01/2019"), df.parse("01/04/2019"), Status.NOT_STARTED);
		testTask = new Task("Test Task 1", "Test Task 1", df.parse("01/01/2019"), df.parse("01/31/2019"), Status.NOT_STARTED);
		testResource1 = new Asset("Laptop", 1000, "Lenovo T570", "Core I8, 8GB");
		testResource2 = new SkillSet("C++", 7);		
		resourceBooking1 = new ResourceBooking(testTask, testResource1, 2);
		resourceBooking2 = new ResourceBooking(testTask, testResource2, 3);
		resourceService.create(testResource1);
		resourceService.create(testResource2);
		projectService.create(testProject);
		taskService.create(testProject, testTask);
	}

	@After
	public void tearDown() throws Exception {
		if (resourceBooking1.getId() > 0) {
			resourceBookingService.delete(resourceBooking1);
		}
		if (resourceBooking2.getId() > 0) {
			resourceBookingService.delete(resourceBooking2);
		}		
		if (testResource1.getId() > 0) {
			resourceService.delete(testResource1);
		}
		if (testResource2.getId() > 0) {
			resourceService.delete(testResource2);
		}		
		if (testTask.getId() > 0) {
			taskService.delete(testTask);
		}
		if (testProject.getId() > 0) {
			projectService.delete(testProject);			
		}
	}

	@Test
	public void testCreate() {
		resourceBookingService.create(testTask, resourceBooking1);
		resourceBookingService.create(testTask, resourceBooking2);
		
		Task task = taskService.findOne(testTask.getId());
	
		assertNotNull(task);
		assertTrue(task.getResourceBookings().size() == 2);
	}

	@Test
	public void testUpdate() {
		resourceBookingService.create(testTask, resourceBooking1);
		resourceBooking1.setQuality(7);
		
		Task task = taskService.findOne(testTask.getId());
	
		assertNotNull(task);
		assertTrue(task.getResourceBookings().get(0).getQuality() == 7);
	}

	@Test
	public void testDelete() {
		ResourceBooking rb = new ResourceBooking(testTask, testResource1, 100);
		resourceBookingService.create(testTask, rb);
		resourceBookingService.delete(rb);

		rb = resourceBookingService.findOne(rb.getId());
		
		assertNull(rb);
	}
}
