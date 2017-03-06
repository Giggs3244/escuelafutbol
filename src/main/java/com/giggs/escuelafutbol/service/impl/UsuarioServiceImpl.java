package com.giggs.escuelafutbol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giggs.escuelafutbol.entity.Usuario;
import com.giggs.escuelafutbol.entity.UsuarioRol;
import com.giggs.escuelafutbol.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Username: " + username + " not found.");
		}
		List<GrantedAuthority> authorities = buildAuthorities(usuario.getUsuarioRols());
		return buildUser(usuario, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(List<UsuarioRol> usuarioRols) {
		return new ArrayList<>(
				usuarioRols.stream().map(ur -> new SimpleGrantedAuthority(ur.getRol())).collect(Collectors.toList()));
	}

	private UserDetails buildUser(Usuario usuario, List<GrantedAuthority> authorities) {
		return new User(usuario.getUsuario(), usuario.getContrasena(), usuario.getEstado(), true, true, true,
				authorities);
	}

}
