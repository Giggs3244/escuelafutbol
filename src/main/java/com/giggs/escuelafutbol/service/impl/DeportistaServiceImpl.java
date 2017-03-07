package com.giggs.escuelafutbol.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.giggs.escuelafutbol.converter.DeportistaConverter;
import com.giggs.escuelafutbol.entity.Deportista;
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
	public Page<DeportistaModel> findAllPageable(Pageable pageable) {
		return deportistaConverter.converterEntities2Models(deportistaRepository.findAll(pageable), pageable);
	}

	@Override
	public Iterable<DeportistaModel> save(Iterable<DeportistaModel> persons) {
		// stand-by
		return null;
	}

	@Override
	public Page<Deportista> search(Predicate predicate, Pageable pageable) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
		// cb.like(, predicate.)
		return deportistaRepository.findAll(predicate, pageable);
	}

	@Override
	public Page<Deportista> findAllByIdentificacionAndNombre(String identificacion, String nombre, Pageable pageable) {
		return deportistaRepository.findByIdentificacionAndNombre(identificacion, nombre, pageable);
	}

	@Override
	public Page<Deportista> findAllByIdentificacion(String identificacion, Pageable pageable) {
		return deportistaRepository.findAllByIdentificacion(identificacion, pageable);
	}

	// @Override
	// public Page<DeportistaModel> findAllPageable(DeportistaModel
	// deportistaModel, Pageable pageable) {
	// String identificacion = "", nombre = "";
	// Date fechaNacimiento = null;
	// TipoIdentificacion tipoIdentificacion = null;
	// if (deportistaModel != null) {
	// identificacion = deportistaModel.getIdentificacion() != null ?
	// deportistaModel.getIdentificacion() : "";
	// nombre = deportistaModel.getNombre() != null ?
	// deportistaModel.getNombre() : "";
	// fechaNacimiento = deportistaModel.getFechaNacimiento() != null ?
	// deportistaModel.getFechaNacimiento()
	// : null;
	// tipoIdentificacion = deportistaModel.getTipoIdentificacion();
	// }
	// deportistaRepository.findByIdentificacionAndNombreAndFechaNacimientoAndTipoIdentificacion(identificacion,
	// nombre, fechaNacimiento, tipoIdentificacion, pageable);
	// return null;
	// }

}
