package com.techelevator;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.techelevator.test.HomePage;
import com.techelevator.test.ParkDetailTempCheckPage;
import com.techelevator.test.SearchResultPage;
import com.techelevator.test.SurveyResultPage;

public class ParkSeleniumTest {

	private static WebDriver webDriver;
	private HomePage homePage;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		String homeDirctory = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDirctory + "/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	@Before 
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void submit_survey_form() {
		SurveyResultPage resultPage = homePage.clickSurveyLink().chooseFavPark("Rocky Mountain National Park").enterAnEmail("test@gmail.com").chooseState("California").chooseActivityLevel("activityLevel3").submitForm();
		Assert.assertEquals ("Rocky Mountain Natinal Park", resultPage.findPark());
	}
	
	@Test
	public void check_temp_in_Fahrenheit_in_park_details() {
		ParkDetailTempCheckPage checkTemp = homePage.clickParkLinkForParkDetail().chooseTemp("selectedTempUnit", "Fahrenheit").submitForm();
		Assert.assertEquals("High: 62 °F", checkTemp.findTempertureInF("side1"));
		Assert.assertEquals("Low: 38 °F", checkTemp.findTempertureInF("side2"));	
	}
	
	@Test 
	public void check_temp_in_Celsius_in_park_details() {
		ParkDetailTempCheckPage checkTemp = homePage.clickParkLinkForParkDetail().chooseTemp("selectedTempUnit", "Celsius").submitForm();
		Assert.assertEquals("High: 62 °C", checkTemp.findTempertureInC("side1"));
		Assert.assertEquals("Low: 38 °C", checkTemp.findTempertureInC("side2"));		
	}
	
	@Test
	public void search_form_for_exist_word() {
		SearchResultPage search = homePage.searchBar("Cuyahoga").clickSearch();
		Assert.assertEquals("Cuyahoga Valley National Park", search.searchResult("Cuyahoga Valley National Park"));
	}
	
	@Test
	public void search_form_for_error_page() {
		SearchResultPage search = homePage.searchBar("ark is the best").clickSearch();
		Assert.assertEquals("BACK TO HOMEPAGE", search.searchResult("BACK TO HOMEPAGE"));
	}
	
}
