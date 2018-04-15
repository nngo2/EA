package edu.mum.cs544.imdb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs544.imdb.service.TVSeriesService;

@Controller
public class TVSeriesController {
	@Resource
	private TVSeriesService tvSeriesService;
	
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
}
