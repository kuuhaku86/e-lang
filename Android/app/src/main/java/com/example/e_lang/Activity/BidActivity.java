package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BidActivity extends AppCompatActivity {
    TextView nama, hargaAwal, deskripsi, status, lelangStart, lelangFinished;
    ImageView image;
    BarangResponse barang;
    Button button;
    EditText harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        barang = (BarangResponse) getIntent().getSerializableExtra("barang");

        nama = findViewById(R.id.et_name);
        hargaAwal = findViewById(R.id.et_price);
        deskripsi = findViewById(R.id.et_description);
        status = findViewById(R.id.et_status);
        lelangStart = findViewById(R.id.et_lelang_start);
        lelangFinished = findViewById(R.id.et_lelang_end);
        image = findViewById(R.id.picture);
        button = findViewById(R.id.btn_bid);
        harga = findViewById(R.id.harga);

        nama.setText(barang.getNama());
        hargaAwal.setText(barang.getHargaAwal());
        deskripsi.setText(barang.getDeskripsi());
        status.setText(barang.getStatus());
        lelangStart.setText(convertDateFormat(barang.getLelangStart()));
        lelangFinished.setText(convertDateFormat(barang.getLelangFinished()));

        Glide.with(getApplicationContext())
                .load(barang.getImageUrl())
                .apply(new RequestOptions().override(150, 150))
                .into(image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String harga_text = harga.getText().toString();
                Integer harga_int = Integer.parseInt(harga_text);
                Integer hargaAwal_int = Integer.parseInt(hargaAwal.getText().toString());

                if (harga_text.length() == 0 ||
                        harga_int < hargaAwal_int
                ) {
                    Toast.makeText(BidActivity.this, getString(R.string.input_not_full), Toast.LENGTH_SHORT).show();

                    return;
                }

                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
                String id = sharedPref.getString(getString(R.string.id), "None");

                Call<PenawaranBarangResponse> call = RemoteDataSource.apiService.bid(harga_int, Integer.parseInt(id), Integer.parseInt(barang.getId()));

                call.enqueue(new Callback<PenawaranBarangResponse>() {
                    @Override
                    public void onResponse(Call<PenawaranBarangResponse> call, Response<PenawaranBarangResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(getApplicationContext(), "Penawaran Success", Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BidActivity.this.finish();
                                }
                            }, 2000);
                        } else {
                            Toast.makeText(getApplicationContext(), "Penawaran Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PenawaranBarangResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String convertDateFormat(String timestamp) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(timestamp.substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        return dt1.format(date);
    }
}