package com.example.e_lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_lang.Activity.BidActivity;
import com.example.e_lang.Activity.ListBarangDilelangActivity;
import com.example.e_lang.Activity.PaymentActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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