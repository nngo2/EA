package edu.mum.cs544.imdb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs544.imdb.domain.Person;
import edu.mum.cs544.imdb.domain.SearchCriteria;
import edu.mum.cs544.imdb.domain.Season;
import edu.mum.cs544.imdb.service.TVSeriesService;

@Controller
public class TVSeriesController {
	@Resource
	private TVSeriesService tvSeriesService;
	
	@ModelAttribute
	public void addCommonAttributes(Model model) {
	    model.addAttribute("searchCriteria", new SearchCriteria());
	}	
	
	@RequestMapping(value = "/images/season/{id}")
	public void getSeasonImage(@PathVariable int id, HttpServletResponse response) throws IOException {
		Season season = tvSeriesService.findSeasonById(id);
		if (season != null) {
		    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		    response.getOutputStream().write(season.getPoster());
		}
	}
	
	@RequestMapping(value = "/images/person/{id}")
	public void getPersonImageAs(@PathVariable int id, HttpServletResponse response) throws IOException {
		Person person = tvSeriesService.findPersonById(id);
		if (person != null) {
		    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		    response.getOutputStream().write(person.getPicture());
		}
	}	
	
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String search(SearchCriteria searchCriteria, Model model) {
		model.addAttribute("tvseriesList", tvSeriesService.search(searchCriteria));
		model.addAttribute("mainPage", "tvseriesList.jsp");
		return "index";
	}	
	
	@RequestMapping(path = {"/", "/series"}, method = RequestMethod.GET)
	public String tvSeriesList(Model model) {
		model.addAttribute("tvseriesList", tvSeriesService.findAll());
		model.addAttribute("mainPage", "tvseriesList.jsp");
		return "index";
	}
	
	@RequestMapping(path = "/series/{id}", method = RequestMethod.GET)
	public String getTVSeries(@PathVariable("id") int id, Model model) {
		model.addAttribute("tvseries", tvSeriesService.findById(id));
		model.addAttribute("mainPage", "tvseries.jsp");
		return "index";
	}
	
	@RequestMapping(path = "/season/{id}", method = RequestMethod.GET)
	public String getEpisodeList(@PathVariable("id") int id, Model model) {
		model.addAttribute("season", tvSeriesService.findSeasonById(id));
		model.addAttribute("mainPage", "episodeList.jsp");
		return "index";
	}
}
