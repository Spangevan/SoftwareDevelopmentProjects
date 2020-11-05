package com.techelevator.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ParkDetailTempCheckPage {
	
	private WebDriver webDriver;
	
	public ParkDetailTempCheckPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkDetailTempCheckPage chooseTemp(String name, String tempValue) {
		Select selectField = new Select(webDriver.findElement(By.name(name)));
		selectField.selectByVisibleText(tempValue);
		return this;
	}
	
	public ParkDetailTempCheckPage submitForm() {
		WebElement submitForm = webDriver.findElement(By.id("submitParkDetail"));
		submitForm.click();
		return this;
	}
	
	public String findTempertureInF(String className) {
		return webDriver.findElement(By.className(className)).getText();
	}
	
	public String findTempertureInC(String className) {
		return webDriver.findElement(By.className(className)).getText();		
	}
	
}
