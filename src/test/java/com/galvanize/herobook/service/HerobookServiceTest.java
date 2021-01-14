package com.galvanize.herobook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.herobook.exception.HerobookException;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.model.Villain;
import com.galvanize.herobook.repository.HeroRepository;
import com.galvanize.herobook.repository.VillainRepository;

public class HerobookServiceTest {

	private String heroPath = "src/test/resources/hero.json";
	private String villainPath = "src/test/resources/villain.json";

	HeroRepository herobookRepository;
	HerobookService herobookService;

	VillainRepository villainRepository;
	
	@BeforeEach
	void initRepository() {
		herobookRepository = mock(HeroRepository.class);
		villainRepository = mock(VillainRepository.class);
		herobookService = new HerobookService(herobookRepository, villainRepository);
	}

	@Test
	public void getHeroes() {

		List<Hero> heroes = new ArrayList<>();
		Hero hero = new Hero("Spider Man");
		heroes.add(hero);

		when(herobookRepository.findAll()).thenReturn(heroes);

		List<String> actualHeroes = herobookService.getHeroes();

		assertFalse(actualHeroes.isEmpty());

		assertTrue(actualHeroes.contains("Spider Man"));

		verify(herobookRepository).findAll();
	}

	@Test
	public void getHeroDetails() throws IOException, HerobookException {

		when(herobookRepository.findById(Mockito.anyString())).thenReturn(Optional.of(heroContent()));

		Hero actualHero = herobookService.getHeroDetails("SpiderMan");

		assertNotNull(actualHero);

		assertEquals("Peter Parker", actualHero.getRealName());

		verify(herobookRepository).findById(Mockito.anyString());
	}

	@Test
	public void getHeroDetails_heroNotFound_thenThrowException() throws Exception {
		when(herobookRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		HerobookException expected = assertThrows(HerobookException.class,
				() -> herobookService.getHeroDetails("Jonathan"));// Not a superhero
		assertEquals("Hero not found", expected.getMessage());

		verify(herobookRepository).findById(Mockito.anyString());
	}

	@Test
	public void getVillains() {

		List<Villain> villains = new ArrayList<>();
		Villain villain = new Villain("Spider Man");
		villains.add(villain);

		when(villainRepository.findAll()).thenReturn(villains);

		List<String> actualVillains = herobookService.getVillains();

		assertFalse(actualVillains.isEmpty());

		assertTrue(actualVillains.contains("Spider Man"));

		verify(villainRepository).findAll();
	}
	
	@Test
	public void getVillainDetails() throws IOException, HerobookException {

		when(villainRepository.findById(Mockito.anyString())).thenReturn(Optional.of(villainContent()));

		Villain actualVillain = herobookService.getVillainDetails("Greengoblin");

		assertNotNull(actualVillain);

		assertEquals("Osbourn", actualVillain.getRealName());

		verify(villainRepository).findById(Mockito.anyString());
	}
	
	@Test
	public void getVillainDetails_villainNotFound_thenThrowException() throws Exception {
		
		when(villainRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		HerobookException expected = assertThrows(HerobookException.class,
				() -> herobookService.getVillainDetails("Jonathan"));// Not a villain
		
		assertEquals("Villain not found", expected.getMessage());

		verify(villainRepository).findById(Mockito.anyString());
	}
	
	private Hero heroContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Hero hero = mapper.readValue(new File(heroPath), Hero.class);
		return hero;

	}
	
	private Villain villainContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Villain villain = mapper.readValue(new File(villainPath), Villain.class);
		return villain;

	}

}
