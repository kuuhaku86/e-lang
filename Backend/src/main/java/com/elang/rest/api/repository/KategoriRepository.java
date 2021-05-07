package com.elang.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Barang;
import com.elang.rest.api.model.Kategori;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long>, CrudRepository<Kategori, Long> {
	Kategori findBynama(String nama);
}

