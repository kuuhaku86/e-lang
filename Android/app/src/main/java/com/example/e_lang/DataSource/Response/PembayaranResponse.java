package com.example.e_lang.DataSource.Response;

import com.google.gson.annotations.SerializedName;

public class PembayaranResponse{

	@SerializedName("buktiPembayaran")
	private String buktiPembayaran;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("adminId")
	private String adminId;

	@SerializedName("penawaranId")
	private String penawaranId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("userId")
	private String userId;

	@SerializedName("status")
	private String status;

	@SerializedName("barang")
	private BarangResponse barang;

	public void setBuktiPembayaran(String buktiPembayaran){
		this.buktiPembayaran = buktiPembayaran;
	}

	public String getBuktiPembayaran(){
		return buktiPembayaran;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setAdminId(String adminId){
		this.adminId = adminId;
	}

	public String getAdminId(){
		return adminId;
	}

	public void setPenawaranId(String penawaranId){
		this.penawaranId = penawaranId;
	}

	public String getPenawaranId(){
		return penawaranId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setBarang(BarangResponse barang){
		this.barang = barang;
	}

	public BarangResponse getBarang(){
		return barang;
	}


	@Override
 	public String toString(){
		return 
			"PembayaranResponse{" + 
			"buktiPembayaran = '" + buktiPembayaran + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",adminId = '" + adminId + '\'' + 
			",penawaranId = '" + penawaranId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",userId = '" + userId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}