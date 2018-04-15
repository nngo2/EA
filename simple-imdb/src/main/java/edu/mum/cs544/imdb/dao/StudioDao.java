package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.imdb.domain.Studio;

public interface StudioDao extends JpaRepository<Studio, Integer> {

}
