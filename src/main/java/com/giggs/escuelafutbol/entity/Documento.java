package com.giggs.escuelafutbol.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@Table(name = "documento")
@NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private byte estado;

	@Column(nullable = false, length = 255)
	private String nombre;

	@Column(nullable = false, length = 255)
	private String ubicacion;

	// bi-directional many-to-many association to Acudiente
	@ManyToMany(mappedBy = "documentos")
	private List<Acudiente> acudientes = new ArrayList<>(0);

	// bi-directional many-to-many association to Deportista
	@ManyToMany(mappedBy = "documentos")
	private List<Deportista> deportistas = new ArrayList<>(0);

	public Documento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Acudiente> getAcudientes() {
		return this.acudientes;
	}

	public void setAcudientes(List<Acudiente> acudientes) {
		this.acudientes = acudientes;
	}

	public List<Deportista> getDeportistas() {
		return this.deportistas;
	}

	public void setDeportistas(List<Deportista> deportistas) {
		this.deportistas = deportistas;
	}

}