package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.imdb.domain.TVSeries;

public interface TVSeriesDao extends JpaRepository<TVSeries, Integer> {

}
