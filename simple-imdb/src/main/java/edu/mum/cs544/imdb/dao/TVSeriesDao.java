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
	
	@Query("select distinct s from TVSeries s join s.genres genre where genre = :genre")
	public List<TVSeries> findByGenre(@Param("genre") Genre genre);
	
	@Query("select distinct se from TVSeries se join se.seasons s join s.episodes e join e.characters c join c.artist a where a.name like %:name%")
	public List<TVSeries> findBySeasonsEpisodesCharactersArtistNameLike(@Param("name") String name);

	@Query("select distinct se from TVSeries se join se.seasons s join s.episodes e join e.characters c where c.name like %:name%")	
	public List<TVSeries> findBySeasonsEpisodesCharactersNameLike(@Param("name") String name);
	
	@Query("select distinct se from TVSeries se join se.director d where d.name like %:name%")
	public List<TVSeries> findByDirectorNameLike(@Param("name") String name);
}
