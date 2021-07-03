package com.example.e_lang.Fragment;

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

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Request.RegisterRequest;
import com.example.e_lang.DataSource.Response.UserResponse;
import com.example.e_lang.MainActivity;
import com.example.e_lang.R;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    TextView imgPath;
    private static final int PICK_IMAGE_REQUEST = 9544;
    Uri selectedImage;
    String part_image;

    public RegisterFragment() {
        // Required empty public constructor
    }

    // Permissions for accessing the storage
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button button = (Button) view.findViewById(R.id.btn_register);
        EditText email = (EditText) view.findViewById(R.id.et_email);
        EditText password = (EditText) view.findViewById(R.id.et_password);
        EditText nama = (EditText) view.findViewById(R.id.et_name);
        EditText nomor_telpon = (EditText) view.findViewById(R.id.et_nomor_telpon);
        EditText alamat = (EditText) view.findViewById(R.id.et_alamat);
        EditText repassword = (EditText) view.findViewById(R.id.et_repassword);

        imgPath = view.findViewById(R.id.item_img);
        imgPath.setText("Upload Image");

        imgPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick(v);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                String nama_text = nama.getText().toString();
                String nomor_telpon_text = nomor_telpon.getText().toString();
                String alamat_text = alamat.getText().toString();
                String repassword_text = repassword.getText().toString();
                File imageFile =new File(part_image);

                if (email_text.length() == 0 ||
                        password_text.length() == 0 ||
                        nama_text.length() == 0 ||
                        nomor_telpon_text.length() == 0 ||
                        alamat_text.length() == 0 ||
                        repassword_text.length() == 0 ||
                        !imageFile.exists()
                ) {
                    Toast.makeText(getActivity(), getString(R.string.input_not_full), Toast.LENGTH_SHORT).show();

                    return;
                }

                if (!password_text.equals(repassword_text)) {
                    Toast.makeText(getActivity(), getString(R.string.password_not_same), Toast.LENGTH_SHORT).show();

                    return;
                }

                String user = String.format("{ \"nama\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"nomorTelpon\": \"%s\" , \"alamat\": \"%s\" }",
                        nama_text,
                        password_text,
                        email_text,
                        nomor_telpon_text,
                        alamat_text
                );
                RequestBody image = RequestBody.create(MediaType.parse("image/*"), imageFile);
                RequestBody userString = RequestBody.create(MediaType.parse("text/plain"),
                        user);

                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", imageFile.getName(), image);
                Call<UserResponse> call = RemoteDataSource.apiService.register(userString, fileToUpload);

                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                            sharedPref.edit().putString(getString(R.string.nama), response.body().getNama());
                            sharedPref.edit().putString(getString(R.string.nomorTelpon), response.body().getNomorTelpon());
                            sharedPref.edit().putString(getString(R.string.password), response.body().getPassword());
                            sharedPref.edit().putString(getString(R.string.photo_profile), response.body().getImageUrl());
                            sharedPref.edit().putString(getString(R.string.id), response.body().getId());
                            sharedPref.edit().putString(getString(R.string.email), response.body().getEmail());
                            sharedPref.edit().putString(getString(R.string.alamat), response.body().getAlamat());
                            sharedPref.edit().commit();

                            Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            ((Activity) getActivity()).overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(getActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    // Method to get the absolute path of the selected image from its URI
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedImage = data.getData();                                                         // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(
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
                    imgPath.setText(part_image);                                                        // Get the image file absolute path
                    Bitmap bitmap = null;
                    cursor.close();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Method for starting the activity for selecting image from phone storage
    public void pick(View view) {
        verifyStoragePermissions(getActivity());
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