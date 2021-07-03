package com.example.e_lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.Activity.BidActivity;
import com.example.e_lang.Activity.ListBarangDilelangActivity;
import com.example.e_lang.Activity.PaymentActivity;

public class MainActivity extends AppCompatActivity {
    ImageView contact;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact = findViewById(R.id.contact);
        name = findViewById(R.id.name);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
        String name_text = sharedPref.getString(getString(R.string.nama), "None");
        String photo_url = sharedPref.getString(getString(R.string.photo_profile), "None");

        name.setText(name_text);
        Glide.with(this)
                .load(photo_url)
                .apply(new RequestOptions().override(50, 50))
                .apply(RequestOptions.circleCropTransform())
                .into(contact);

        int[] arrayId = {
                R.id.button2,
                R.id.button3,
                R.id.button4,
        };

        for (int id: arrayId) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.onClick(v.getId());
                }
            });
        }
    }

    public void onClick(int idView) {
        Intent intent;

        switch (idView) {
            case R.id.button2:
                intent = new Intent(MainActivity.this, BidActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this, ListBarangDilelangActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(MainActivity.this, PaymentActivity.class);
                startActivity(intent);
                break;
        }
    }
}