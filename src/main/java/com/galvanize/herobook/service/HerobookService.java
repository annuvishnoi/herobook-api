package com.galvanize.herobook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.repository.HerobookRepository;

@Service
public class HerobookService {
	
	HerobookRepository herobookRepository;
	
	public HerobookService(HerobookRepository herobookRepository) {
		this.herobookRepository = herobookRepository;
	}

	public List<String> getHeroes() {
		return herobookRepository.findAll().stream().map(Hero::getHeroName).collect(Collectors.toList());
	}

	public Hero getHeroDetails(String heroName) {		
		return new Hero();
	}
}
