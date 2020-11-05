package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.FavoriteParkDAO;
import com.techelevator.npgeek.FavoritePark;

@Component
public class JDBCFavoriteParkDAO implements FavoriteParkDAO{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCFavoriteParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<FavoritePark> getFavoriteParks() { // selects the count of park names from the survey result table
		List<FavoritePark> allSurveyedParks = new ArrayList<>();
		String sql = "SELECT COUNT(park.parkname), park.parkname, park.parkcode FROM survey_result " + 
				"JOIN PARK ON survey_result.parkcode = park.parkcode " + 
				"GROUP BY park.parkname, park.parkcode ORDER BY COUNT DESC";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while(results.next()) {
			FavoritePark park = new FavoritePark();
			park.setCount(results.getInt("count"));
			park.setParkName(results.getString("parkname"));
			park.setParkCode(results.getString("parkcode"));
			allSurveyedParks.add(park);

		}
		return allSurveyedParks;
	}

}