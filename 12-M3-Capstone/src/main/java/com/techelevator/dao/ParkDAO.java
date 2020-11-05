package com.techelevator.dao;

import java.util.List;

import com.techelevator.npgeek.Park;

public interface ParkDAO {

	public List<Park> getAllParks();
	
	public Park getParkByParkCode(String parkCode);
	
	public List<Park> showSurveyPark();
	
	public List<Park> showResultsBySearch(String search);
	
	
}
