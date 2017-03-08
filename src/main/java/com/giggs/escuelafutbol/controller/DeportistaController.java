package com.giggs.escuelafutbol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.giggs.escuelafutbol.entity.Deportista;
import com.giggs.escuelafutbol.entity.TipoIdentificacion;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.giggs.escuelafutbol.model.Pager;
import com.giggs.escuelafutbol.repository.DeportistaRepositoryPageable;
import com.giggs.escuelafutbol.repository.TipoIdentificacionRepository;
import com.giggs.escuelafutbol.service.DeportistaService;

@Controller
@RequestMapping("/deportistas")
public class DeportistaController {

	@Autowired
	@Qualifier("deportistaService")
	private DeportistaService deportistaService;

	@Autowired
	@Qualifier("deportistaRepositoryPageable")
	private DeportistaRepositoryPageable deportistaRepositoryPageable;

	@Autowired
	@Qualifier("tipoIdentificacionRepository")
	private TipoIdentificacionRepository tipoIdentificacionRepository;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	// @GetMapping("/")
	// public ModelAndView consultarDeportistas() {
	// ModelAndView mav = new ModelAndView("deportista/inicio");
	// mav.addObject("title", "Inicio | Deportistas");
	// mav.addObject("deportistas", deportistaService.findAll());
	// return mav;
	// }

	@GetMapping("/{pageSize}/{page}")
	public ModelAndView consultarDeportistasPage(@PathVariable(name = "pageSize", required = false) Integer pageSize,
			@PathVariable(name = "page", required = false) Integer page) {
		ModelAndView mav = new ModelAndView("deportista/inicio");

		/*
		 * PAGE: numero de la pagina. PAGE SIZE: cantidad de registros por
		 * pagina. EJM: total de registros = 16 page size = 4 total page = 16 /
		 * 4 = 4 page = 1
		 */
		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DeportistaModel> deportistas = deportistaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		if (deportistas != null) {
			Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
			mav.addObject("deportistas", deportistas);
			mav.addObject("pager", pager);
		}

		mav.addObject("selectedPageSize", evalPageSize);
		// mav.addObject("deportistaFiltro", new DeportistaModel());
		mav.addObject("tiposIdentificacion", tiposIdentificacion);
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

	@PostMapping("/filtrar")
	public ModelAndView filtrarDeportista(@ModelAttribute("deportistaFiltro") DeportistaModel deportistaFiltro) {
		ModelAndView mav = new ModelAndView("deportista/inicio");
		if (deportistaFiltro != null) {
			mav.addObject("deportistaFiltro", deportistaFiltro);
		} else {
			mav.addObject("deportistaFiltro", new DeportistaModel());
		}
		return mav;
	}

	@GetMapping("/")
	public ModelAndView consultarDeportistasPageRequest(
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "page", required = false) Integer page) {

		ModelAndView mav = new ModelAndView("deportista/consultar");
		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DeportistaModel> deportistas = deportistaService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		if (deportistas != null) {
			Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
			mav.addObject("deportistas", deportistas);
			mav.addObject("pager", pager);
		}

		mav.addObject("selectedPageSize", evalPageSize);
		mav.addObject("tiposIdentificacion", tiposIdentificacion);
		mav.addObject("deportistaModel", new DeportistaModel());
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

	@PostMapping("/filtrarDeportistas")
	public ModelAndView filtrarDeportistas(@ModelAttribute("deportistaModel") DeportistaModel deportistaModel,
			@PageableDefault(page = 1, size = 5) Pageable pageable) {

		ModelAndView mav = new ModelAndView("deportista/consultar");

		// QDeportista qDeportista = QDeportista.deportista;
		//
		// Predicate predicate =
		// qDeportista.identificacion.like("1020").and(qDeportista.nombre.like("Bry"));
		//
		// Page<Deportista> deportistas = deportistaService.search(predicate,
		// pageable);

		Page<Deportista> deportistas = deportistaService.findAllByIdentificacion(deportistaModel.getIdentificacion(),
				pageable);

		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();

		if (deportistas != null) {
			Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
			mav.addObject("deportistas", deportistas);
			mav.addObject("pager", pager);
		}

		mav.addObject("selectedPageSize", 5);
		mav.addObject("tiposIdentificacion", tiposIdentificacion);
		mav.addObject("deportistaModel", new DeportistaModel());
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

	@GetMapping("/deportistasFiltros")
	public ModelAndView filtrarDeportistas(@RequestParam("identificacion") String identificacion,
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "page", required = false) Integer page) {
		ModelAndView mav = new ModelAndView("deportista/consultar");

		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();
		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<Deportista> deportistas = deportistaRepositoryPageable.findByIdentificacionLike("%" + identificacion + "%",
				new PageRequest(evalPage, evalPageSize));

		if (deportistas != null) {
			Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
			mav.addObject("deportistas", deportistas);
			mav.addObject("pager", pager);
		}

		mav.addObject("selectedPageSize", evalPageSize);
		mav.addObject("deportistaModel", new DeportistaModel());
		mav.addObject("tiposIdentificacion", tiposIdentificacion);
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}

	@GetMapping("/saludo")
	public String saludar() {
		return "saludo";
	}

	@ModelAttribute("deportistaFiltro")
	public DeportistaModel loadEmptyModelBean() {
		return new DeportistaModel();
	}

}
