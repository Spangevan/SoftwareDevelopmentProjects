package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ForecastDAO;
import com.techelevator.npgeek.Forecast;

@Component
public class JDBCForecastDAO implements ForecastDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCForecastDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	

	@Override
	public List<Forecast> getForecastByParkCode(String parkCode) {
		List<Forecast> weather = new ArrayList<Forecast>();
		
		String sql = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		
		while(results.next()) {
			weather.add(mapRowToForecast(results));
		}
		
		return weather;
	}
	
	
	private Forecast mapRowToForecast(SqlRowSet results) {
		Forecast weather = new Forecast();
		weather.setParkCode(results.getString("parkcode"));
		weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setLow(results.getInt("low"));
		weather.setHigh(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		
		return weather;
		
	}
	
	
	

}
