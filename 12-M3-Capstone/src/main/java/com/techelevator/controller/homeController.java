package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.dao.ForecastDAO;
import com.techelevator.dao.ParkDAO;
import com.techelevator.npgeek.Forecast;
import com.techelevator.npgeek.Park;

@Controller
@SessionAttributes({"tempChoice"}) // session variable name to allow temp choice to remain throughout site visit
public class homeController {
	
	@Autowired
	private ParkDAO parkDao;
	@Autowired
	private ForecastDAO forecastDao;
	
	@RequestMapping ("/") // home page 
	public String displayHomePage(ModelMap map) {
		
		List<Park> allParks = parkDao.getAllParks();
		
		map.addAttribute("parks", allParks);
		
		return "homePage";
	}
	
	
	@RequestMapping(path="/parkDetailPage", method=RequestMethod.GET) // get request for individual park detail page
	public String displayDetailsPage(@RequestParam String parkCode, ModelMap map) {
		// takes in get request param park code to display proper info based on park chosen
		map.put("park", parkDao.getParkByParkCode(parkCode));
		List<Forecast> forecast = forecastDao.getForecastByParkCode(parkCode);
		
		map.addAttribute("forecast", forecast);
		
		return "parkDetailPage";
	}
	
	@RequestMapping(path = "/parkDetailPage", method = RequestMethod.POST) // post method used for session attribute temp choice
	public String viewParkDetailPageUserTempChoice(@RequestParam("selectedTempUnit") String selectedTempUnit,
			@RequestParam("parkCode") String parkCode, ModelMap modelMap) {

		modelMap.addAttribute("tempChoice", selectedTempUnit);
		modelMap.addAttribute("parkCode", parkCode);
		return "redirect:/parkDetailPage"; // redirets to same page after the method is used to change temp choice
	}
	
	@RequestMapping(path = "/searchResults", method = RequestMethod.GET) // get request for search bar function. never figured out how to make case insensitive.
	public String showSearchResult(ModelMap map, @RequestParam String search) {
		
		

		List<Park> showResult = parkDao.showResultsBySearch(search);
		if (showResult.isEmpty()) {
			throw new RuntimeException();
		}
		map.addAttribute("park", showResult);
		return "searchResults";
	}


}