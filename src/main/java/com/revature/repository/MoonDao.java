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
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		// TODO: implement
		return null;
	}

	public Moon getMoonByName(String moonName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM moons WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
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

	public Moon getMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM moons WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
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

	public Moon createMoon(Moon m) {
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
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

	public boolean deleteMoonById(int moonId) {
		// TODO: implement
		return false;
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		// TODO: implement
		return null;
	}
}
