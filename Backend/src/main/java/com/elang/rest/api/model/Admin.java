package com.elang.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "update_at"}, allowGetters = true)
public class Admin implements Serializable {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String nama;
	
	@NotBlank
	private String email;
	

	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;


	@OneToMany(mappedBy = "admin", cascade = {
	        CascadeType.ALL
	    })
    private List < Barang > barangList;
	
	@OneToMany(mappedBy = "admin", cascade = {
	        CascadeType.ALL
	    })
    private List < Users > userList;
	
	@OneToMany(mappedBy = "admin", cascade = {
	        CascadeType.ALL
	    })
    private List < Pembayaran > pembayaranList;
	
	public List<Barang> getBarangList() {
		return barangList;
	}

	public void setBarangList(List<Barang> barangList) {
		this.barangList = barangList;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public List<Pembayaran> getPembayaranList() {
		return pembayaranList;
	}

	public void setPembayaranList(List<Pembayaran> pembayaranList) {
		this.pembayaranList = pembayaranList;
	}
	
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getNama(){
		return nama;
	}

	public void setNama(String nama){
		this.nama=nama;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
