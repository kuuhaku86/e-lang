package com.elang.rest.api.service;

import java.io.IOException;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.model.Pembayaran;
import com.elang.rest.api.payload.BayarRequest;
import com.elang.rest.api.repository.BarangRepository;
import com.elang.rest.api.repository.PembayaranRepository;
import com.elang.rest.api.repository.PenawaranRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PembayaranService {

	@Autowired
	PenawaranRepository penawaranRepository;
	@Autowired
	PembayaranRepository pembayaranRepository;
	@Autowired
	BarangRepository barangRepository;
	
	@Autowired
	FileService fileService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public ResponseEntity<?> bayarBarang( String stringRequest, MultipartFile file){
		BayarRequest request = stringToPembayaran(stringRequest);
				
		Pembayaran pembayaran = new Pembayaran();
		
		
		pembayaran.setStatus("new");
		pembayaran.setUserId(request.getUserId());
		pembayaran.setPenawaranId(request.getPenawaranId());
		
		pembayaran.setBarang( penawaranRepository.getOne(pembayaran.getPenawaranId()).getBarang() );
		
		
		Pembayaran savedPembayaran = pembayaranRepository.save(pembayaran);
        
        String fileName = "Pembayaran_" + savedPembayaran.getId() + 
				file.getOriginalFilename().substring( file.getOriginalFilename().lastIndexOf('.') );
        savedPembayaran.setBuktiPembayaran( fileService.storeFile(file, fileName) );
        
        return new ResponseEntity<>(
        		pembayaranRepository.save(savedPembayaran),
        		HttpStatus.OK);		
	}
	
	public ResponseEntity<?> getPembayaranku(Long userId){
		return new ResponseEntity<>(
				pembayaranRepository.findByUserId(userId),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> getUnverified(){
		return new ResponseEntity<>(
				pembayaranRepository.findByStatus("new"),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> verifying(Long pembayaranId){
		if( pembayaranRepository.findById(pembayaranId).isEmpty() )
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
			
		Pembayaran pembayaran = pembayaranRepository.getOne(pembayaranId);
		pembayaran.setStatus("verified");
		pembayaranRepository.save(pembayaran);
		
		return new ResponseEntity<>(
				HttpStatus.OK);
		
	}

	
	/*--------------- Helper Function ------------------------------------------- */
	
	protected BayarRequest stringToPembayaran(String string) {
		try {
			return objectMapper.readValue(string, BayarRequest.class);
		}catch(IOException e) {
			System.out.print(e);
			return null;
		}
	}
	
}
