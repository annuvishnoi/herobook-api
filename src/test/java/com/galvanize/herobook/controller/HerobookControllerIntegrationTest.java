package com.galvanize.herobook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.repository.HerobookRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HerobookControllerIntegrationTest {
	
	private String heroPath = "src/test/resources/hero.json";
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	HerobookRepository repository;
	
		
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
		
		repository.save(new Hero("Spider Man"));
		
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
		
		repository.save(new Hero("Batman"));	
		
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
	
		repository.save(heroContent());
		
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
	
	private Hero heroContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Hero hero = mapper.readValue(new File(heroPath), Hero.class);
		return hero;
		
	}

}
