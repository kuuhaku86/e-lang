package com.example.e_lang.DataSource.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BarangResponse implements Serializable {

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("lelangStart")
	private String lelangStart;

	@SerializedName("penawaranBarangList")
	private List<PenawaranBarangResponse> penawaranBarangList;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("hargaAwal")
	private String hargaAwal;

	@SerializedName("id")
	private String id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("userId")
	private String userId;

	@SerializedName("status")
	private String status;

	@SerializedName("lelangFinished")
	private String lelangFinished;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setLelangStart(String lelangStart){
		this.lelangStart = lelangStart;
	}

	public String getLelangStart(){
		return lelangStart;
	}

	public void setPenawaranBarangList(List<PenawaranBarangResponse> penawaranBarangList){
		this.penawaranBarangList = penawaranBarangList;
	}

	public List<PenawaranBarangResponse> getPenawaranBarangList(){
		return penawaranBarangList;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setHargaAwal(String hargaAwal){
		this.hargaAwal = hargaAwal;
	}

	public String getHargaAwal(){
		return hargaAwal;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
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

	public void setLelangFinished(String lelangFinished){
		this.lelangFinished = lelangFinished;
	}

	public String getLelangFinished(){
		return lelangFinished;
	}

	@Override
 	public String toString(){
		return 
			"BarangResponse{" + 
			"nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",lelangStart = '" + lelangStart + '\'' + 
			",penawaranBarangList = '" + penawaranBarangList + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",hargaAwal = '" + hargaAwal + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",userId = '" + userId + '\'' + 
			",status = '" + status + '\'' + 
			",lelangFinished = '" + lelangFinished + '\'' + 
			"}";
	}
}