package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.imdb.domain.TVSeries;

@Repository
public interface TVSeriesDao extends JpaRepository<TVSeries, Integer> {

}
