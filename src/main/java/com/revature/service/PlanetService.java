package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int currentUserId) {
		return dao.getAllPlanets(currentUserId);
	}

	public void getPlanetByName(int ownerId, String planetName) {
		// TODO probably change to void and print the planet here
		Planet planetResult = dao.getPlanetByName(ownerId, planetName);
		System.out.println(planetResult);
	}

	public void getPlanetById(int ownerId, int planetId) {
		Planet planetResult = dao.getPlanetById(ownerId, planetId);
		System.out.println(planetResult);
	}

	public void createPlanet(int ownerId, Planet planet) {
		dao.createPlanet(planet);
		System.out.println("Planet created! Details: " + planet.toString());
	}

	public void deletePlanetById(int planetId) {
		boolean deleted = dao.deletePlanetById(planetId);
		if(deleted)
			System.out.println("Planet deleted!");
		else
			System.out.println("Planet could not be deleted!");
	}
}
