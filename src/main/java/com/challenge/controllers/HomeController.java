package com.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge.repositories.CategoryRepository;

@Controller
public class HomeController {
	
	@Autowired
	CategoryRepository categories;

	@RequestMapping("/")
	String home(Model model) {
		model.addAttribute("view", "home");
		model.addAttribute("categories", categories.findAll());
		return "index";
	}
}
