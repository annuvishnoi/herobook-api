package com.galvanize.herobook.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.herobook.exception.HerobookException;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.model.Villain;
import com.galvanize.herobook.repository.HeroRepository;
import com.galvanize.herobook.repository.VillainRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HerobookControllerIntegrationTest {
	
	private String heroPath = "src/test/resources/hero.json";
	private String villainPath = "src/test/resources/villain.json";
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	HeroRepository heroRepository;
	
	@Autowired
	VillainRepository villainRepository;
	
		
	@Test
	@Order(1)
	public void test_getHeroes_AsVisitor_ReturnsEmptyList() throws Exception {
		mockMvc.perform(
				get("/api/heroes")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(0));
	}
	
	@Test
	@Order(2)
	public void test_getHeroes_AsVisitor_ReturnsSingleHero() throws Exception {
		
		heroRepository.save(new Hero("Spider Man"));
		
		mockMvc.perform(
				get("/api/heroes")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(1));
	}
	
	@Test
	@Order(3)
	public void test_getHeroes_AsVisitor_ReturnsMultipleHero() throws Exception {
		
		heroRepository.save(new Hero("Batman"));	
		
		mockMvc.perform(
				get("/api/heroes")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(2));
	}
	
	@Test
	@Order(4)
	public void test_getHero_asVisitor_returnsHeroDetail() throws Exception {		
	
		heroRepository.save(heroContent());
		
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
		
		
	}
	
	@Test
	@Order(5)
	public void test_getHero_asVisitor_returnsHeroNotFound() throws Exception {			
		mockMvc.perform(
				get("/api/heroes/{heroName}","Jonathan")
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.data").isEmpty())		
		.andExpect(jsonPath("$.errorMessages.length()").value(1))
		.andExpect(jsonPath("$.errorMessages[0]").value("Hero not found"));		
	
	}
	
	@Test
	@Order(6)
	public void test_getVillains_AsVisitor_ReturnsEmptyList() throws Exception {
		mockMvc.perform(
				get("/api/villains")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(0));
	}
	
	@Test
	@Order(7)
	public void test_getVillains_AsVisitor_ReturnsMultipleVillain() throws Exception {
		
		villainRepository.save(new Villain("Batman Villian"));
		villainRepository.save(new Villain("Spider Man Villian"));	
		
		mockMvc.perform(
				get("/api/villains")
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.length()").value(2));
	}
	
	
	
	@Test
	@Order(8)
	public void test_getVillain_asVisitor_returnsVillainDetail() throws Exception {		
	
		villainRepository.save(villainContent());
		
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
		
	}
	
	@Test
	@Order(9)
	public void test_getVillain_asVisitor_returnsVillainNotFound() throws Exception {		
		
		mockMvc.perform(
				get("/api/villains/{villainName}","Jonathan")
				)
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.data").isEmpty())		
		.andExpect(jsonPath("$.errorMessages.length()").value(1))
		.andExpect(jsonPath("$.errorMessages[0]").value("Villain not found"));
		
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
