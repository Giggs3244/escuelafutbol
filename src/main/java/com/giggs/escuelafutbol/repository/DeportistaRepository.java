package com.giggs.escuelafutbol.repository;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giggs.escuelafutbol.entity.Deportista;

@Repository("deportistaRepository")
public interface DeportistaRepository extends PagingAndSortingRepository<Deportista, Integer>, QueryDslPredicateExecutor<Deportista> {

	public Page<Deportista> findByIdentificacionAndNombre(String identificacion, String nombre, Pageable pageable);

	public Page<Deportista> findAll(Predicate predicate, Pageable pageable);
	
	public Page<Deportista> findAllByIdentificacion(String identificacion, Pageable pageable);


}
