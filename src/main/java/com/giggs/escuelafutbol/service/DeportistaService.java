package com.giggs.escuelafutbol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.giggs.escuelafutbol.model.DeportistaModel;

public interface DeportistaService {

	/**
	 * Finds a "page" of persons
	 * 
	 * @param pageable
	 * @return {@link Page} instance
	 */
	Page<DeportistaModel> findAllPageable(Pageable pageable);

	/**
	 * Saves collection of persons
	 * 
	 * @param persons
	 * 
	 * @return collection of persons
	 */
	Iterable<DeportistaModel> save(Iterable<DeportistaModel> persons);

}
