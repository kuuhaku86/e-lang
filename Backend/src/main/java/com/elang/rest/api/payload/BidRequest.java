package com.elang.rest.api.payload;

public class BidRequest {

	private Long harga;
	private Long userId;
	private Long barangId;
	public Long getHarga() {
		return harga;
	}
	public void setHarga(Long harga) {
		this.harga = harga;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBarangId() {
		return barangId;
	}
	public void setBarangId(Long barangId) {
		this.barangId = barangId;
	}
	
	
	
}
