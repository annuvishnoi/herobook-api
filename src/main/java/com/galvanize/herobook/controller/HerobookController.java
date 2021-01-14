package com.galvanize.herobook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.herobook.exception.HerobookException;
import com.galvanize.herobook.response.HerobookResponse;
import com.galvanize.herobook.service.HerobookService;

@RestController
@RequestMapping("/api/")
public class HerobookController {
	
	HerobookService herobookService;
	
	public HerobookController(HerobookService herobookService) {
		this.herobookService = herobookService;
	}

	@GetMapping("heroes")
	public HerobookResponse getHeroes(
			@RequestParam(defaultValue = "visitor", required = false, name = "role") String role) {
		HerobookResponse response = new HerobookResponse();
		response.setData(herobookService.getHeroes());
		return response;
	}
	
	@GetMapping("heroes/{heroName}")
	public HerobookResponse getHeroByName(
			@PathVariable String heroName,
			@RequestParam(defaultValue = "visitor", required = false, name = "role") String role) throws HerobookException {
		HerobookResponse response = new HerobookResponse();
		response.setData(herobookService.getHeroDetails(heroName));
		return response;
	}
	
	@GetMapping("villains")
	public HerobookResponse getVillains(
			@RequestParam(defaultValue = "visitor", required = false, name = "role") String role) {
		HerobookResponse response = new HerobookResponse();
		response.setData(herobookService.getVillains());
		return response;
	}
}


