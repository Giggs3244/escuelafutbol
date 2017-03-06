package com.giggs.escuelafutbol.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.giggs.escuelafutbol.entity.Deportista;

@Repository("deportistaRepository")
public interface DeportistaRepository extends PagingAndSortingRepository<Deportista, Integer> {

	
	
}
