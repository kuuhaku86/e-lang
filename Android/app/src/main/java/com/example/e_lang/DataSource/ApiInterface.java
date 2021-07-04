package com.example.e_lang.DataSource;

import com.example.e_lang.DataSource.Request.RegisterRequest;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("user/register")
    Call<UserResponse> register(@Part("user") RequestBody user, @Part MultipartBody.Part image);

    @Multipart
    @POST("barang/create")
    Call<BarangResponse> createBarang(@Part("barang") RequestBody barang, @Part MultipartBody.Part image);
}
