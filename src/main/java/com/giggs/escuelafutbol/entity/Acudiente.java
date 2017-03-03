package com.giggs.escuelafutbol.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the acudiente database table.
 * 
 */
@Entity
@Table(name = "acudiente")
@NamedQuery(name = "Acudiente.findAll", query = "SELECT a FROM Acudiente a")
public class Acudiente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 255)
	private String email;

	@Column(nullable = false)
	private byte estado;

	@Column(nullable = false, length = 255)
	private String nombre;

	@Column(nullable = false, length = 12)
	private String identificacion;

	// bi-directional many-to-many association to Documento
	@ManyToMany
	@JoinTable(name = "acudiente_documento", joinColumns = {
			@JoinColumn(name = "acudiente_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "documento_id", nullable = false) })
	private List<Documento> documentos = new ArrayList<>(0);;

	// bi-directional many-to-one association to TipoIdentificacion
	@ManyToOne
	@JoinColumn(name = "tipo_identificacion_id", nullable = false, insertable = false, updatable = false)
	private TipoIdentificacion tipoIdentificacion;

	public Acudiente() {
	}

	public int getId() {
		return id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

}