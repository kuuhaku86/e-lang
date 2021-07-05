package com.example.e_lang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_lang.Activity.BidActivity;
import com.example.e_lang.Entity.Barang;
import com.example.e_lang.MainActivity;
import com.example.e_lang.R;

import java.util.ArrayList;

public class ListBarangDilelangAdapter extends RecyclerView.Adapter<ListBarangDilelangAdapter.ViewHolder> {

    private ArrayList<Barang> rvData;

    public ListBarangDilelangAdapter(ArrayList<Barang> listBarang) {
        rvData = listBarang;
    }

    @NonNull
    @Override
    public ListBarangDilelangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang_dilelang_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BidActivity.class);
                context.startActivity(intent);
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBarangDilelangAdapter.ViewHolder holder, int position) {
        final Barang barang = rvData.get(position);
        final String tanggal = barang.tanggalMulai.toString() + " - " + barang.tanggalSelesai.toString();

        holder.listBarangName.setText(barang.nama);
        holder.listBarangTanggal.setText(tanggal);
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView listBarangName, listBarangTanggal;
        public ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listBarangName = itemView.findViewById(R.id.listBarangDilelangNama);
            listBarangTanggal = itemView.findViewById(R.id.listBarangDilelangTanggal);
            picture = itemView.findViewById(R.id.picture);
        }
    }
}
