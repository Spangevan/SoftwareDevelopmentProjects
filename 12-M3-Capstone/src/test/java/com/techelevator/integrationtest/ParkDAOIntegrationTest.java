package com.techelevator.integrationtest;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.dao.ParkDAO;
import com.techelevator.jdbc.JDBCParkDao;

public class ParkDAOIntegrationTest extends DAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private ParkDAO parkDao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
	dataSource = (SingleConnectionDataSource) super.getDataSource();
	parkDao = new JDBCParkDao(dataSource);
	jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
//	@BeforeClass
//	public static void setupDataSource() {
//		dataSource = new SingleConnectionDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
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
//		parkDao = new JDBCParkDao(dataSource);
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
	public void get_all_parks() {
		int originalSize = parkDao.getAllParks().size();
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		Assert.assertEquals("Wrong number of Parks", originalSize + 1, parkDao.getAllParks().size());
		
	}
	
	@Test
	public void get_park_by_parkcode() {
		insertPark("k", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		Assert.assertNotNull("Wrong number of Parks Returned", parkDao.getParkByParkCode("k"));	
	}
	
	@Test 
	public void get_park_by_invalid_parkcode() {
		Assert.assertNull("Wrong number of Parks Returned", parkDao.getParkByParkCode("t"));	
	}
	
	@Test
	public void get_count_of_surveys() {
		int originalSize = parkDao.showSurveyPark().size();
		insertPark("t", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		insertSurvey("t", "Tom@aol.com", "OH", "Tom Hanks");
		Assert.assertEquals("Wrong number of Survey", originalSize + 1, parkDao.showSurveyPark().size());
		
	}
	
	@Test 
	public void search_park_by_parkcode() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1234", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 0, parkDao.showResultsBySearch("1234").size());
	}
	
	@Test
	public void search_park_by_parkname() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1234", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 1, parkDao.showResultsBySearch("1234").size());

	}
	
	@Test
	public void search_park_by_state() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1", "1234", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 0, parkDao.showResultsBySearch("1234").size());

	}
	
	@Test
	public void search_park_by_climate() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1234", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 0, parkDao.showResultsBySearch("1234").size());
	}
	
	@Test
	public void search_park_by_quote() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1", "1234", 1, 1, 1.0, 1, "1", 1, 1, "1234", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 1, parkDao.showResultsBySearch("1234").size());
	}
	
	@Test
	public void search_park_by_quotesource() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1", "1234", 1, 1, 1.0, 1, "1", 1, 1, "1", "1234", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 1, parkDao.showResultsBySearch("1234").size());
	}
	
	@Test
	public void search_park_by_park_description() {
		int originalSize = parkDao.showResultsBySearch("1234").size();
		insertPark("1", "1", "1234", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1234", 1, 1);
		Assert.assertEquals("Wrong number of Parks", originalSize + 1, parkDao.showResultsBySearch("1234").size());
	}
	
	@Test
	public void no_parks_returned_from_invalid_search() {
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks", 0, parkDao.showResultsBySearch("1234").size());
	}
	
	private void insertPark(String parkCode, String parkName, String state, int acreage, int elevation, double milesOfTrail, int campsites, String climate, int yearFounded, int annualVisitors, String quote, String quoteSource, String description, int fee, int animals) {
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, "
				+ "elevationinfeet, milesoftrail, numberofcampsites, climate, "
				+ "yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, "
				+ "parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		jdbcTemplate.update(sql,parkCode, parkName, state, acreage, elevation, milesOfTrail, campsites, climate, yearFounded, annualVisitors, quote, quoteSource, description, fee, animals);
	}
	
	private void insertSurvey(String parkCode, String email, String state, String activityLevel) {
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, parkCode, email, state, activityLevel);
	}

}
