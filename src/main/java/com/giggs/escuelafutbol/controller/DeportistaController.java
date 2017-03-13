package com.giggs.escuelafutbol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.giggs.escuelafutbol.entity.TipoIdentificacion;
import com.giggs.escuelafutbol.model.DeportistaModel;
import com.giggs.escuelafutbol.model.Pager;
import com.giggs.escuelafutbol.repository.TipoIdentificacionRepository;
import com.giggs.escuelafutbol.service.DeportistaService;

@Controller
@RequestMapping("/deportistas")
public class DeportistaController {

	@Autowired
	@Qualifier("deportistaService")
	private DeportistaService deportistaService;

	@Autowired
	@Qualifier("tipoIdentificacionRepository")
	private TipoIdentificacionRepository tipoIdentificacionRepository;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@GetMapping("/")
	public ModelAndView consultarDeportistas(@ModelAttribute("filtros") DeportistaModel filtros,
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "page", required = false) Integer page) {

		System.out.println(filtros);
		System.out.println(pageSize);
		System.out.println(page);

		ModelAndView mav = new ModelAndView("deportista/inicio");
		List<TipoIdentificacion> tiposIdentificacion = tipoIdentificacionRepository.findAll();

		int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
		int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

		Page<DeportistaModel> deportistas = deportistaService.findAll(filtros, new PageRequest(evalPage, evalPageSize));
		
		if (deportistas != null) {
			Pager pager = new Pager(deportistas.getTotalPages(), deportistas.getNumber(), BUTTONS_TO_SHOW);
			mav.addObject("deportistas", deportistas);
			mav.addObject("pager", pager);
		}

		mav.addObject("selectedPageSize", evalPageSize);
		mav.addObject("tiposIdentificacion", tiposIdentificacion);
		
//		if()
			mav.addObject("filtros", new DeportistaModel());
		
		
		mav.addObject("pageSizes", PAGE_SIZES);

		return mav;
	}
	
//	public boolean hayFiltros(DeportistaModel filtros) {
//		for(int i = 0; i < filtros.getClass().getFields().length; i++) {
//			if(filtros.getClass().getMethod("", )) {
//				
//			}
//		}
//		
//	}

}
