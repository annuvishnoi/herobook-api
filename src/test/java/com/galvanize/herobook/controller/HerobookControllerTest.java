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
import com.galvanize.herobook.exception.HerobookException;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.model.Villain;
import com.galvanize.herobook.service.HerobookService;

@WebMvcTest
public class HerobookControllerTest {
	
	private String heroPath = "src/test/resources/hero.json";
	private String villainPath = "src/test/resources/villain.json";
	
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
		.andExpect(jsonPath("$.data.agility").value("High"))
		.andExpect(jsonPath("$.data.image").value("/src/heroImage.jpg"))
		.andExpect(jsonPath("$.data.intelligence").value("Spider Sense"))
		.andExpect(jsonPath("$.data.strength").value("UnKnown"))
		.andExpect(jsonPath("$.data.power").value("Web"))
		.andExpect(jsonPath("$.data.description").value("Hero with spider strength"))
		.andExpect(jsonPath("$.data.story").value("A long Story"))
		.andExpect(jsonPath("$.data.speed").value("High"));
		
		verify(herobookService).getHeroDetails(Mockito.anyString());
	}
	
	@Test
	public void test_getHero_asVisitor_returnsHeroNotFound() throws Exception {		
	
		when(herobookService.getHeroDetails(Mockito.anyString())).thenThrow(new HerobookException("Hero not found"));
		
		mockMvc.perform(
				get("/api/heroes/{heroName}","Jonathan")
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.data").isEmpty())		
		.andExpect(jsonPath("$.errorMessages.length()").value(1))
		.andExpect(jsonPath("$.errorMessages[0]").value("Hero not found"));
		
		verify(herobookService).getHeroDetails(Mockito.anyString());
	}
	
	@Test
	public void test_getVillains_AsVisitor_returnsZeroVillains() throws Exception {
		
		List<String> villains = new ArrayList<>();
		
		when(herobookService.getVillains()).thenReturn(villains);
		
		mockMvc.perform(
				get("/api/villains")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(0));
		
		verify(herobookService).getVillains();
	}
	
	@Test
	public void test_getVillains_AsVisitor_returnsMultipleVillains() throws Exception {
		
		List<String> villains = new ArrayList<>();
		villains.add("Spider Man Villain");
		villains.add("Robo Cop Villain");
		
		when(herobookService.getVillains()).thenReturn(villains);
		
		mockMvc.perform(
				get("/api/villains")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(2));
		
		verify(herobookService).getVillains();
	}
	
	
	@Test
	public void test_getVillain_asVisitor_returnsVillainDetail() throws Exception {		
	
		when(herobookService.getVillainDetails(Mockito.anyString())).thenReturn(villainContent());
		
		mockMvc.perform(
				get("/api/villains/{villainName}","Greengoblin")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.villainName").value("Greengoblin"))
		.andExpect(jsonPath("$.data.realName").value("Osbourn"))
		.andExpect(jsonPath("$.data.weight").value("70 Kg"))
		.andExpect(jsonPath("$.data.height").value("180 cm"))
		.andExpect(jsonPath("$.data.specialPower").value("Web"))
		.andExpect(jsonPath("$.data.agility").value("High"));
		
		
		
		verify(herobookService).getVillainDetails(Mockito.anyString());
	}
	
	@Test
	public void test_getVillain_asVisitor_returnsVillainNotFound() throws Exception {		
	
		when(herobookService.getVillainDetails(Mockito.anyString())).thenThrow(new HerobookException("Villain not found"));
		
		mockMvc.perform(
				get("/api/villains/{villainName}","Jonathan")
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.data").isEmpty())		
		.andExpect(jsonPath("$.errorMessages.length()").value(1))
		.andExpect(jsonPath("$.errorMessages[0]").value("Villain not found"));
		
		verify(herobookService).getVillainDetails(Mockito.anyString());
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
