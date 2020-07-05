package com.javatpoint.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Rota extends BaseModel{
	
	@ManyToOne()
	@JoinColumn(name="kalkis")
	private HavaAlani kalkis;
	
	@ManyToOne()
	@JoinColumn(name="inis")
	private HavaAlani inis;

	public HavaAlani getKalkis() {
		return kalkis;
	}

	public void setKalkis(HavaAlani kalkis) {
		this.kalkis = kalkis;
	}

	public HavaAlani getInis() {
		return inis;
	}

	public void setInis(HavaAlani inis) {
		this.inis = inis;
	}

	
}