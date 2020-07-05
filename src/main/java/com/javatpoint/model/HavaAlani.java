package com.javatpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HAVA_ALANI")
public class HavaAlani extends BaseModel {
	
	@Column
	private String adi;

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}
}