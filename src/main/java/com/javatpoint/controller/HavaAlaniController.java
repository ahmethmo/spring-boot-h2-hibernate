package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.HavaAlani;
import com.javatpoint.service.HavaAlaniService;

@RestController
@RequestMapping("havaalani")
public class HavaAlaniController {
	@Autowired
	HavaAlaniService service;

	@GetMapping("/all")
	private List<HavaAlani> getAllStudent() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	private HavaAlani getById(@PathVariable("id") int id) {
		return service.getById(id);
	}

	@PostMapping("/save")
	private int save(@RequestBody HavaAlani t) {
		return service.saveOrUpdate(t).getId();
	}

	@PostMapping("/save/all")
	private List<Integer> saveAll(@RequestBody List<HavaAlani> t) {
		return service.saveOrUpdateAll(t);
	}
	
}
