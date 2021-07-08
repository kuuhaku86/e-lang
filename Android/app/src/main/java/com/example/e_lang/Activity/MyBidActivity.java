package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBidActivity extends AppCompatActivity {
    TextView nama, hargaAwal, deskripsi, status, lelangStart, lelangFinished;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bid);

        PenawaranBarangResponse penawaranBarang = (PenawaranBarangResponse) getIntent().getSerializableExtra("penawaranBarang");

        nama = findViewById(R.id.et_name);
        hargaAwal = findViewById(R.id.et_price);
        deskripsi = findViewById(R.id.et_description);
        lelangStart = findViewById(R.id.et_lelang_start);
        lelangFinished = findViewById(R.id.et_lelang_end);
        image = findViewById(R.id.picture);

        nama.setText(penawaranBarang.getBarang().getNama());
        hargaAwal.setText(penawaranBarang.getHarga());
        deskripsi.setText(penawaranBarang.getBarang().getDeskripsi());
        lelangStart.setText(convertDateFormat(penawaranBarang.getBarang().getLelangStart()));
        lelangFinished.setText(convertDateFormat(penawaranBarang.getBarang().getLelangFinished()));

        Glide.with(getApplicationContext())
                .load(penawaranBarang.getBarang().getImageUrl())
                .apply(new RequestOptions().override(150, 150))
                .into(image);
    }

    private String convertDateFormat(String timestamp) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(timestamp.substring(0, 9));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        return dt1.format(date);
    }
}