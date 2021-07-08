package com.example.e_lang.DataSource.Response;

import com.example.e_lang.Entity.Barang;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PenawaranBarangResponse implements Serializable {

	@SerializedName("harga")
	private String harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("userId")
	private String userId;

	@SerializedName("barang")
	private BarangResponse barang;

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public void setBarang(BarangResponse barang){
		this.barang = barang;
	}

	public BarangResponse getBarang(){
		return barang;
	}

	@Override
 	public String toString(){
		return 
			"PenawaranBarangResponse{" + 
			"harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}