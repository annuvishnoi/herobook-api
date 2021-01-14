package com.galvanize.herobook.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Villain extends Persona{
	@Id
	private String villainName;
	
	private String archRival;

	public Villain() {
		super();
	}

	public Villain(String villainName) {
		super();
		this.villainName = villainName;
	}

	public String getVillainName() {
		return villainName;
	}

	public void setVillainName(String villainName) {
		this.villainName = villainName;
	}

	public String getArchRival() {
		return archRival;
	}

	public void setArchRival(String archRival) {
		this.archRival = archRival;
	}
	
}
