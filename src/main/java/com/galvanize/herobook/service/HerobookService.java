package com.galvanize.herobook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.galvanize.herobook.exception.HerobookException;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.model.Villain;
import com.galvanize.herobook.repository.HeroRepository;
import com.galvanize.herobook.repository.VillainRepository;

@Service
public class HerobookService {
	
	HeroRepository herobookRepository;
	VillainRepository villainRepository;
	
	public HerobookService(HeroRepository herobookRepository, VillainRepository villainRepository) {
		this.herobookRepository = herobookRepository;
		this.villainRepository = villainRepository;
	}

	public List<String> getHeroes() {
		return herobookRepository.findAll().stream().map(Hero::getHeroName).collect(Collectors.toList());
	}

	public Hero getHeroDetails(String heroName) throws HerobookException {		
		return herobookRepository.findById(heroName).orElseThrow(() -> new HerobookException("Hero not found"));
	}

	public List<String> getVillains() {
		return villainRepository.findAll().stream().map(Villain::getVillainName).collect(Collectors.toList());
	}

	public Villain getVillainDetails(String villainName) throws HerobookException{
		return villainRepository.findById(villainName).orElseThrow(() -> new HerobookException("Villain not found"));
	}
}

