package com.galvanize.herobook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.galvanize.herobook.model.Hero;
import com.galvanize.herobook.repository.HerobookRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HerobookControllerIntegrationTest {
	
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
		.andExpect(jsonPath("$.data").isNotEmpty())
		.andExpect(jsonPath("$.data[0]").value("Spider Man"));
	}
	
	@Test
	@Order(2)
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
	

}
