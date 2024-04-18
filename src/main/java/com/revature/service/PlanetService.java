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
		//TODO: check if is user is currently authorized
		return dao.getAllPlanets(currentUserId);
	}

	public void getPlanetByName(int ownerId, String planetName) {
		//TODO: check if is user is currently authorized

		Planet planetResult = dao.getPlanetByName(ownerId, planetName);
		if(planetResult != null)
			System.out.println(planetResult);
		else
			System.out.println("No planet found with that name under your account.");
	}

	public void getPlanetById(int ownerId, int planetId) {
		//TODO: check if is user is currently authorized

		Planet planetResult = dao.getPlanetById(ownerId, planetId);
		if(planetResult != null)
			System.out.println(planetResult);
		else
			System.out.println("No planet found with that id under your account.");
	}

	public void createPlanet(int ownerId, Planet planet) {
		//TODO: check if is user is currently authorized

		if(planet.getName().length() <= 30){
			Planet result = dao.createPlanet(planet);
			if(result != null){
				System.out.println("Planet created! Details: " + result.toString());
			}
		}
		else{
			System.out.println("Planet name too long! Please only have 30 or less characters!");
		}
	}

	public void deletePlanetById(int planetId) {
		boolean deleted = dao.deletePlanetById(planetId);
		if(deleted)
			System.out.println("Planet deleted!");
		else
			System.out.println("Planet could not be deleted!");
	}
}
