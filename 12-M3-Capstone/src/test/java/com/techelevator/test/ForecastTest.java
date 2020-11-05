package com.techelevator.test;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.*;

import com.techelevator.npgeek.Forecast;


public class ForecastTest {

	private Forecast forecast;
	
	@Before
	public void setup() {
		forecast = new Forecast();
	}
	
	@Test
	public void convert_5_degree_f_to_c() {
		forecast.setHigh(5);
		forecast.getHighCelsius();
		Assert.assertEquals(-15.00, forecast.getHighCelsius(), 0.00);
	}
	
	@Test
	public void convert_11_degree_f_to_c() {
		forecast.setHigh(11);
		forecast.getHighCelsius();
		Assert.assertEquals(-11.67, forecast.getHighCelsius(), 0.00);
	}
	
	@Test
	public void convert_0_degree_f_to_c() {
		forecast.setHigh(0);
		forecast.getHighCelsius();
		Assert.assertEquals(-17.78, forecast.getHighCelsius(), 0.00);		
	}
	
	@Test
	public void convert_negative_10_degree_f_to_c() {
		forecast.setHigh(-10);
		forecast.getHighCelsius();
		Assert.assertEquals(-23.34, forecast.getHighCelsius(), 0.00);		
	}
	
	@Test
	public void get_snow_advisory_message() {
		forecast.setHigh(50);
		forecast.setLow(50);
		forecast.setForecast("snow");
		Assert.assertEquals("It's Snowing! Pack snowshoes.  ", forecast.getAdvisoryMessage());
	}
	
	@Test
	public void get_snow_and_low_temp_advisory_message() {
		forecast.setHigh(2);
		forecast.setLow(0);
		forecast.setForecast("snow");
		Assert.assertEquals("It's Snowing! Pack snowshoes.  BRRRRRRRR!!! Today's a tad nippy so make sure to pack snow shoes and and dress accordingly!", forecast.getAdvisoryMessage());
	}
	
	@Test
	public void get_rain_and_high_temp_advisory_message() {
		forecast.setHigh(80);
		forecast.setLow(59);
		forecast.setForecast("rain");
		Assert.assertEquals("Looks like it's rain today folks, you should pack rain gear and waterproof shoes! Looks like it's ULTRA SUNRISE TODAY!!! You're probably going to need an extra gallon of water as well as sunblock. ", forecast.getAdvisoryMessage());		
	}
	
	@Test
	public void get_no_forecast_advisory_message() {
		forecast.setHigh(50);
		forecast.setLow(50);
		forecast.setForecast("");
		Assert.assertEquals("No Weather Advisory Today.", forecast.getAdvisoryMessage());		
	}
	
	@Test
	public void get_today_as_day() {
		forecast.setFiveDayForecastValue(1);
		Assert.assertEquals("Today", forecast.getForecastOfTheWeek());
	}
	
	@Test
	public void get_tomorrow_as_day() {
		forecast.setFiveDayForecastValue(2);
		Assert.assertEquals("Tomorrow", forecast.getForecastOfTheWeek());
	}
	
	@Test
	public void get_third_day_as_day() {
		LocalDate date = LocalDate.now().plusDays(2);
		date.getDayOfWeek().name();
		Assert.assertEquals(date.getDayOfWeek().name(), forecast.getForecastOfTheWeek().toUpperCase());
	}
}
