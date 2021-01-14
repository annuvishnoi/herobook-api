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
import com.galvanize.herobook.repository.HerobookRepository;

public class HerobookServiceTest {

	private String heroPath = "src/test/resources/hero.json";

	HerobookRepository herobookRepository;
	HerobookService herobookService;

	@BeforeEach
	void initRepository() {
		herobookRepository = mock(HerobookRepository.class);
		herobookService = new HerobookService(herobookRepository);
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

	private Hero heroContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Hero hero = mapper.readValue(new File(heroPath), Hero.class);
		return hero;

	}

}
