package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Firma;
import com.javatpoint.service.FirmaService;

@RestController
@RequestMapping("firma")
public class FirmaController {
	@Autowired
	FirmaService service;
	
	@GetMapping("/all")
	private List<Firma> getAllStudent() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	private Firma getById(@PathVariable("id") int id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	private int save(@RequestBody Firma t) {
		return service.saveOrUpdate(t).getId();
	}
	
	@PostMapping("/save/all")
	private List<Integer> saveAll(@RequestBody List<Firma> t) {
		return service.saveOrUpdateAll(t);
	}
}
