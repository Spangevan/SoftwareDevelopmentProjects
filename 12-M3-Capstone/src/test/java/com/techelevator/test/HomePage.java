package com.techelevator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver webDriver;
	
	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyInputPage clickSurveyLink() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();
		return new SurveyInputPage(webDriver);
	}
	
	public ParkDetailTempCheckPage clickParkLinkForParkDetail() {
		WebElement parkDetailLink = webDriver.findElement(By.linkText("Cuyahoga Valley National Park"));
		parkDetailLink.click();
		return new ParkDetailTempCheckPage(webDriver);
	}
	
	public HomePage searchBar(String parkName) {
		WebElement search = webDriver.findElement(By.name("search"));
		search.sendKeys(parkName);
		return this;
	}
	
	public SearchResultPage clickSearch() {
		WebElement submitSearch = webDriver.findElement(By.id("searchButton"));
		submitSearch.click();
		return new SearchResultPage(webDriver);
	}
	
}
