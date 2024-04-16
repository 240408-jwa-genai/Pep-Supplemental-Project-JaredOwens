package com.revature.controller;

import com.revature.models.Planet;
import com.revature.service.PlanetService;

import java.util.List;

public class PlanetController {
	
	private PlanetService planetService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public List<Planet> getAllPlanets(int currentUserId) {
		return planetService.getAllPlanets(currentUserId);
	}

	public void getPlanetByName(int currentUserId, String name) {
		// TODO: implement
	}

	public void getPlanetByID(int currentUserId, int id) {
		// TODO: implement
	}

	public void createPlanet(int currentUserId, Planet planet) {
		planetService.createPlanet(currentUserId, planet);
	}

	public void deletePlanet(int planetId) {
		planetService.deletePlanetById(planetId);
	}
}
