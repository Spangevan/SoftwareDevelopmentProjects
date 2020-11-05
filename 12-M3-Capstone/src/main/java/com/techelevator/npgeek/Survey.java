package com.techelevator.npgeek;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {

	private int id;
	private String parkCode;
	@NotBlank(message = "*")
	@Email(message = "This field is required")
	private String emailAddress;
	private String state;
	@NotBlank(message = "This field is required")
	private String activityLevel;
	private String parkName;
	private int surveyCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	public int getSurveyCount() {
		return surveyCount;
	}

	public void setSurveyCount(int surveyCount) {
		this.surveyCount = surveyCount;
	}

}
