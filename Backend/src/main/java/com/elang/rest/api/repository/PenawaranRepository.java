package com.elang.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.PenawaranBarang;

@Repository
public interface PenawaranRepository extends JpaRepository<PenawaranBarang, Long>, CrudRepository<PenawaranBarang, Long> {
	List<PenawaranBarang> findByUserId(Long userId);
}
