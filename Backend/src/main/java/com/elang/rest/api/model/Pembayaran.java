package com.elang.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

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
		
	private String status;
	
	private String buktiPembayaran;

	private Long userId;
	
	private Long adminId;
	
//	private Date deadline;
	
	private Long penawaranId;
	
	@Column(nullable = false, updatable = false)
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_at;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_at;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barang_id", referencedColumnName = "id")
    private Barang barang;

	public Barang getBarang() {
		return barang;
	}

	public void setBarang(Barang barang) {
		this.barang = barang;
	}
	
	
	public Long getUserId() {
		return userId;
	}

	public Long getPenawaranId() {
		return penawaranId;
	}

	public void setPenawaranId(Long penawaranId) {
		this.penawaranId = penawaranId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public String getBuktiPembayaran() {
		return buktiPembayaran;
	}

	public void setBuktiPembayaran(String buktiPembayaran) {
		this.buktiPembayaran = buktiPembayaran;
	}

//	public java.util.Date getDeadline() {
//		return deadline;
//	}
//
//	public void setDeadline(java.util.Date deadline) {
//		this.deadline = deadline;
//	}

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
