package com.giggs.escuelafutbol.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giggs.escuelafutbol.entity.TipoIdentificacion;

@Repository("tipoIdentificacionRepository")
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Serializable> {

}
