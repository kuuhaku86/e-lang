package com.example.e_lang.DataSource.Request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("nama")
    String nama;

    @SerializedName("password")
    String password;

    @SerializedName("email")
    String email;

    @SerializedName("nomorTelpon")
    String nomorTelpon;

    @SerializedName("alamat")
    String alamat;

    public RegisterRequest(String nama, String password, String email, String nomorTelpon, String alamat) {
        this.nama = nama;
        this.password = password;
        this.email = email;
        this.nomorTelpon = nomorTelpon;
        this.alamat = alamat;
    }
}
