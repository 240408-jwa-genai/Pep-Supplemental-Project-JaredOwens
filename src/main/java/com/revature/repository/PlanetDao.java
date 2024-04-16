package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets(int currentUserId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			// craft initial sql
			String sql = "SELECT * FROM planets WHERE ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, currentUserId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Planet> planets = new ArrayList<>();
			while (rs.next()) {
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(currentUserId);
				planets.add(planet);
			}
			return planets;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
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
		try (Connection connection = ConnectionUtil.createConnection()) {
			// craft initial sql
			String sql = "INSERT INTO planets (name, ownerId) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setInt(2, p.getOwnerId());
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return null;
		//probably change return type to void OR print out the planet that was made in the service
	}

	public boolean deletePlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM planets WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ps.execute();
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		//TODO: USE THIS FOR TESTING THE PLANETS DB SIMILAR TO UserDao IMPLEMENTATION
		PlanetDao pd = new PlanetDao();
	}
}
