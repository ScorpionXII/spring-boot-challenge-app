package com.challenge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer film_id;
	
	private String title;
	
	@Column(name="rental_rate")
	private Double price;
	
	@OneToOne
	@JoinTable(name="film_category",
	joinColumns=@JoinColumn(name="film_id"),
	inverseJoinColumns=@JoinColumn(name="category_id"))
	private Category category;
	
	public Integer getId() {
		return film_id;
	}
	
	public void setId(Integer id) {
		this.film_id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Category getCategory() {
		return category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
