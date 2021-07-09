package com.elang.rest.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.model.Barang;
import com.elang.rest.api.model.Kategori;
import com.elang.rest.api.model.PenawaranBarang;
import com.elang.rest.api.repository.BarangRepository;
import com.elang.rest.api.repository.KategoriRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BarangService {

	@Autowired
	BarangRepository barangRepository;
	@Autowired
	KategoriRepository kategoriRepository;
	
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public ResponseEntity<?> createBarang(String stringBarang, MultipartFile image) {
		Barang newBarang = stringToBarang(stringBarang);
//		newBarang.setKategoriList( checkKategori( stringToList(stringList) ) );
				
		if( !userService.checkUser(newBarang.getUserId()) )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return saveBarang(newBarang, image);
	}
	
	public ResponseEntity<?> viewBarang(Long id) {
		if (barangRepository.findById(id).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(
			barangRepository.getOne(id),
    		HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateBarang(String stringBarang, MultipartFile image){
		Barang barang = stringToBarang(stringBarang);
//		barang.setKategoriList( checkKategori( stringToList(stringList) ) );
		
		if( barangRepository.findById(barang.getId()).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		if( !barang.getStatus().equals("new") ) 
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		
		return saveBarang(barang, image);
		
	}
	
	public ResponseEntity<?> deleteBarang(Long id) {
		if( barangRepository.findById(id).isEmpty() ) 
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		if( !barangRepository.getOne(id).getStatus().equals("new") ) 
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		barangRepository.deleteById(id);
		return new ResponseEntity<>(
				HttpStatus.OK);
	}

	public ResponseEntity<?> getBarangku(Long id){
		if( !userService.checkUser(id) )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(
				barangRepository.findByUserId(id),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> getUnverified(){
		return new ResponseEntity<>(
				barangRepository.findBystatus("new"),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> verifying(Long id){
		if (barangRepository.findById(id).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		Barang barang = barangRepository.getOne(id);
		barang.setStatus("verified");
		barangRepository.save(barang);
		
		return new ResponseEntity<>(
				HttpStatus.OK);
	}
	
	
	
	/*--------------- Helper Function ------------------------------------------- */
	
	private ResponseEntity<?> saveBarang(Barang barang, MultipartFile image) {
		barang.setStatus("new");
//		barang.setPenawaranBarangList( new ArrayList<PenawaranBarang>() );
		
        Barang savedBarang = barangRepository.save(barang);
        
        String fileName = "Barang_" + savedBarang.getId() + 
				image.getOriginalFilename().substring( image.getOriginalFilename().lastIndexOf('.') );
        savedBarang.setImageUrl( fileService.storeFile(image, fileName) );
        
        return new ResponseEntity<>(
        		barangRepository.save(savedBarang),
        		HttpStatus.OK);
	}
		
	protected Barang stringToBarang(String string) {
		try {
			return objectMapper.readValue(string, Barang.class);
		}catch(IOException e) {
			System.out.print(e);
			return null;
		}
	}
	
	protected List<String> stringToList(String string) {
		try {
			return objectMapper.readValue(string, new TypeReference<List<String>>() {});
		}catch(IOException e) {
			System.out.print(e);
			return null;
		}		
	}

	protected List<Kategori> checkKategori(List<String> kategoriList) {
		List<Kategori> list = new ArrayList<>();
		
		if(kategoriList == null)
			return list;
		if(kategoriList.isEmpty())
			return list;
		
		for(String k : kategoriList) {
			Kategori kategori = kategoriRepository.findByNama(k);
        	if(kategori == null) {
        		kategori = new Kategori(k);
        		kategori = kategoriRepository.save( kategori );
        	}
        	list.add( kategoriRepository.save(kategori) );
		}
		
		return list;
		
	}
		
}
