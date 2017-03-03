package com.giggs.escuelafutbol.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@Table(name = "usuario_rol")
@NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
public class UsuarioRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false, length = 255)
	private String rol;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_usuario", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	public UsuarioRol() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}