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

import com.elang.rest.api.service.PembayaranService;

@RestController
@RequestMapping("/pembayaran")
public class PembayaranController {
	
	@Autowired
	PembayaranService pembayaranService;
	
	@PostMapping(value = "/ajukan", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> bayarBarang(@RequestPart("pembayaran") String pembayaran, @RequestPart("file") MultipartFile file){
		return pembayaranService.bayarBarang(pembayaran, file);
	}
	
	// lihat semua barang yang belum diverif (oleh admin)
	@GetMapping("/unverified")
	public ResponseEntity<?> getUnverified(){
		return pembayaranService.getUnverified();
	}	
	
	// verif barang (oleh admin)
	@GetMapping("/verifying")
	public ResponseEntity<?> verifying(Long pembayaranId){
		return pembayaranService.verifying(pembayaranId);
	}
	
}
