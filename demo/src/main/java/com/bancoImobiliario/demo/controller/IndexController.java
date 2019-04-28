package com.bancoImobiliario.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bancoImobiliario.demo.model.Estatistica;
import com.bancoImobiliario.demo.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	IndexService service;

	@GetMapping(value = "/")
	private String bankrupt(Model model) throws Exception {

		Estatistica estatistica = service.rodarSimulacao(300);

		model.addAttribute("estatistica", estatistica);

		return "index";
	}

}
