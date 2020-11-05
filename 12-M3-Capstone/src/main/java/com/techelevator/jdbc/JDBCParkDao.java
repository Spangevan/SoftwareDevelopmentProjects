package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ParkDAO;
import com.techelevator.npgeek.Park;

@Component
public class JDBCParkDao implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park ORDER BY parkname ASC";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			Park p = mapRowToPark(results);
			parks.add(p);
		}

		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park returnPark = null;

		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);

		if (results.next()) {
			returnPark = mapRowToPark(results);
		}

		return returnPark;
	}

	@Override
	public List<Park> showSurveyPark() { // returns parks that have recieved a survey entry in the DB
		List<Park> showSurvey = new ArrayList<Park>();

		String sql = "SELECT parkname, park.parkcode, count(parkname) AS totalVotes FROM park JOIN survey_result ON park.parkcode = survey_result.parkcode GROUP BY parkname,park.parkcode ORDER BY totalVotes Desc, parkname ASC";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		while(result.next()) {
			Park p = mapRowToParkSurvey(result);
			showSurvey.add(p);
		}
			
		return showSurvey;
	}

	@Override
	public List<Park> showResultsBySearch(String search) { // sql code that allows user input to search database and return specific entries that contain the input
		List<Park> showSearchResult = new ArrayList<Park>();
		
		String sql ="SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park "
				+ "WHERE parkcode = ? "
				+ "OR parkname LIKE ? OR state = ? OR parkdescription LIKE ?"
				+ "OR climate = ? OR inspirationalquote LIKE ? OR inspirationalquotesource LIKE ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql,"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%"); // each search variable represents a different possible search possible search by parameter. surrounded by the % for the contains functionality of SQL 
		
		while(result.next()) {
			showSearchResult.add(mapRowToPark(result));
		}
		
		return showSearchResult;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park parks = new Park();
		parks.setParkCode(results.getString("parkcode"));
		parks.setParkName(results.getString("parkname"));
		parks.setState(results.getString("state"));
		parks.setAcreage(results.getInt("acreage"));
		parks.setElevationInFeet(results.getInt("elevationinfeet"));
		parks.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		parks.setEntryFee(results.getDouble("entryfee"));
		parks.setMilesOfTrail(results.getDouble("milesoftrail"));
		parks.setInspirationalQuote(results.getString("inspirationalquote"));
		parks.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		parks.setParkDescription(results.getString("parkdescription"));
		parks.setYearFounded(results.getInt("yearfounded"));
		parks.setClimate(results.getString("climate"));
		parks.setNumberOfCampsites(results.getInt("numberofcampsites"));
		parks.setInspirationalQuoteSource(results.getString("inspirationalQuoteSource"));

		return parks;
	}
	
	private Park mapRowToParkSurvey(SqlRowSet results) {
		Park park = new Park(); 
		park.setParkName(results.getString("parkname"));
		park.setParkCode(results.getString("parkcode"));
		park.setVotes(results.getInt("totalVotes"));
		return park;
	}

}
