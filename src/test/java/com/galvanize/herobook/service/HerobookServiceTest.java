package com.galvanize.herobook.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.repository.HerobookRepository;

public class HerobookServiceTest {

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
	
}
