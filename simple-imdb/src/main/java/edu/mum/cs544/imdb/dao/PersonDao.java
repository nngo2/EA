package edu.mum.cs544.imdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs544.imdb.domain.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer>{

}
