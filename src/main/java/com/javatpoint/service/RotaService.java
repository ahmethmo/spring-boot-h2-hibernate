package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.model.Rota;
import com.javatpoint.repository.RotaRepository;

@Service
public class RotaService{
	@Autowired
	RotaRepository baseRepository;

	public Rota getById(int id) {
		return baseRepository.findById(id).get();
	}
	
	public List<Rota> getAll() {
		List<Rota> ts = new ArrayList<Rota>();
		baseRepository.findAll().forEach(t -> ts.add(t));
		return ts;
	}
	
	public Rota saveOrUpdate(Rota t) {
		baseRepository.save(t);
		return t;
	}

	public List<Integer> saveOrUpdateAll(List<Rota> t) {
		return t.stream().map(p -> saveOrUpdate(p).getId()).collect(Collectors.toList());
	}
}