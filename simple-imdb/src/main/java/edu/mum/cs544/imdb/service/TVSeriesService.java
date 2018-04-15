package edu.mum.cs544.imdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.imdb.dao.TVSeriesDao;
import edu.mum.cs544.imdb.domain.TVSeries;

@Service
@Transactional
public class TVSeriesService {
	@Autowired
	private TVSeriesDao tvSeriesDao;
	
	public List<TVSeries> findAll() {
		return tvSeriesDao.findAll();
	}
	
	public TVSeries findById(int id) {
		return tvSeriesDao.findOne(id);
	}
}
