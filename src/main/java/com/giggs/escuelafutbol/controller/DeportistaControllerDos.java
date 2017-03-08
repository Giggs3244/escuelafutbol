package com.giggs.escuelafutbol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.giggs.escuelafutbol.entity.Deportista;
import com.giggs.escuelafutbol.entity.QDeportista;
import com.giggs.escuelafutbol.entity.TipoIdentificacion;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.giggs.escuelafutbol.model.Pager;
import com.giggs.escuelafutbol.repository.DeportistaRepositoryPageable;
import com.giggs.escuelafutbol.repository.TipoIdentificacionRepository;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/players")
public class DeportistaControllerDos {

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@Autowired
	@Qualifier("deportistaRepositoryPageable")
	private DeportistaRepositoryPageable deportistaRepositoryPageable;

	@Autowired
	@Qualifier("tipoIdentificacionRepository")
	private TipoIdentificacionRepository tipoIdentificacionRepository;

	@GetMapping("/inicio")
	public ModelAndView inicio(@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "page", required = false) Integer page) {

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		ModelAndView mav = new ModelAndView("deportista/inicio");

		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();
		mav.addObject("tiposIdentificacion", tiposIdentificacion);

		Page<Deportista> deportistas = deportistaRepositoryPageable.findAll(new PageRequest(evalPage, evalPageSize));
		mav.addObject("deportistas", deportistas);

		Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
		mav.addObject("pager", pager);

		mav.addObject("deportistaFiltro", new DeportistaModel());
		mav.addObject("selectedPageSize", evalPageSize);
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

	@PostMapping("/filtrar")
	public ModelAndView filtrar(@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "page", required = false) Integer page,
			@ModelAttribute("deportistaFiltro") DeportistaModel deportistaFiltro) {

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		ModelAndView mav = new ModelAndView("deportista/inicio");

		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();
		mav.addObject("tiposIdentificacion", tiposIdentificacion);

		QDeportista qDeportista = QDeportista.deportista;
		Predicate predicate = qDeportista.identificacion.like("%" + deportistaFiltro.getIdentificacion() + "%")
				.and(qDeportista.nombre.like("%" + deportistaFiltro.getNombre() + "%"));

		Page<Deportista> deportistas = deportistaRepositoryPageable.findAll(predicate,
				new PageRequest(evalPage, evalPageSize));
		mav.addObject("deportistas", deportistas);

		Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
		mav.addObject("pager", pager);

		mav.addObject("deportistaFiltro", deportistaFiltro);
		mav.addObject("selectedPageSize", evalPageSize);
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

}