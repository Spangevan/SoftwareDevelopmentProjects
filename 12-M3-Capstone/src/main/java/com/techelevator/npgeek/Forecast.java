package com.techelevator.npgeek;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Forecast {

	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	private String forecastOfTheWeek;
	private String showDegree;
	
	public String getParkCode() {
		return parkCode;
	}
	
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		this.low = low;
	}
	
	public int getHigh() {
		return high;
	}
		
	public void setHigh(int high) {
		this.high = high;
	}
	
	public String getForecast() {
		return forecast;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public String getForecastOfTheWeek() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, fiveDayForecastValue - 1);
		Date tomorrow = calendar.getTime();
		String day;
		if (fiveDayForecastValue == 1) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
			day = simpleDateFormat.format(tomorrow);
			day = "Today";
		} else if (fiveDayForecastValue == 2) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
			day = simpleDateFormat.format(tomorrow);
			day = "Tomorrow";
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
			day = simpleDateFormat.format(tomorrow);
		}
		
		return day;
	}
	
	public void setForecastOfTheWeek(String forecastOfTheWeek) {
		this.forecastOfTheWeek = forecastOfTheWeek;
	}
	
	public String getShowDegree() {
		return showDegree = "\u00B0"; // unicode character for degree symbol 
	}
	
	public void setShowDegree(String showDegree) {
		this.showDegree = showDegree;
	}

	public double getHighCelsius() {
		return convertFToC(high);
	}
	
	public double getLowCelsius() {
		return convertFToC(low);
	}
	
	public String getAdvisoryMessage() {
		String returnString = "";
		if(forecast.equals("snow")) {
			returnString = returnString + "It's Snowing! Pack snowshoes.  ";
		} else if (forecast.equals("rain")) {
			returnString = returnString + "Looks like it's rain today folks, you should pack rain gear and waterproof shoes! ";
		} else if (forecast.equals("thunderstorms")) {
			returnString = returnString + "There's thunderstorms about, we advise seeking shelter and avoid hiking on exposed ridges.";
		} else if (forecast.equals("sunny")) {
			returnString = returnString + "Nice and sunny today. We reccomend packing sunblock just in case. ";
		}
		if (high > 75) {
			returnString = returnString + "Looks like it's ULTRA SUNRISE TODAY!!! You're probably going to need an extra gallon of water as well as sunblock. ";
		}
		if (low < 20) { 
			returnString = returnString + "BRRRRRRRR!!! Today's a tad nippy so make sure to pack snow shoes and and dress accordingly!";
		}
		if (Math.abs(high - low) > 20) {
			returnString = returnString + "Today is going to vary a bit so we reccomend wearing breathable layers. AS WELL AS ARMOR AND A CAN DO ATTITUDE IF YOU YOU'RE HARDY ENOUGH FOR ABERRATION! ";
		}
		if(returnString.equals("")) {
			returnString = "No Weather Advisory Today.";
		}

		return returnString;
	}
	
	private double convertFToC(int temp) { // method for converting temp from F to C
		return Math.floor(((temp - 32) / 1.8) * 100) / 100;
	}
	
}