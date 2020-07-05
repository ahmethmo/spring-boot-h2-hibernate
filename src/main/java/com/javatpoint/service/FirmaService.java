package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.model.Firma;
import com.javatpoint.repository.FirmaRepository;

@Service
public class FirmaService{
	@Autowired
	FirmaRepository baseRepository;

	public Firma getById(int id) {
		return baseRepository.findById(id).get();
	}
	
	public List<Firma> getAll() {
		List<Firma> ts = new ArrayList<Firma>();
		baseRepository.findAll().forEach(t -> ts.add(t));
		return ts;
	}

	public Firma saveOrUpdate(Firma t) {
		baseRepository.save(t);
		return t;
	}
	
	public List<Integer> saveOrUpdateAll(List<Firma> t) {
		return t.stream().map(p -> saveOrUpdate(p).getId()).collect(Collectors.toList());
	}
	
}