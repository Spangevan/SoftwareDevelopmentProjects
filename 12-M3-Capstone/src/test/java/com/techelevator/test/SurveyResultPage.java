package com.techelevator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SurveyResultPage {
	
	private WebDriver webDriver;
	
	public SurveyResultPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String findPark() {
		return webDriver.findElement(By.partialLinkText("Rocky Mountain National Park")).getText();
	}

}
