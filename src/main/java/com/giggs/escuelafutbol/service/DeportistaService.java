package com.giggs.escuelafutbol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.giggs.escuelafutbol.entity.Deportista;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.querydsl.core.types.Predicate;

public interface DeportistaService {

	/**
	 * Finds a "page" of persons
	 * 
	 * @param pageable
	 * @return {@link Page} instance
	 */
	public Page<DeportistaModel> findAllPageable(Pageable pageable);

	public Page<Deportista> search(Predicate predicate, Pageable pageable);

	public Page<Deportista> findAllByIdentificacionAndNombre(String identificacion, String nombre, Pageable pageable);

	public Page<Deportista> findAllByIdentificacion(String identificacion, Pageable pageable);

	/**
	 * Saves collection of persons
	 * 
	 * @param persons
	 * 
	 * @return collection of persons
	 */
	public Iterable<DeportistaModel> save(Iterable<DeportistaModel> persons);

}
