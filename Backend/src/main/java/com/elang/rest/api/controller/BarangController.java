package com.elang.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.service.BarangService;

@RestController
@RequestMapping("/barang")
public class BarangController {

	@Autowired
	BarangService barangService;
	
	// buat barang baru
//	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<?> createBarang(@RequestPart("barang") String barang, @RequestPart("kategoriList") String kategoriList, @RequestPart("image") MultipartFile image){
//		return barangService.createBarang(barang, kategoriList, image);
//	}
	
	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createBarang(@RequestPart("barang") String barang, @RequestPart("image") MultipartFile image){
		return barangService.createBarang(barang, image);
	}
	
	// lihat 1 barang
	@GetMapping("/view")
	public ResponseEntity<?> viewBarang(Long barangId){
		return barangService.viewBarang(barangId);
	}
	
	// edit/update barang
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateBarang(@RequestPart("barang") String barang, @RequestPart("image") MultipartFile image){
		return barangService.updateBarang(barang, image);
		
	}
	
	// delete barang (yang belum dilelang)
	@PostMapping("/delete")
	public ResponseEntity<?> deleteBarang(Long barangId){
		return barangService.deleteBarang(barangId);
	}
	
	//lihat semua barang miliknya (yang diajukan ke pelelangan)
	@GetMapping("/mine")
	public ResponseEntity<?> getBarangku(Long userId){
		return barangService.getBarangku(userId);
	}
	
	// lihat semua barang yang belum diverif (oleh admin)
	@GetMapping("/unverified")
	public ResponseEntity<?> getUnverified(){
		return barangService.getUnverified();
	}
	
	// verif barang (oleh admin)
	@PostMapping("/verifying")
	public ResponseEntity<?> verifying(Long barangId){
		return barangService.verifying(barangId);
	}
	
			
}
