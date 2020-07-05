package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.model.HavaAlani;
import com.javatpoint.repository.HavaAlaniRepository;


@Service
public class HavaAlaniService{
	@Autowired
	HavaAlaniRepository baseRepository;

	public HavaAlani getById(int id) {
		return baseRepository.findById(id).get();
	}
	
	public List<HavaAlani> getAll() {
		List<HavaAlani> ts = new ArrayList<HavaAlani>();
		baseRepository.findAll().forEach(t -> ts.add(t));
		return ts;
	}
	public HavaAlani saveOrUpdate(HavaAlani t) {
		baseRepository.save(t);
		return t;
	}

	public List<Integer> saveOrUpdateAll(List<HavaAlani> t) {
		return t.stream().map(p -> saveOrUpdate(p).getId()).collect(Collectors.toList());
	}
}