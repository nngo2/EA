package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.imdb.domain.Artist;

public interface ArtistDao extends JpaRepository<Artist, Integer>{

}
