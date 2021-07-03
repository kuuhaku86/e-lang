package com.elang.rest.api.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "kategori")
@EntityListeners(AuditingEntityListener.class)
public class Kategori implements Serializable {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;
	
	private String nama;
	
	public Kategori(String nama) {
		super();
		this.nama = nama;
	}
	
	public Kategori(Long id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}
	
	public Kategori() {
		super();
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
		
}
