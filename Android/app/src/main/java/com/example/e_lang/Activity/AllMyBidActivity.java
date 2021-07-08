package com.example.e_lang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_lang.Adapter.ListBarangku;
import com.example.e_lang.Adapter.ListMyBidAdapter;
import com.example.e_lang.DataSource.RemoteDataSource;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMyBidActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<PenawaranBarangResponse> dataset;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_my_bid);

        dataset = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar2);
        rvView = (RecyclerView) findViewById(R.id.rv);

        initDataset();
    }

    private void initDataset() {
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("sf", Context.MODE_PRIVATE);
        String id_text = sharedPref.getString(getString(R.string.id), "-");
        Call<List<PenawaranBarangResponse>> call = RemoteDataSource.apiService.getAllMyBid(id_text);

        call.enqueue(new Callback<List<PenawaranBarangResponse>>() {
            @Override
            public void onResponse(Call<List<PenawaranBarangResponse>> call, Response<List<PenawaranBarangResponse>> response) {
                if (response.body() != null) {
                    for (PenawaranBarangResponse barang: response.body()) {
                        dataset.add(barang);
                    }
                }

                progressBar.setVisibility(View.GONE);
                rvView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                rvView.setLayoutManager(layoutManager);

                adapter = new ListMyBidAdapter(dataset);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PenawaranBarangResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}