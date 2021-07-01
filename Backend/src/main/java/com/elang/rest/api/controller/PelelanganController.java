package com.elang.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elang.rest.api.payload.BidRequest;
import com.elang.rest.api.service.PelelanganService;

@RestController
@RequestMapping("/pelelangan")
public class PelelanganController {
	
	@Autowired
	PelelanganService pelelanganService;
	
	
	@GetMapping("/")
	public ResponseEntity<?> getAllBarangLelang(){
		return pelelanganService.getAllBarangLelang();
	}
	
	@GetMapping("/bidku")
	public ResponseEntity<?> getBarangBidku(Long userId){
		return pelelanganService.getBarangBidku(userId);
	}
	
//	@GetMapping("/view") ->pakai /barang/view aja
	
	@PostMapping("/bid")
	public ResponseEntity<?> bidBarang(BidRequest request){
		return pelelanganService.bidBarang(request);
	}
	
}
