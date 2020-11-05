package com.techelevator.dao;

import java.util.List;

import com.techelevator.npgeek.Survey;

public interface SurveyDAO {
	
	public List<Survey> getAllSurveys();
	
	public void saveSurvey(Survey newSurvey);

}
