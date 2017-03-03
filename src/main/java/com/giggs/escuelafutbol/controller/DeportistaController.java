package com.giggs.escuelafutbol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.giggs.escuelafutbol.service.DeportistaService;

@Controller
@RequestMapping("/deportistas")
public class DeportistaController {

	@Autowired
	@Qualifier("deportistaService")
	private DeportistaService deportistaService;

	@GetMapping("/")
	public ModelAndView consultarDeportistas() {
		ModelAndView mav = new ModelAndView("deportista/inicio");
		mav.addObject("title", "Inicio | Deportistas");
		mav.addObject("deportistas", deportistaService.findAll());
		return mav;
	}

	@GetMapping("/saludo")
	public String saludar() {
		return "saludo";
	}

}
