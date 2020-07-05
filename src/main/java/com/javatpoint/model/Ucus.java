package com.javatpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Ucus extends BaseModel {

	@ManyToOne()
	@JoinColumn(name="firma")
	private Firma firma;
	
	@ManyToOne()
	@JoinColumn(name="rota")
	private Rota rota;
	
	@Column
	private Double fiyat;
	
	@Column
	private int kontenjan;

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Double getFiyat() {
		return fiyat;
	}

	public void setFiyat(Double fiyat) {
		this.fiyat = fiyat;
	}

	public int getKontenjan() {
		return kontenjan;
	}

	public void setKontenjan(int kontenjan) {
		this.kontenjan = kontenjan;
	}
	
	
}