package com.giggs.escuelafutbol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giggs.escuelafutbol.entity.Deportista;
import com.querydsl.core.types.Predicate;

@Repository("deportistaRepositoryPageable")
public interface DeportistaRepositoryPageable
		extends PagingAndSortingRepository<Deportista, Integer>, QueryDslPredicateExecutor<Deportista> {

	Page<Deportista> findByIdentificacionLike(String identificacion, Pageable pageable);
	Page<Deportista> findAll(Predicate predicate, Pageable pageable);

}
