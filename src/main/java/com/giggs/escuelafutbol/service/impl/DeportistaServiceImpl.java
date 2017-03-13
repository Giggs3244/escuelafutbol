package com.giggs.escuelafutbol.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giggs.escuelafutbol.converter.DeportistaConverter;
import com.giggs.escuelafutbol.entity.QDeportista;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.giggs.escuelafutbol.repository.DeportistaRepository;
import com.giggs.escuelafutbol.service.DeportistaService;
import com.querydsl.core.types.Predicate;

@Service("deportistaService")
public class DeportistaServiceImpl implements DeportistaService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	@Qualifier("deportistaRepository")
	private DeportistaRepository deportistaRepository;

	@Autowired
	@Qualifier("deportistaConverter")
	private DeportistaConverter deportistaConverter;

	@Override
	public Page<DeportistaModel> findAll(DeportistaModel filtros, Pageable pageable) {

		QDeportista qDeportista = QDeportista.deportista;

		Predicate predicate = qDeportista.identificacion.like("%" + filtros.getIdentificacion() + "%")
				.and(qDeportista.nombre.like("%" + filtros.getNombre() + "%"));

		return deportistaConverter.converterEntities2Models(deportistaRepository.findAll(predicate, pageable),
				pageable);
	}

	@Override
	public Iterable<DeportistaModel> save(Iterable<DeportistaModel> persons) {
		return null;
	}

}
