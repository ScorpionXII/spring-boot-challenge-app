package com.challenge.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.challenge.models.Film;

public interface FilmRepository extends CrudRepository<Film, Serializable> {
	List<Film> findByTitle(String title);
	List<Film> findByCategory_NameIn(Collection<String> names);
	
	@Query(value = "SELECT * FROM film WHERE title=AFRICAN EGG", nativeQuery = true)
	List<Film> findByQuery();
}
