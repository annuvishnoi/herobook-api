package com.galvanize.herobook.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hero extends Persona{
	
	@Id
	private String heroName;
	
	public Hero() {
		super();
	}
	
	public Hero(String heroName) {
		super();
		this.heroName = heroName;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	
}
