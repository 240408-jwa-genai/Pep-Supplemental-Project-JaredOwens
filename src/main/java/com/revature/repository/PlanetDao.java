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
			throw new RuntimeException(e);
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
				return null;
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	//TODO: Verify the else statement is what catches if the user is verified to see the planet
	public Planet getPlanetById(int userID, int id) {
		try(Connection connection = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM planets WHERE id = ? AND ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, userID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
				return planet;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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
				String sql2 = "INSERT INTO planets (name, ownerId) VALUES (?,?) returning *";
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				ps2.setString(1, p.getName());
				ps2.setInt(2, p.getOwnerId());
				ps2.execute();
				ResultSet rs2 = ps2.getResultSet();
				if(rs2.next()) {
					Planet planet = new Planet();
					planet.setId(rs2.getInt("id"));
					planet.setName(rs2.getString("name"));
					planet.setOwnerId(rs2.getInt("ownerId"));
					return planet;
				}
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
