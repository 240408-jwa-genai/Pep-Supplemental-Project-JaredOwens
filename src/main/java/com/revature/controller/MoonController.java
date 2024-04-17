package com.revature.controller;

import com.revature.models.Moon;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public List<Moon> getAllMoons(int currentUserId) {
		return moonService.getAllMoons();
	}

	public void getMoonByName(int currentUserId, String name) {
		moonService.getMoonByName(currentUserId, name);
	}

	public void getMoonById(int currentUserId, int id) {
		moonService.getMoonById(id);
	}

	public void createMoon(int currentUserId, Moon moon) {
		//TODO: Check if need currentUserId
		moonService.createMoon(moon);
	}

	public void deleteMoon(int id) {
		// TODO: implement
	}
	
	public void getPlanetMoons(int myPlanetId) {
		// TODO: implement
	}
}
