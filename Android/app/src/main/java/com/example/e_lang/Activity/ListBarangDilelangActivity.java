package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.e_lang.Adapter.ListBarangDilelangAdapter;
import com.example.e_lang.Entity.Barang;
import com.example.e_lang.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ListBarangDilelangActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Barang> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang_dilelang);

        dataset = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) findViewById(R.id.rvListBarangDilelang);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        adapter = new ListBarangDilelangAdapter(dataset);
        rvView.setAdapter(adapter);
    }

    private void initDataset() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataset.add(new Barang(
               "Buku",
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ));
        }
    }
}