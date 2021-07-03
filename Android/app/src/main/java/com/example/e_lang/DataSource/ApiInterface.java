package com.example.e_lang.DataSource;

import com.example.e_lang.DataSource.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);
}
