package com.giggs.escuelafutbol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giggs.escuelafutbol.converter.DeportistaConverter;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.giggs.escuelafutbol.repository.DeportistaRepository;
import com.giggs.escuelafutbol.service.DeportistaService;

@Service("deportistaService")
public class DeportistaServiceImpl implements DeportistaService {

	@Autowired
	@Qualifier("deportistaRepository")
	private DeportistaRepository deportistaRepository;

	@Autowired
	@Qualifier("deportistaConverter")
	private DeportistaConverter deportistaConverter;

	@Override
	public Page<DeportistaModel> findAllPageable(Pageable pageable) {
		return deportistaConverter.converterEntities2Models(deportistaRepository.findAll(pageable), pageable);
	}

	@Override
	public Iterable<DeportistaModel> save(Iterable<DeportistaModel> persons) {
		// stand-by
		return null;
	}

}
