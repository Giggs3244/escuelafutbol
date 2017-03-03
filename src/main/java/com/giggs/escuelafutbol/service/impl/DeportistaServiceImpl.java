package com.giggs.escuelafutbol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public List<DeportistaModel> findAll() {
		return deportistaConverter.converterEntities2Models(deportistaRepository.findAll());
	}

}
