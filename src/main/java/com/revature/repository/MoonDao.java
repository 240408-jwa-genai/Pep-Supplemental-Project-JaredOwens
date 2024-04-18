package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons(int currentUserID) {
		try (Connection connection = ConnectionUtil.createConnection()) {

			//String sql = "SELECT * FROM planets WHERE ownerId = ?";
			String sql = "SELECT * FROM moons m " +
			"JOIN planets p ON m.myPlanetId = p.id " +
			"WHERE p.ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, currentUserID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Moon> moons = new ArrayList<>();
			while (rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moons.add(moon);
			}
			return moons;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public Moon getMoonByName(int currentUserID, String moonName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM moons m " +
					"JOIN planets p ON m.myPlanetId = p.id " +
					"WHERE m.name = ? " +
					"AND p.ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ps.setInt(2, currentUserID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				return moon;
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
	}

	public Moon getMoonById(int currentUserID, int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM moons m " +
					"JOIN planets p ON m.myPlanetId = p.id " +
					"WHERE m.id = ? " +
					"AND p.ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ps.setInt(2, currentUserID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				return moon;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Moon createMoon(int currentUserID, Moon m) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sqlCheck = "Select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sqlCheck);
			ps.setString(1, m.getName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Moon already exists");
				return null;
			}
			else{
				//check that planet to be inserted exists
				String sqlCheck2 = "select * from planets p where p.id = ? " +
						"AND p.ownerId = ?";
				PreparedStatement ps2 = connection.prepareStatement(sqlCheck2);
				ps2.setInt(1, m.getMyPlanetId());
				ps2.setInt(2, currentUserID);
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					String sqlInsert = "Insert into moons (name, myPlanetId) values (?, ?) returning *";
					PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
					psInsert.setString(1, m.getName());
					psInsert.setInt(2, m.getMyPlanetId());
					psInsert.execute();
					ResultSet rsInsert = psInsert.getResultSet();
					if(rsInsert.next()) {
						Moon moon = new Moon();
						moon.setId(rsInsert.getInt("id"));
						moon.setName(rsInsert.getString("name"));
						moon.setMyPlanetId(rsInsert.getInt("myPlanetId"));
						return moon;
					}
				}
				else{
					System.out.println("Planet does not exist or you do not have access to the planet");
				}
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

	public boolean deleteMoonById(int currentUserID, int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			//String sql = "DELETE FROM planets WHERE id = ?";
			String sql = "delete from moons " +
					"where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ps.execute();
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int currentUserID, int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM moons m " +
					"JOIN planets p ON m.myPlanetId = p.id " +
					"WHERE p.ownerId = ? AND p.id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, currentUserID);
			ps.setInt(2, planetId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Moon> moons = new ArrayList<>();
			while (rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moons.add(moon);
			}
			return moons;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
