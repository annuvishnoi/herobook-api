package com.galvanize.herobook.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galvanize.herobook.response.HerobookResponse;

@RestController
@RequestMapping("/api/heroes")
public class HerobookController {

	@GetMapping
	public HerobookResponse getHeroes(
			@RequestParam(defaultValue = "visitor", required = false, name = "role") String role) {
		HerobookResponse response = new HerobookResponse();
		response.setData(new ArrayList<>());
		return response;
	}
}
