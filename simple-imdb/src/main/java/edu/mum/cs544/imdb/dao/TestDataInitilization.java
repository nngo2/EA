package edu.mum.cs544.imdb.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.imdb.domain.Person;
import edu.mum.cs544.imdb.domain.Season;

@Component
public class TestDataInitilization {
	@Autowired
	private SeasonDao seasonDao;
	
	@Autowired
	private PersonDao personDao;	

	@PostConstruct
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void initializeData() {

		List<Season> seasons = seasonDao.findAll();
		for (int i = 1; i <= 5; i++) {
			byte[] img = getImage("testimg/" + i + ".jpg");
			seasons.get(i - 1).setPoster(img);
			seasonDao.save(seasons.get(i - 1));
		}
		
		List<Person> persons = personDao.findAll();
		for (int i = 1; i <= 5; i++) {
			byte[] img = getImage("testimg/p" + i + ".jpg");
			persons.get(i - 1).setPicture(img);
			personDao.save(persons.get(i - 1));
		}
	}

	private byte[] getImage(String path) {
		try {
			Resource resource = new ClassPathResource(path);
			InputStream resourceInputStream = resource.getInputStream();

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = resourceInputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			
			return buffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
