package com.galvanize.herobook.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.service.HerobookService;

@WebMvcTest
public class HerobookControllerTest {
	
	private String heroPath = "src/test/resources/hero.json";
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	HerobookService herobookService;
	
	@Test
	public void test_getHeroes_AsVisitor_returnsZeroHeroes() throws Exception {
		
		List<String> heroes = new ArrayList<>();
		
		when(herobookService.getHeroes()).thenReturn(heroes);
		
		mockMvc.perform(
				get("/api/heroes")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(0));
		
		verify(herobookService).getHeroes();
	}
	
	@Test
	public void test_getHeroes_AsVisitor_returnsMultipleHeroes() throws Exception {
		
		List<String> heroes = new ArrayList<>();
		heroes.add("Spider Man");
		heroes.add("Robo Cop");
		
		when(herobookService.getHeroes()).thenReturn(heroes);
		
		mockMvc.perform(
				get("/api/heroes")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(2));
		
		verify(herobookService).getHeroes();
	}
	
	@Test
	public void test_getHero_asVisitor_returnsHeroDetail() throws Exception {		
	
		when(herobookService.getHeroDetails(Mockito.anyString())).thenReturn(heroContent());
		
		mockMvc.perform(
				get("/api/heroes/{heroName}","SpiderMan")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.heroName").value("SpiderMan"))
		.andExpect(jsonPath("$.data.realName").value("Peter Parker"))
		.andExpect(jsonPath("$.data.weight").value("70 Kg"))
		.andExpect(jsonPath("$.data.height").value("180 cm"))
		.andExpect(jsonPath("$.data.specialPower").value("Web"))
		.andExpect(jsonPath("$.data.agility").value("High"));
		
		verify(herobookService).getHeroDetails(Mockito.anyString());
	}
	
	private Hero heroContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Hero hero = mapper.readValue(new File(heroPath), Hero.class);
		return hero;
		
	}
	

}
