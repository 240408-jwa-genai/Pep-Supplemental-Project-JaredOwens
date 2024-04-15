package com.revature.repository;

import java.util.List;

import com.revature.models.Planet;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() {
		// TODO: implement
		return null;
	}

	public Planet getPlanetByName(String planetName) {
		// TODO: implement
		return null;			
	}

	public Planet getPlanetById(int planetId) {
		// TODO: implement
		return null;
	}

	// REQUIREMENT: Planets should be “owned” by the user that added it to the Planetarium
		//this means grabbing current userID
	public Planet createPlanet(Planet p) {
		// TODO: implement
		return null;
	}

	public boolean deletePlanetById(int planetId) {
		// TODO: implement
		return false;
	}

	public static void main(String[] args) {
		//TODO: USE THIS FOR TESTING THE PLANETS DB SIMILAR TO UserDao IMPLEMENTATION
		PlanetDao pd = new PlanetDao();
	}
}
