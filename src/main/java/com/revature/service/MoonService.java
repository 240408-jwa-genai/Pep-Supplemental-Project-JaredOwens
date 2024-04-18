package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int currentUserID) {
		//TODO: check if is user is currently authorized

		return dao.getAllMoons(currentUserID);
	}

	public void getMoonByName(int currentUserID, String moonName) {
		//TODO: check if is user is currently authorized

		Moon moonResult = dao.getMoonByName(currentUserID, moonName);
		if(moonResult != null)
			System.out.println(moonResult);
		else
			System.out.println("No moon found under this name for the current user.");
	}

	public boolean getMoonById(int currentUserID, int moonId) {
		//TODO: check if is user is currently authorized

		Moon moonResult = dao.getMoonById(currentUserID, moonId);
		if (moonResult != null) {
			System.out.println(moonResult);
			return true;
		}
		else{
			System.out.println("No moon found under this id for the current user.");
			return false;
		}
	}

	public void createMoon(int currentUserID, Moon moon) {
		//TODO: check if is user is currently authorized

		if(moon.getName().length() <= 30){
			Moon result = dao.createMoon(currentUserID, moon);
			if(result != null){
				System.out.println("Moon created! Details: " + result.toString());
			}
		}
		else{
			System.out.println("Moon name too long! Please only have 30 or less characters!");
		}
	}

	public boolean deleteMoonById(int currentUserID, int moonId) {
		//TODO: check if is user is currently authorized
		boolean deleted = dao.deleteMoonById(currentUserID, moonId);
		if(deleted)
			System.out.println("Moon deleted!");
		else
			System.out.println("Moon could not be deleted!");
		return false;
	}

	public void getMoonsFromPlanet(int currentUserID, int planetId) {
		//TODO: check if is user is currently authorized

		// TODO Auto-generated method stub
		List<Moon> moons = dao.getMoonsFromPlanet(currentUserID, planetId);
		if(!moons.isEmpty()){
			for(Moon moon : moons){
				System.out.println(moon);
			}
		}
		else{
			System.out.println("No moons found under this Planet for the current user.");
		}
	}
}
