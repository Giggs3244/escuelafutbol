package com.giggs.escuelafutbol.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 16)
	private String usuario;

	@Column(nullable = false, length = 255)
	private String contrasena;

	@Column(nullable = false)
	private boolean estado;

	// bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<UsuarioRol> usuarioRoles = new ArrayList<>(0);;

	public Usuario() {
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRoles;
	}

	public void setUsuarioRols(List<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setUsuario(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setUsuario(null);

		return usuarioRol;
	}

}