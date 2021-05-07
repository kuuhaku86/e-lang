package com.elang.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pembayaran")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_at", "update_at"}, allowGetters = true)
public class Pembayaran implements Serializable {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;
		
	@NotBlank
	private String status;
	
	private String bukti_pembayaran;
	
	@NotNull
	private java.util.Date deadline;
	
	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "penawaranBarang_id")
    private PenawaranBarang penawaranBarang;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;
	
	public PenawaranBarang getPenawaranBarang() {
		return penawaranBarang;
	}

	public void setPenawaranBarang(PenawaranBarang penawaranBarang) {
		this.penawaranBarang = penawaranBarang;
	}

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

	public Long getId() {
		return id;
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

	public String getBukti_pembayaran() {
		return bukti_pembayaran;
	}

	public void setBukti_pembayaran(String bukti_pembayaran) {
		this.bukti_pembayaran = bukti_pembayaran;
	}

	public java.util.Date getDeadline() {
		return deadline;
	}

	public void setDeadline(java.util.Date deadline) {
		this.deadline = deadline;
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
