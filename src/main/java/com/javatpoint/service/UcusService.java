package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.HibernateUtil;
import com.javatpoint.model.Bilet;
import com.javatpoint.model.Ucus;
import com.javatpoint.repository.UcusRepository;

@Service
public class UcusService{
	@Autowired
	UcusRepository baseRepository;

	public Ucus getById(int id) {
		Ucus result = null;
		Session ses = null;
		try {
			ses =  HibernateUtil.openSession();
			Query<Ucus> query = ses.createQuery("FROM Ucus Where id = :id ",Ucus.class);
			query.setParameter("id",id);
			result = query.getSingleResult();
			if (result != null) {
				result.setFiyat(fiyatBelirler(result));;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(ses);
		}
		return result;
	}
	
	public Ucus saveOrUpdate(Ucus t) {
		baseRepository.save(t);
		return t;
	}

	public List<Integer> saveOrUpdateAll(List<Ucus> t) {
		return t.stream().map(p -> saveOrUpdate(p).getId()).collect(Collectors.toList());
	}
	
	
	public List<Ucus> filter(Ucus t) {
		List<Ucus> result = null;
		Session ses = null;
		try {
			ses =  HibernateUtil.openSession();
			CriteriaBuilder cb = ses.getCriteriaBuilder();
			CriteriaQuery<Ucus> cq = cb.createQuery(Ucus.class);
			Root<Ucus> rootEntity = cq.from(Ucus.class);
			List<Predicate> criteria = new ArrayList<Predicate>();
			
			if (t.getId() != null) {
				criteria.add(cb.equal(rootEntity.get("id"), t.getId()));
			}
			if (t.getRota() != null && t.getRota().getKalkis() != null && t.getRota().getKalkis().getId() != null ) {
				criteria.add(cb.equal(rootEntity.get("rota").get("kalkis").get("id"), t.getRota().getKalkis().getId()));
			}
			
			cq.select(rootEntity).where(criteria.toArray(new Predicate[] {}));
			result =ses.createQuery(cq).getResultList();
			
			if (result != null ) {
				result.forEach(p -> p.setFiyat(fiyatBelirler(p)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSession(ses);
		}
		return result;
	}
	
	public double fiyatBelirler(Ucus u) {
		double biletSayisi = 0;
		double fiyat = 0;
		BiletService bs = new BiletService();
		Bilet flt = new Bilet();
		flt.setAktif(true);
		List<Bilet> biletler =  bs.filter(flt);
		if (biletler != null) {
			biletSayisi = biletler.size();
		}
		double oran = (int) (biletSayisi/u.getKontenjan()*10) ;
		if (oran > 1) {
			fiyat = u.getFiyat()* (oran/10.0); 
		}else {
			fiyat = u.getFiyat();
		}
		return fiyat;
		
		
	}
	
}