package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Rota;
import com.javatpoint.service.RotaService;

@RestController
@RequestMapping("rota")
public class RotaController{
	@Autowired
	RotaService service;

	@GetMapping("/all")
	private List<Rota> getAllStudent() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	private Rota getById(@PathVariable("id") int id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	private int save(@RequestBody Rota t) {
		return service.saveOrUpdate(t).getId();
	}
	
	@PostMapping("/save/all")
	private List<Integer> saveAll(@RequestBody List<Rota> t) {
		return service.saveOrUpdateAll(t);
	}
	
}
