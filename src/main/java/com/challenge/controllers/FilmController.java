package com.challenge.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.challenge.models.Film;
import com.challenge.utils.MetaSQLParser;

@Controller
@RequestMapping("film")
public class FilmController {
	
	@PersistenceContext
	private EntityManager emanager;
	
	@RequestMapping("")
	String film(@RequestParam(value="q") String query, Model model) {
		MetaSQLParser parser = new MetaSQLParser();
		
		//http://localhost:8080/film?q=(and(and(price>0.99)(price<4))(or(category="Action")(category="Horror")))
		
		String whereClause = parser.toSQL(query); 
		
		whereClause = whereClause.replaceAll("price", "rental_rate"); // taking rental_rate for price
		whereClause = whereClause.replaceAll("category", "category.name"); // taking categiry.name for category
		
		Query q = emanager.createNativeQuery("SELECT * "
				+ "FROM film "
				+ "JOIN film_category ON film.film_id=film_category.film_id "
				+ "JOIN category ON category.category_id=film_category.category_id "
				+ "WHERE " + whereClause, Film.class);
		
		@SuppressWarnings("unchecked")
		List<Film> films = q.getResultList();
		
		model.addAttribute("view", "film");
		model.addAttribute("films", films);
		return "index";
	}
}
