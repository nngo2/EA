package edu.mum.cs544.imdb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import edu.mum.cs544.imdb.service.TVSeriesService;

@Controller
public class TVSeriesController {
	@Resource
	private TVSeriesService tvSeriesService;
	
	
}
