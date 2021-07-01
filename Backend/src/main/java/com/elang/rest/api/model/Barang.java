package com.elang.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "barang")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "updated_at"}, allowGetters = true)
public class Barang implements Serializable {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;
	
	private String nama;
	
	private Long hargaAwal;

	private String deskripsi;
	
	private String status;
	
	private Date lelangStart;
	
	private Date lelangFinished;
	
	private Long userId;
	
	private String imageUrl;
	
	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;
	
	@ManyToMany(targetEntity = Kategori.class,cascade = CascadeType.ALL )
    private List<Kategori> kategoriList;
	
	@ManyToMany(targetEntity = PenawaranBarang.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<PenawaranBarang> penawaranBarangList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public List<Kategori> getKategoriList() {
		return kategoriList;
	}

	public void setKategoriList(List<Kategori> kategoriList) {
		this.kategoriList = kategoriList;
	}

	public List<PenawaranBarang> getPenawaranBarangList() {
		return penawaranBarangList;
	}

	public void setPenawaranBarangList(List<PenawaranBarang> penawaranBarangList) {
		this.penawaranBarangList = penawaranBarangList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLelangStart() {
		return lelangStart;
	}

	public void setLelangStart(Date lelangStart) {
		this.lelangStart = lelangStart;
	}

	public Date getLelangFinished() {
		return lelangFinished;
	}

	public void setLelangFinished(Date lelangFinished) {
		this.lelangFinished = lelangFinished;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getHargaAwal() {
		return hargaAwal;
	}

	public void setHargaAwal(Long hargaAwal) {
		this.hargaAwal = hargaAwal;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
