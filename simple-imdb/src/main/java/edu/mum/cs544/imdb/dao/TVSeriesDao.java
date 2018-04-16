package edu.mum.cs544.imdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.mum.cs544.imdb.domain.Genre;
import edu.mum.cs544.imdb.domain.TVSeries;

public interface TVSeriesDao extends JpaRepository<TVSeries, Integer> {
	@Query("select s from TVSeries s where name like %:name%")
	public List<TVSeries> findByName(@Param("name") String name);
	
	@Query("select s from TVSeries s join s.genres genre where genre = :genre")
	public List<TVSeries> findByGenre(@Param("genre") Genre genre);
	
	public List<TVSeries> findBySeasonsEpisodesCharactersArtistName(String name);
	
	public List<TVSeries> findBySeasonsEpisodesCharactersName(String name);
	
	public List<TVSeries> findByDirectorName(String name);
}
