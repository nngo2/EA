package edu.mum.cs544.imdb.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Director")
public class Director extends Person {

}
