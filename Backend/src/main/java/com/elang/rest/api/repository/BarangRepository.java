package com.elang.rest.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elang.rest.api.model.Barang;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long>, CrudRepository<Barang, Long> {
	List<Barang> findBynama(String nama);
//	List<Barang> findBykategoriList_nama(String nama);
	List<Barang> findBystatus(String status);
	List<Barang> findByUserId(Long userId);
//	List<Barang> findByPenawaranBarangList_UserId(Long userId);
	
	@Query("select a from Barang a where a.lelangStart <= :lelangStart and a.status='verified' and a.lelangFinished >= :lelangFinished")
    List<Barang> getPelelangan(@Param("lelangStart") Date lelangStart, @Param("lelangFinished") Date lelangFinished);
}
