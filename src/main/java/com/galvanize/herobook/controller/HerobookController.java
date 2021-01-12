package com.galvanize.herobook.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.herobook.response.HerobookResponse;
import com.galvanize.herobook.service.HerobookService;

@RestController
@RequestMapping("/api/heroes")
public class HerobookController {
	
	HerobookService herobookService;
	
	public HerobookController(HerobookService herobookService) {
		this.herobookService = herobookService;
	}

	@GetMapping
	public HerobookResponse getHeroes(
			@RequestParam(defaultValue = "visitor", required = false, name = "role") String role) {
		HerobookResponse response = new HerobookResponse();
		response.setData(herobookService.getHeroes());
		return response;
	}
}


