package com.giggs.escuelafutbol.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.giggs.escuelafutbol.entity.TipoIdentificacion;

public class DeportistaModel {

	private int id;

	private String foto;

	private String nombre;

	private String identificacion;

	private TipoIdentificacion tipoIdentificacion;

	private byte estado;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	public DeportistaModel() {
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEstado() {
		return estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "DeportistaModel [id=" + id + ", foto=" + foto + ", nombre=" + nombre + ", identificacion="
				+ identificacion + ", tipoIdentificacion=" + tipoIdentificacion + ", estado=" + estado
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}
