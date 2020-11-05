package com.techelevator.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.SurveyDAO;
import com.techelevator.npgeek.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override 
	public void saveSurvey(Survey newSurvey) { // inserts a new survey

		String sqlInsertSurvey = "INSERT INTO survey_result VALUES (DEFAULT, ?, ?, ?, ?) RETURNING surveyid";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInsertSurvey, newSurvey.getParkCode(), newSurvey.getEmailAddress(), newSurvey.getState(), newSurvey.getActivityLevel());
		
		if (results.next()) {
			int surveyId = results.getInt("surveyid");
		newSurvey.setId(surveyId);
		}
		
	}

	

	@Override
	public List<Survey> getAllSurveys() { // returns all surveys
		List<Survey> allSurveys = new ArrayList<>();
		String sqlSearchAllSurveys = "SELECT * FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchAllSurveys);
		while(results.next()) {
			Survey survey = new Survey();
			survey.setId(results.getInt("surveyid"));
			survey.setParkCode(results.getString("parkcode"));
			survey.setEmailAddress(results.getString("emailaddress"));
			survey.setState(results.getString("state"));
			survey.setActivityLevel(results.getString("activitylevel"));
			allSurveys.add(survey);
		}
		return allSurveys;
	}

}
