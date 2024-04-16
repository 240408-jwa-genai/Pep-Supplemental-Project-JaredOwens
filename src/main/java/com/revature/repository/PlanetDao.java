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

	//TODO: Verify the else statement is what catches if the user is verified to see the planet
	public Planet getPlanetByName(int ownerId, String planetName) {
		try(Connection connection = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM planets WHERE name = ? AND ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ps.setInt(2, ownerId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
				return planet;
			}
			else{
				System.out.println("No planet found with that name under your account");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}

	//TODO: Verify the else statement is what catches if the user is verified to see the planet
	public Planet getPlanetById(int planetId, int id) {
		try(Connection connection = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM planets WHERE id = ? AND ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
				return planet;
			}
			else{
				System.out.println("No planet found with that id under your account");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	//TODO: DO NOT ALLOW SAME NAME CREATION
	//	- make sure of requirement for this but breaks code if you don't anyways
	public Planet createPlanet(Planet p) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql1 = "Select * from planets where name = ?";
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, p.getName());
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				System.out.println("Planet already exists. Please select a different name.");
				return null;
			}
			else {
				String sql2 = "INSERT INTO planets (name, ownerId) VALUES (?,?)";
//					"SELECT @name " +
//					"WHERE NOT EXISTS (SELECT * FROM planets WHERE name = @name)";
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				ps2.setString(1, p.getName());
				ps2.setInt(2, p.getOwnerId());
				ps2.execute();
			}
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
