package com.elang.rest.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
		Date now = new Date();
		return new ResponseEntity<>(
				barangRepository.getPelelangan( now,now ),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> getBarangBidku(Long userId){
		if( !userService.checkUser(userId) )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(
				penawaranRepository.findByUserId(userId),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> bidBarang(BidRequest request){
		if (barangRepository.findById(request.getBarangId()).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		Barang barang = barangRepository.getOne(request.getBarangId()); 
		
		if( barang.getStatus().equals("new") )
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		
		barang.setHargaAwal(request.getHarga());
		barangRepository.save(barang);
		PenawaranBarang penawaran = new PenawaranBarang();
		
		penawaran.setHarga(request.getHarga());
		penawaran.setUserId(request.getUserId());
		penawaran.setBarang(barang);
		
		PenawaranBarang savedPenawaran = penawaranRepository.save(penawaran);
//		barang.getPenawaranBarangList().add(savedPenawaran);
		
		barangRepository.save(barang);
		return new ResponseEntity<>(
				savedPenawaran,
				HttpStatus.OK);
	}
	
}
