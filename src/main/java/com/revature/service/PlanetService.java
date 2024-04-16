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

	public Planet getPlanetByName(int ownerId, String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createPlanet(int ownerId, Planet planet) {
		dao.createPlanet(planet);
		//TODO: Print the planet that was created
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
