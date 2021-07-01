package com.elang.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Pembayaran;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long>, CrudRepository<Pembayaran, Long> {
	List<Pembayaran> findByStatus(String status);
}
