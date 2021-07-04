package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.UserResponse;
import com.example.e_lang.MainActivity;
import com.example.e_lang.R;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjukanPelelanganActivity extends AppCompatActivity {
    TextView imgPath;
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
        setContentView(R.layout.activity_ajukan_pelelangan);
        Button button = (Button) findViewById(R.id.btn_create_auction);
        EditText name = (EditText) findViewById(R.id.et_name);
        EditText hargaAwal = (EditText) findViewById(R.id.et_price);
        EditText deskripsi = (EditText) findViewById(R.id.et_description);
        EditText lelangStart = (EditText) findViewById(R.id.et_lelang_start);
        EditText lelangFinished = (EditText) findViewById(R.id.et_lelang_end);

        imgPath = findViewById(R.id.item_img);
        imgPath.setText("Upload Image");

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
        String id_text = sharedPref.getString(getString(R.string.id), "None");

        lelangStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mdiDialog =new DatePickerDialog(AjukanPelelanganActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        lelangStart.setText(String.format("%d-%d-%d", year, monthOfYear, dayOfMonth));
                    }
                }, 2021, 7, 9);
                mdiDialog.show();
            }
        });

        lelangFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mdiDialog =new DatePickerDialog(AjukanPelelanganActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        lelangFinished.setText(String.format("%d-%d-%d", year, monthOfYear, dayOfMonth));
                    }
                }, 2021, 7, 9);
                mdiDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = name.getText().toString();
                Integer hargaAwal_int = Integer.parseInt(hargaAwal.getText().toString());
                String deskripsi_text = deskripsi.getText().toString();
                String lelangStart_date = lelangStart.getText().toString();
                String lelangFinished_date = lelangFinished.getText().toString();
                File imageFile =new File(part_image);

                if (name_text.length() == 0 ||
                        hargaAwal_int == 0 ||
                        deskripsi_text.length() == 0 ||
                        lelangStart_date.length() == 0 ||
                        lelangFinished_date.length() == 0 ||
                        !imageFile.exists()
                ) {
                    Toast.makeText(AjukanPelelanganActivity.this, getString(R.string.input_not_full), Toast.LENGTH_SHORT).show();

                    return;
                }

                String barang = String.format("{ \"nama\":\"%s\",\"hargaAwal\":%d, \"deskripsi\":\"%s\", \"lelangStart\":%s, \"lelangFinished\":%s, \"userId\":%s }",
                        name_text,
                        hargaAwal_int,
                        deskripsi_text,
                        lelangStart_date,
                        lelangFinished_date,
                        id_text
                );

                RequestBody image = RequestBody.create(MediaType.parse("image/*"), imageFile);
                RequestBody barangString = RequestBody.create(MediaType.parse("text/plain"),
                        barang);

                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", imageFile.getName(), image);
                Call<BarangResponse> call = RemoteDataSource.apiService.createBarang(barangString, fileToUpload);

                call.enqueue(new Callback<BarangResponse>() {
                    @Override
                    public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(AjukanPelelanganActivity.this, "Tambah Pelelangan Success", Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            Toast.makeText(AjukanPelelanganActivity.this, "Tambah Pelelangan Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BarangResponse> call, Throwable t) {
                        Toast.makeText(AjukanPelelanganActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


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

                if (cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    imgPath.setText(part_image);                                                        // Get the image file absolute path
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