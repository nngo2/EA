package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.imdb.domain.Episode;

public interface EpisodeDao extends JpaRepository<Episode, Integer>{

}
