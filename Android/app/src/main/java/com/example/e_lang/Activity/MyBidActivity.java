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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PembayaranResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.DataSource.Response.UserResponse;
import com.example.e_lang.MainActivity;
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

public class MyBidActivity extends AppCompatActivity {
    TextView nama, hargaAwal, deskripsi, imgPayProof, lelangStart, lelangFinished;
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
        setContentView(R.layout.activity_my_bid);

        PenawaranBarangResponse penawaranBarang = (PenawaranBarangResponse) getIntent().getSerializableExtra("penawaranBarang");

        nama = findViewById(R.id.et_name);
        hargaAwal = findViewById(R.id.et_price);
        deskripsi = findViewById(R.id.et_description);
        lelangStart = findViewById(R.id.et_lelang_start);
        lelangFinished = findViewById(R.id.et_lelang_end);
        image = findViewById(R.id.picture);
        imgPayProof = findViewById(R.id.img_pay_proof);
        button = findViewById(R.id.btn_pay);

        nama.setText(penawaranBarang.getBarang().getNama());
        hargaAwal.setText(penawaranBarang.getHarga());
        deskripsi.setText(penawaranBarang.getBarang().getDeskripsi());
        lelangStart.setText(convertDateFormat(penawaranBarang.getBarang().getLelangStart()));
        lelangFinished.setText(convertDateFormat(penawaranBarang.getBarang().getLelangFinished()));

        canPay(penawaranBarang.getBarang().getLelangFinished());

        Glide.with(getApplicationContext())
                .load(penawaranBarang.getBarang().getImageUrl())
                .apply(new RequestOptions().override(150, 150))
                .into(image);

        imgPayProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(v);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File imageFile =new File(part_image);
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
                String id_text = sharedPref.getString(getString(R.string.id), "-");

                if (!imageFile.exists()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.input_not_full), Toast.LENGTH_SHORT).show();

                    return;
                }

                String user = String.format("{ \"userId\":%s, \"penawaranId\":%s }",
                        id_text,
                        penawaranBarang.getId()
                );
                RequestBody image = RequestBody.create(MediaType.parse("image/*"), imageFile);
                RequestBody userString = RequestBody.create(MediaType.parse("text/plain"),
                        user);

                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", imageFile.getName(), image);
                Call<PembayaranResponse> call = RemoteDataSource.apiService.createPembayaran(userString, fileToUpload);

                call.enqueue(new Callback<PembayaranResponse>() {
                    @Override
                    public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(getApplicationContext(), "Pembayaran Success", Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Pembayaran Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void canPay(String lelangFinished) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null, todayDate = new Date();
        try {
            date = formatter.parse(lelangFinished.substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date.after(todayDate)) {
            button.setVisibility(View.GONE);
            imgPayProof.setVisibility(View.GONE);
        }
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

    // Method to get the absolute path of the selected image from its URI
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedImage = data.getData();                                                         // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(
                        selectedImage,
                        imageProjection,
                        null,
                        null,
                        null
                );

                if(cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    imgPayProof.setText(part_image);                                                        // Get the image file absolute path
                    Bitmap bitmap = null;
                    cursor.close();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Method for starting the activity for selecting image from phone storage
    public void pick(View view) {
        verifyStoragePermissions(this);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}