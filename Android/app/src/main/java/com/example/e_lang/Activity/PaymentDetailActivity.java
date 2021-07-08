package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.PembayaranResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.io.File;
import java.io.IOException;
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

public class PaymentDetailActivity extends AppCompatActivity {
    TextView nama, status, deskripsi, imgPayProof, lelangStart, lelangFinished;
    ImageView image;
    Button button;
    private static final int PICK_IMAGE_REQUEST = 9544;
    Uri selectedImage;
    String part_image;

    // Permissions for accessing the storage
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

        PembayaranResponse pembayaran = (PembayaranResponse) getIntent().getSerializableExtra("pembayaran");

        nama = findViewById(R.id.et_name);
        deskripsi = findViewById(R.id.et_description);
        lelangStart = findViewById(R.id.et_lelang_start);
        lelangFinished = findViewById(R.id.et_lelang_end);
        image = findViewById(R.id.picture);
        status = findViewById(R.id.et_status);

        nama.setText(pembayaran.getBarang().getNama());
        deskripsi.setText(pembayaran.getBarang().getDeskripsi());
        lelangStart.setText(convertDateFormat(pembayaran.getBarang().getLelangStart()));
        lelangFinished.setText(convertDateFormat(pembayaran.getBarang().getLelangFinished()));
        status.setText(pembayaran.getStatus());

        Glide.with(getApplicationContext())
                .load(pembayaran.getBuktiPembayaran())
                .apply(new RequestOptions().override(150, 150))
                .into(image);
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