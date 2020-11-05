package com.techelevator.dao;

import java.util.List;

import com.techelevator.npgeek.Forecast;

public interface ForecastDAO {
	
	public List<Forecast> getForecastByParkCode(String parkCode);

}
