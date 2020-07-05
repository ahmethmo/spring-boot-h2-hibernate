package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.HibernateUtil;
import com.javatpoint.model.Bilet;
import com.javatpoint.repository.BiletRepository;

@Service
public class BiletService{
	@Autowired
	BiletRepository baseRepository;
	
	public Bilet getById(int id) {
		return baseRepository.findById(id).get();
	}
	
	public List<Bilet> getAll() {
		List<Bilet> ts = new ArrayList<Bilet>();
		baseRepository.findAll().forEach(t -> ts.add(t));
		return ts;
	}

	public Bilet saveOrUpdate(Bilet t){
		t.maskele();
		t.setAktif(true);
		UcusService bs = new UcusService();
		t.setUcus(bs.getById(t.getUcus().getId()));
		t.setFiyat(t.getUcus().getFiyat());
		baseRepository.save(t);
		return t;
	}
	
	public List<Integer> saveOrUpdateAll(List<Bilet> t) {
		return t.stream().map(p -> saveOrUpdate(p).getId()).collect(Collectors.toList());
	}
	
	
	public List<Bilet> filter(Bilet t) {
		List<Bilet> result = null;
		Session ses = null;
		try {
			ses =  HibernateUtil.openSession();
			StringBuilder hql = new StringBuilder("FROM Bilet Where 1=1 ");

			if (t.getId() != null) {
				hql.append(" AND id = :id ");
			}
			if (t.isAktif() != null) {
				hql.append(" AND aktif = :aktif ");
			}
			
			Query<Bilet> query = ses.createQuery(hql.toString(),Bilet.class);
			
			if (t.getId() != null) {
				query.setParameter("id",t.getId());
			}
			if (t.isAktif() != null) {
				query.setParameter("aktif",t.isAktif());
			}
			
			result = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(ses);
		}
		return result;
	}
	
	public void iptalEt (int id) {
		Session ses = null;
		try {
			ses =  HibernateUtil.openSession();
			ses.beginTransaction();
			StringBuilder hql = new StringBuilder("update Bilet set aktif = 'false' Where id = :id ");
			Query<?> query = ses.createQuery(hql.toString());
			query.setParameter("id",id);
			query.executeUpdate();
			ses.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(ses);
		}
	}
}