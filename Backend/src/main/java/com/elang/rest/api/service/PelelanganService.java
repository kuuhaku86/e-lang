package com.elang.rest.api.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.model.Barang;
import com.elang.rest.api.model.Pembayaran;
import com.elang.rest.api.model.PenawaranBarang;
import com.elang.rest.api.payload.BidRequest;
import com.elang.rest.api.repository.BarangRepository;
import com.elang.rest.api.repository.PembayaranRepository;
import com.elang.rest.api.repository.PenawaranRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PelelanganService {

	@Autowired
	BarangRepository barangRepository;
	@Autowired
	PenawaranRepository penawaranRepository;
	@Autowired
	PembayaranRepository pembayaranRepository;
	
	@Autowired
	UserService userService;
	
	public ResponseEntity<?> getAllBarangLelang(){
		return new ResponseEntity<>(
				barangRepository.findBystatus("processed"),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> getBarangBidku(Long userId){
		if( !userService.checkUser(userId) )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(
				barangRepository.findByPenawaranBarangList_UserId(userId),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> bidBarang(BidRequest request){
		if (barangRepository.findById(request.getBarangId()).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		Barang barang = barangRepository.getOne(request.getBarangId()); 
		
		if( !barang.getStatus().equals("processed") )
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		
		PenawaranBarang penawaran = new PenawaranBarang();
		
		penawaran.setHarga(request.getHarga());
		penawaran.setUserId(request.getUserId());
		
		PenawaranBarang savedPenawaran = penawaranRepository.save(penawaran);
		barang.getPenawaranBarangList().add(savedPenawaran);
		
		barangRepository.save(barang);
		return new ResponseEntity<>(
				savedPenawaran,
				HttpStatus.OK);
	}
	
}
