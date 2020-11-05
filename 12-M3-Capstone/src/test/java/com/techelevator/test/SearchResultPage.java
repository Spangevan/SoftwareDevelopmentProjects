package com.techelevator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
	
	private WebDriver webDriver;
	
	public SearchResultPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String searchResult(String linkText) {
		return webDriver.findElement(By.linkText(linkText)).getText();
	}

}
