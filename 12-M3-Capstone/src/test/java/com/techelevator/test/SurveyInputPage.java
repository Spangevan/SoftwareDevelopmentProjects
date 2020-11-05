package com.techelevator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SurveyInputPage {

	private WebDriver webDriver;

	public SurveyInputPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyInputPage chooseFavPark(String parkName) {
		Select selectField = new Select(webDriver.findElement(By.id("parkCode")));
		selectField.selectByVisibleText(parkName);
		return this;
	}
	
	public SurveyInputPage enterAnEmail(String emailAddress) {
		WebElement amountField = webDriver.findElement(By.id("emailAddress"));
		amountField.sendKeys(emailAddress);
		return this;
	}
	
	public SurveyInputPage chooseState(String state) {
		Select selectField = new Select(webDriver.findElement(By.id("state")));
		selectField.selectByVisibleText(state);
		return this;
	}
	
	public SurveyInputPage chooseActivityLevel(String activityLevel) {
		WebElement amountField = webDriver.findElement(By.id("activityLevel"));
		amountField.click();
		return this;
	}
	
	public SurveyResultPage submitForm() {
		WebElement submitForm = webDriver.findElement(By.id("submit"));
		submitForm.click();
		return new SurveyResultPage(webDriver);
	}
	
}
