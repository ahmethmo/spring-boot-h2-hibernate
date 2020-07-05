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
import com.javatpoint.model.Ucus;
import com.javatpoint.service.UcusService;

@RestController
@RequestMapping("ucus")
public class UcusController {
	@Autowired
	UcusService service;

//	@GetMapping("/all")
//	private List<Ucus> getAllStudent() {
//		return service.getAll();
//	}

	@GetMapping("/{id}")
	private Ucus getById(@PathVariable("id") int id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	private int save(@RequestBody Ucus t) {
		return service.saveOrUpdate(t).getId();
	}
	
	@PostMapping("/save/all")
	private List<Integer> saveAll(@RequestBody List<Ucus> t) {
		return service.saveOrUpdateAll(t);
	}
	
	@PostMapping("/filter")
	private List<Ucus> filter(@RequestBody Ucus t) {
		return service.filter(t);
	}
	
}
