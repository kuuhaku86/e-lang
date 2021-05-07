package com.elang.rest.api.model;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "updated_at"}, allowGetters = true)
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String password;
	
	@NotBlank
	private String nama;

	@NotBlank
	private String email;
	
	@NotBlank
	private String nomor_telpon;

	@NotBlank
	private String alamat;

	private String photo;
	
	@NotBlank
	private String verified;
	
	private Date verified_date;
	
	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;
	
	@OneToMany(mappedBy = "users", cascade = {
	        CascadeType.ALL
	    })
    private List < PenawaranBarang > penawaranBarangList;
	
	@OneToMany(mappedBy = "users", cascade = {
	        CascadeType.ALL
	    })
    private List < Barang > barangList;
	
	@OneToMany(mappedBy = "users", cascade = {
	        CascadeType.ALL
	    })
    private List < Pembayaran > pembayaranList;
	
	
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

	public List<Barang> getBarangList() {
		return barangList;
	}

	public void setBarangList(List<Barang> barangList) {
		this.barangList = barangList;
	}

	public List<Pembayaran> getPembayaranList() {
		return pembayaranList;
	}

	public void setPembayaranList(List<Pembayaran> pembayaranList) {
		this.pembayaranList = pembayaranList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomor_telpon() {
		return nomor_telpon;
	}

	public void setNomor_telpon(String nomor_telpon) {
		this.nomor_telpon = nomor_telpon;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public Date getVerified_date() {
		return verified_date;
	}

	public void setVerified_date(Date verified_date) {
		this.verified_date = verified_date;
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
}
