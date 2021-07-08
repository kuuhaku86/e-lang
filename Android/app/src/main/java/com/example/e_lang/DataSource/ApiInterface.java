package com.example.e_lang.DataSource;

import com.example.e_lang.DataSource.Request.RegisterRequest;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PembayaranResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.DataSource.Response.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    @GET("barang/mine")
    Call<List<BarangResponse>> getAllBarangku(@Query("userId") String id);

    @GET("pelelangan/")
    Call<List<BarangResponse>> getListBarangDilelang();

    @FormUrlEncoded
    @POST("pelelangan/bid")
    Call<PenawaranBarangResponse> bid(@Field("harga") Integer harga, @Field("userId") Integer userId, @Field("barangId") Integer barangId);

    @GET("pelelangan/bidku")
    Call<List<PenawaranBarangResponse>> getAllMyBid(@Query("userId") String id);

    @Multipart
    @POST("pembayaran/ajukan")
    Call<PembayaranResponse> createPembayaran(@Part("pembayaran") RequestBody pembayaran, @Part MultipartBody.Part file);

    @GET("pembayaran/mine")
    Call<List<PembayaranResponse>> getAllPayment(@Query("userId") String id);
}
