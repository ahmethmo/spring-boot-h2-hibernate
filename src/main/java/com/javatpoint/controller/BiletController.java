package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Bilet;
import com.javatpoint.service.BiletService;

@RestController
@RequestMapping("bilet")
public class BiletController {
	@Autowired
	BiletService service;
	
	@GetMapping("/all")
	private List<Bilet> getAllStudent() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	private Bilet getById(@PathVariable("id") int id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	private int save(@RequestBody Bilet t) {
		return service.saveOrUpdate(t).getId();
	}
	
	@PostMapping("/save/all")
	private List<Integer> saveAll(@RequestBody List<Bilet> t) {
		return service.saveOrUpdateAll(t);
	}
	
	@PostMapping("/filter")
	private List<Bilet> filter(@RequestBody Bilet t) {
		return service.filter(t);
	}
	
	@GetMapping("/iptalet/{id}")
	private void iptalEt(@PathVariable("id") int id) {
		service.iptalEt(id);
	}
}
