package com.example.e_lang.DataSource.Response;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	@SerializedName("nomorTelpon")
	private String nomorTelpon;

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("verified")
	private String verified;

	@SerializedName("adminId")
	private String adminId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	public void setNomorTelpon(String nomorTelpon){
		this.nomorTelpon = nomorTelpon;
	}

	public String getNomorTelpon(){
		return nomorTelpon;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

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

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setVerified(String verified){
		this.verified = verified;
	}

	public String getVerified(){
		return verified;
	}

	public void setAdminId(String adminId){
		this.adminId = adminId;
	}

	public String getAdminId(){
		return adminId;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"nomorTelpon = '" + nomorTelpon + '\'' + 
			",password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",verified = '" + verified + '\'' + 
			",adminId = '" + adminId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}