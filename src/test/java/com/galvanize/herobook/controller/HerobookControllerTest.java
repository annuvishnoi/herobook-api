package com.galvanize.herobook.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.galvanize.herobook.service.HerobookService;

@WebMvcTest
public class HerobookControllerTest {
	
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
	

}
