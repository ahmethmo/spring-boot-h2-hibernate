package com.javatpoint.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bilet")
public class Bilet extends BaseModel{
	
	@ManyToOne()
	@JoinColumn(name="ucus")
	private Ucus ucus;
	
	@Column(name = "MUSTERI_AD_SOYAD")
	private String musteriAdSoyad;
	
	@Column(name = "KREDI_KARTI")
	private String krediKarti;
	
	@Column
	private Boolean aktif;
	
	@Column
	private Double fiyat;

	public Ucus getUcus() {
		return ucus;
	}

	public void setUcus(Ucus ucus) {
		this.ucus = ucus;
	}

	public String getMusteriAdSoyad() {
		return musteriAdSoyad;
	}

	public void setMusteriAdSoyad(String musteriAdSoyad) {
		this.musteriAdSoyad = musteriAdSoyad;
	}

	public String getKrediKarti() {
		return krediKarti;
	}

	public void setKrediKarti(String krediKarti) {
		this.krediKarti = krediKarti;
	}

	public Boolean isAktif() {
		return aktif;
	}

	public void setAktif(Boolean aktif) {
		this.aktif = aktif;
	}
	
	public Double getFiyat() {
		return fiyat;
	}
	
	public void setFiyat(Double fiyat) {
		this.fiyat = fiyat;
	}
	
	public void maskele () {
		String maske = "####XXXXXXXX####";
		Arrays.asList(" ", ",", ".", "-", "_").forEach(p -> this.setKrediKarti(this.getKrediKarti().replace(p, "")));
		String b = "";
		for (int i = 0; i < this.getKrediKarti().length(); i++) {
			if (maske.charAt(i) == '#') {
				b += this.getKrediKarti().charAt(i); 
			}else if (maske.charAt(i) == 'X') {
				b += "*"; 
			}
		}
		this.setKrediKarti(b);
		
	}

}