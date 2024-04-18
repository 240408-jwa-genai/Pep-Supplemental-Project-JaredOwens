package com.revature.controller;

import com.revature.models.Moon;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public List<Moon> getAllMoons(int currentUserID) {
		return moonService.getAllMoons(currentUserID);
	}

	public void getMoonByName(int currentUserID, String name) {
		moonService.getMoonByName(currentUserID, name);
	}

	public boolean getMoonById(int currentUserID, int id) {
		return moonService.getMoonById(currentUserID, id);
	}

	public void createMoon(int currentUserID, Moon moon) {
		//TODO: Check if need currentUserId

		moonService.createMoon(currentUserID, moon);
	}

	public void deleteMoon(int currentUserID, int id) {
		if(getMoonById(currentUserID, id)){
			moonService.deleteMoonById(currentUserID, id);
		}
		//moonService.checkMoonOwnership(..)
		//calls db to check that persons moons then if exists can delete
	}
	
	public void getPlanetMoons(int currentUserID, int planetId) {
		moonService.getMoonsFromPlanet(currentUserID, planetId);
	}
}
