package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.FavoritePark;
import com.techelevator.dao.FavoriteParkDAO;
import com.techelevator.npgeek.Survey;
import com.techelevator.dao.SurveyDAO;

@Controller
public class surveyController {
	
	@Autowired
	private SurveyDAO surveydao; 
	@Autowired 
	private FavoriteParkDAO favoriteparkdao; 
	
	
	@RequestMapping("/survey")
	public String displaySurveyPage(Model model) {
		
		if(! model.containsAttribute("survey")) {
			model.addAttribute("survey", new Survey()); 
		}
		return "survey"; 
	}

	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String addNewSurvey(
			@Valid @ModelAttribute Survey newSurvey, 
			BindingResult result, // interface that represents binding results and extends the interface for registration error capabilities
			RedirectAttributes attr 
	) {
		
		if(result.hasErrors()) { // if the form isn't complete, return the same survey page
			return "survey"; 
		}
		
		surveydao.saveSurvey(newSurvey); // otherwise save the survey results and redirect to the survey results page 
		attr.addFlashAttribute("survey", newSurvey); 
		return "redirect:/surveyResults";
		
	}
		
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String displayThankYou(Model modelHolder, ModelMap map) {
		
		if(!modelHolder.containsAttribute("survey")) {
			map.addAttribute("surveyResult", new Survey()); 
		} else {
			map.addAttribute("surveyResult", modelHolder.asMap().get("survey")); 
		}
		
		List<FavoritePark> favoriteParks = favoriteparkdao.getFavoriteParks();  // displays the parks that have been voted on with surveys
		map.addAttribute("favoriteParks", favoriteParks); 
		return "surveyResults"; 
	}
}		
		
	
	
