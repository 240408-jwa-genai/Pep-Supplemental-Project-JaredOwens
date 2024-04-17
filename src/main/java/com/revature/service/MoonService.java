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

	public List<Moon> getAllMoons() {
		return dao.getAllMoons();
	}

	public void getMoonByName(int currentUserID, String moonName) {
		Moon moonResult = dao.getMoonByName(currentUserID, moonName);
		if(moonResult != null)
			System.out.println(moonResult);
		else
			System.out.println("No moon found under this name for the current user.");
	}

	public void getMoonById(int currentUserID, int moonId) {
		Moon moonResult = dao.getMoonById(currentUserID, moonId);
		if(moonResult != null)
			System.out.println(moonResult);
		else
			System.out.println("No moon found under this id for the current user.");
	}

	public void createMoon(Moon moon) {
		if(moon.getName().length() <= 30){
			Moon result = dao.createMoon(moon);
			if(result != null){
				System.out.println("Moon created! Details: " + result.toString());
			}
		}
		else{
			System.out.println("Moon name too long! Please only have 30 or less characters!");
		}
	}

	public boolean deleteMoonById(int moonId) {
		return false;
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		// TODO Auto-generated method stub
		return null;
	}
}
