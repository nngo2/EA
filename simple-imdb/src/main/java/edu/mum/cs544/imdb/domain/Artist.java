package edu.mum.cs544.imdb.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Artist")
public class Artist extends Person {

}
