package com.challenge.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.challenge.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Serializable>{

}
