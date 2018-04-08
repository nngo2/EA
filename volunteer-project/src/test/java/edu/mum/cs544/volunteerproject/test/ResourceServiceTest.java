package edu.mum.cs544.volunteerproject.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.mum.cs544.volunteerproject.domain.Asset;
import edu.mum.cs544.volunteerproject.domain.SkillSet;
import edu.mum.cs544.volunteerproject.service.ResourceService;

public class ResourceServiceTest {
	private ResourceService resourceService = new ResourceService();
	private Asset testResource1;
	private SkillSet testResource2; 
	
	@Before
	public void setUp() throws Exception {
		testResource1 = new Asset("Laptop", 1000, "Lenovo T570", "Core I8, 8GB");
		testResource2 = new SkillSet("C++", 7);	
	}

	@After
	public void tearDown() throws Exception {
		if (testResource1.getId() > 0) {
			resourceService.delete(testResource1);
		}
		if (testResource2.getId() > 0) {
			resourceService.delete(testResource2);
		}
	}

	@Test
	public void testCreate() {
		resourceService.create(testResource1);
		resourceService.create(testResource2);
		
		Asset r1 = (Asset)resourceService.findOne(testResource1.getId());
		SkillSet r2 = (SkillSet)resourceService.findOne(testResource2.getId());
	
		assertNotNull(r1);
		assertNotNull(r2);
		assertTrue(r1.getConfiguration() == testResource1.getConfiguration());
		assertTrue(r2.getYearOfExperience() == testResource2.getYearOfExperience());
	}

	@Test
	public void testUpdate() {
		resourceService.create(testResource1);
		testResource1.setCost(2000);
		resourceService.update(testResource1);
		
		Asset r1 = (Asset)resourceService.findOne(testResource1.getId());
	
		assertNotNull(r1);
		assertTrue(r1.getCost() == testResource1.getCost());
	}
	
	@Test
	public void testGetAllAssets() {
		resourceService.create(testResource1);
		
		List<Asset> resources = resourceService.findAllAssets();
	
		assertNotNull(resources);
		assertTrue(resources.size() == 1);
	}
	
	@Test
	public void testGetAllSkillSets() {
		resourceService.create(testResource2);
		
		List<SkillSet> resources = resourceService.findAllSkillSets();
	
		assertNotNull(resources);
		assertTrue(resources.size() == 1);
	}
}
