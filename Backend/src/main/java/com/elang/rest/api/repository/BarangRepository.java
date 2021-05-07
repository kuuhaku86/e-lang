package com.elang.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Barang;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long>, CrudRepository<Barang, Long> {
	List<Barang> findBynama(String nama);
	List<Barang> findBykategoriList_nama(String nama);
	
//	@Query("Select b from Barang b where b.termasuk.kategori = :kategori")
//    List<Barang> getTermasukList(@Param("kategori") String kategori);
}
