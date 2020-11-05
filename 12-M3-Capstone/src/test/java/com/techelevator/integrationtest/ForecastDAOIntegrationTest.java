package com.techelevator.integrationtest;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.dao.ForecastDAO;
import com.techelevator.jdbc.JDBCForecastDAO;
import com.techelevator.jdbc.JDBCParkDao;

public class ForecastDAOIntegrationTest extends DAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private ForecastDAO forecastDAO;
	private JdbcTemplate jdbcTemplate; 
	
	@Before
	public void setup() {
	dataSource = (SingleConnectionDataSource) super.getDataSource();
	forecastDAO = new JDBCForecastDAO(dataSource);
	jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	@BeforeClass 
//	public static void setupDataSource() {
//		dataSource = new SingleConnectionDataSource();
//		dataSource.setUrl("jdbc::postgressql://localhost:5432/npgeek");
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("postgres1");
//		dataSource.setAutoCommit(false);
//	}
//	
//	@AfterClass 
//	public static void closeDataSource() throws SQLException {
//		dataSource.destroy();
//	}
//	
//	@Before
//	public void before() {
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		forecastDAO = new JDBCForecastDAO(dataSource);
//	}
//
//	@After
//	public void rollback() throws SQLException {
//		dataSource.getConnection().rollback();
//	}
//	
//	public DataSource getDataSource() {
//		return dataSource;
//	}
	
	@Test
	public void get_forecast_for_valid_park() {
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		insertForecast("1", 1, 1, 1, "Slippery When Wet" ); 	
		Assert.assertEquals("Wrong number of Weather", 1, forecastDAO.getForecastByParkCode("1").size());
	}
	
	@Test 
	public void get_forecast_for_invalid_park() {
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		insertForecast("1", 1, 1, 1, "Slippery When Wet" ); 	
		Assert.assertEquals("Wrong number of Weather", 0, forecastDAO.getForecastByParkCode("2").size());		
	}
	
	private void insertPark(String parkCode, String parkName, String state, int acreage, int elevation, double milesOfTrail, int campsites, String climate, int yearFounded, int annualVisitors, String quote, String quoteSource, String description, int fee, int animals) {
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, "
				+ "milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, "
				+ "inspirationalquote, inspirationalquotesource, parkdescription, entryfee, "
				+ "numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,parkCode, parkName, state, acreage, elevation, milesOfTrail, campsites, climate, yearFounded, annualVisitors, quote, quoteSource, description, fee, animals);
	}
	
	private void insertForecast(String parkCode, int forecastValue, int low, int high, String forecast) {
		String sql = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, parkCode, forecastValue, low, high, forecast);
	}

}
