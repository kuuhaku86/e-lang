package com.example.e_lang.Entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Barang {
    public String nama;
    public LocalDateTime tanggalMulai;
    public LocalDateTime tanggalSelesai;

    public Barang(String nama, LocalDateTime tanggalMulai, LocalDateTime tanggalSelesai) {
        this.nama = nama;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }
}
