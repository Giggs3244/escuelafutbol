package com.giggs.escuelafutbol.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the tipo_identificacion database table.
 * 
 */
@Entity
@Table(name = "tipo_identificacion")
@NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t")
public class TipoIdentificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 255)
	private String abreviatura;

	@Column(nullable = false, length = 255)
	private String nombre;

	// bi-directional many-to-one association to Acudiente
	@OneToMany(mappedBy = "tipoIdentificacion")
	private List<Acudiente> acudientes = new ArrayList<>(0);;

	// bi-directional many-to-one association to Deportista
	@OneToMany(mappedBy = "tipoIdentificacion")
	private List<Deportista> deportistas = new ArrayList<>(0);;

	public TipoIdentificacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Acudiente> getAcudientes() {
		return this.acudientes;
	}

	public void setAcudientes(List<Acudiente> acudientes) {
		this.acudientes = acudientes;
	}

	public Acudiente addAcudiente(Acudiente acudiente) {
		getAcudientes().add(acudiente);
		acudiente.setTipoIdentificacion(this);

		return acudiente;
	}

	public Acudiente removeAcudiente(Acudiente acudiente) {
		getAcudientes().remove(acudiente);
		acudiente.setTipoIdentificacion(null);

		return acudiente;
	}

	public List<Deportista> getDeportistas() {
		return this.deportistas;
	}

	public void setDeportistas(List<Deportista> deportistas) {
		this.deportistas = deportistas;
	}

	public Deportista addDeportista(Deportista deportista) {
		getDeportistas().add(deportista);
		deportista.setTipoIdentificacion(this);

		return deportista;
	}

	public Deportista removeDeportista(Deportista deportista) {
		getDeportistas().remove(deportista);
		deportista.setTipoIdentificacion(null);

		return deportista;
	}

}