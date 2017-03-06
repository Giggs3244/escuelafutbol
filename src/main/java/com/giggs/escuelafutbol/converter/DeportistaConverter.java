package com.giggs.escuelafutbol.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.giggs.escuelafutbol.entity.Deportista;
import com.giggs.escuelafutbol.model.DeportistaModel;

@Component("deportistaConverter")
public class DeportistaConverter {

	public DeportistaModel converterEntity2Model(Deportista entidad) {
		if (entidad != null) {
			DeportistaModel modelo = new DeportistaModel();
			modelo.setId(entidad.getId());
			modelo.setNombre(entidad.getNombre());
			modelo.setIdentificacion(entidad.getIdentificacion());
			modelo.setFechaNacimiento(entidad.getFechaNacimiento());
			modelo.setEstado(entidad.getEstado());
			modelo.setFoto(entidad.getFoto());
			modelo.setTipoIdentificacion(entidad.getTipoIdentificacion());
			return modelo;
		}
		return null;
	}

	public Deportista converterModel2Entity(DeportistaModel modelo) {
		if (modelo != null) {
			Deportista entidad = new Deportista();
			entidad.setId(modelo.getId());
			entidad.setNombre(modelo.getNombre());
			entidad.setIdentificacion(modelo.getIdentificacion());
			entidad.setFechaNacimiento(modelo.getFechaNacimiento());
			entidad.setEstado(modelo.getEstado());
			entidad.setFoto(modelo.getFoto());
			entidad.setTipoIdentificacion(modelo.getTipoIdentificacion());
			return entidad;
		}
		return null;
	}

	public List<DeportistaModel> converterEntities2Models(List<Deportista> entidades) {
		if (entidades != null && !entidades.isEmpty())
			return entidades.stream().map(entidad -> this.converterEntity2Model(entidad)).collect(Collectors.toList());
		return null;
	}

	public Page<DeportistaModel> converterEntities2Models(Page<Deportista> entidades, Pageable pageable) {
		if (entidades != null && entidades.hasContent()) {
			List<DeportistaModel> modelos = entidades.getContent().stream()
					.map(entidad -> this.converterEntity2Model(entidad)).collect(Collectors.toList());
//			modelos.get(0).setTotalPages(entidades.getTotalPages());
//			modelos.get(0).setNumber(entidades.getNumber());
			Page<DeportistaModel> pages = new PageImpl<>(modelos, pageable, entidades.getTotalElements());
//			pages.s
			return pages;
		}
		return null;
	}

	public List<Deportista> converterModels2Entities(List<DeportistaModel> modelos) {
		if (modelos != null && !modelos.isEmpty())
			return modelos.stream().map(modelo -> this.converterModel2Entity(modelo)).collect(Collectors.toList());
		return null;
	}

}
