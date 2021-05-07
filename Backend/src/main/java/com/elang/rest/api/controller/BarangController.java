package com.elang.rest.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elang.rest.api.model.Barang;
import com.elang.rest.api.service.BarangService;

@RestController
@RequestMapping("/barang")
public class BarangController {

	BarangService barangService;
	
	public BarangController(BarangService barangService) {
        this.barangService = barangService;
    }
	

	@PostMapping("/")
	public Barang createBarang(@Valid @RequestBody Barang barang) {        
		return barangService.createBarang(barang);
	}
	
	@GetMapping("/")
	public List<Barang> getAll(){
		return barangService.findAll();
	}
	
	@GetMapping("/test")
	public Set<Barang> getByKategori(@RequestBody List<String> kategoriList){
		return barangService.findBykategori(kategoriList);
	}
	
}
