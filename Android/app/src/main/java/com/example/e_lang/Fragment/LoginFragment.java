package com.example.e_lang.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.UserResponse;
import com.example.e_lang.MainActivity;
import com.example.e_lang.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button button = (Button) view.findViewById(R.id.btn_login);
        EditText email = (EditText) view.findViewById(R.id.et_email);
        EditText password = (EditText) view.findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();

                if (email_text.length() == 0 || password_text.length() == 0) {
                    Toast.makeText(getActivity(), R.string.input_not_full, Toast.LENGTH_SHORT).show();

                    return;
                }

                Call<UserResponse> call = RemoteDataSource.apiService.login(email_text, password_text);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            SharedPreferences sharedPref = getActivity().getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(getString(R.string.nama), response.body().getNama());
                            editor.commit();
                            editor.putString(getString(R.string.nomorTelpon), response.body().getNomorTelpon());
                            editor.commit();
                            editor.putString(getString(R.string.password), response.body().getPassword());
                            editor.commit();
                            editor.putString(getString(R.string.photo_profile), response.body().getImageUrl());
                            editor.commit();
                            editor.putString(getString(R.string.id), response.body().getId());
                            editor.commit();
                            editor.putString(getString(R.string.email), response.body().getEmail());
                            editor.commit();
                            editor.putString(getString(R.string.alamat), response.body().getAlamat());
                            editor.commit();

                            Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            ((Activity) getActivity()).overridePendingTransition(0, 0);
                        } else {
                            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
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
}