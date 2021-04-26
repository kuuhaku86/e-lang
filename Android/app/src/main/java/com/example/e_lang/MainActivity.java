package com.example.e_lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        switch (idView) {
            case R.id.button2:
                Intent intent = new Intent(MainActivity.this, BidActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}