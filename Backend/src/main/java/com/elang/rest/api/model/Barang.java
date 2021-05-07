package com.elang.rest.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "barang")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "updated_at"}, allowGetters = true)
public class Barang implements Serializable {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	private int penawaran_id;
	
	@NotBlank
	private String nama;
	
	@NotNull
	private int harga_awal;

	@NotBlank
	private String photo;

	private String deskripsi;
	
	private String status;
	
	@NotNull
	private Date lelang_start;
	
	@NotNull
	private Date lelang_finished;
	

	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;
			
	@OneToMany(mappedBy = "barang", cascade = {
	        CascadeType.ALL
	    })
    private List < PenawaranBarang > penawaranBarangList;
	
	@ManyToMany(targetEntity = Kategori.class,cascade = CascadeType.ALL )
    private List<Kategori> kategoriList;
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<PenawaranBarang> getPenawaranBarangList() {
		return penawaranBarangList;
	}

	public void setPenawaranBarangList(List<PenawaranBarang> penawaranBarangList) {
		this.penawaranBarangList = penawaranBarangList;
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

	public void setId(Long id) {
		this.id = id;
	}

	public int getPenawaran_id() {
		return penawaran_id;
	}

	public void setPenawaran_id(int penawaran_id) {
		this.penawaran_id = penawaran_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLelang_start() {
		return lelang_start;
	}

	public void setLelang_start(Date lelang_start) {
		this.lelang_start = lelang_start;
	}

	public Date getLelang_finished() {
		return lelang_finished;
	}

	public void setLelang_finished(Date lelang_finished) {
		this.lelang_finished = lelang_finished;
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

	public int getHarga_awal() {
		return harga_awal;
	}

	public void setHarga_awal(int harga_awal) {
		this.harga_awal = harga_awal;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	
		
}
