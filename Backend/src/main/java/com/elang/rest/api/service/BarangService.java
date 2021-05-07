package com.elang.rest.api.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.elang.rest.api.model.Barang;
import com.elang.rest.api.model.Kategori;
import com.elang.rest.api.repository.BarangRepository;
import com.elang.rest.api.repository.KategoriRepository;

@Service
public class BarangService {

	BarangRepository barangRepository;
	KategoriRepository kategoriRepository;
	
	public BarangService(BarangRepository barangRepository, KategoriRepository kategoriRepository) {
        this.barangRepository = barangRepository;
        this.kategoriRepository = kategoriRepository;
    }
	
	
	@Transactional
	public Barang createBarang(Barang barang) {
		Barang newBarang = new Barang();
        
        newBarang.setNama(barang.getNama());
        newBarang.setHarga_awal(barang.getHarga_awal());
        newBarang.setPhoto(barang.getPhoto());
        newBarang.setDeskripsi(barang.getDeskripsi());
        newBarang.setStatus("new");
        newBarang.setLelang_start(barang.getLelang_start());
        newBarang.setLelang_finished(barang.getLelang_finished());

        List<Kategori> kategoriList = new ArrayList<>(); 
        for(Kategori k : barang.getKategoriList()){
        	Kategori kategori = kategoriRepository.findBynama(k.getNama());
        	if(kategori == null) 
        		kategori = kategoriRepository.save(k);
    		kategoriList.add(kategori);
    	}
        newBarang.setKategoriList(kategoriList);        
        
        Barang savedBarang = barangRepository.save(newBarang);
        if (barangRepository.findById(savedBarang.getId()).isPresent())
            return savedBarang;
        else
        	return null;
	}
	
	public List<Barang> findAll() {
		return barangRepository.findAll();
	}
	
	public Set<Barang> findBykategori(List<String> kategoriList) {
		Set<Barang> barangSet = new HashSet<Barang>();
		for(String kategori : kategoriList)
			barangSet.addAll(barangRepository.findBykategoriList_nama(kategori));
				
		return barangSet;
	}
}
