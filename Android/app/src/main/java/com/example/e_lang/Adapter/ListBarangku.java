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
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.Entity.Barang;
import com.example.e_lang.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ListBarangku extends RecyclerView.Adapter<ListBarangku.ViewHolder> {

    private ArrayList<BarangResponse> rvData;
    private Context context;
    private View view;

    public ListBarangku(ArrayList<BarangResponse> listBarang) {
        rvData = listBarang;
    }

    @NonNull
    @Override
    public ListBarangku.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang_dilelang_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBarangku.ViewHolder holder, int position) {
        final BarangResponse barang = rvData.get(position);

        final String tanggal = convertDateFormat(barang.getLelangStart()) + " - " + convertDateFormat(barang.getLelangFinished());

        holder.listBarangName.setText(barang.getNama());
        holder.listBarangTanggal.setText(tanggal);
        Glide.with(context)
                .load(barang.getImageUrl())
                .apply(new RequestOptions().override(50, 50))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.picture);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BidActivity.class);
                context.startActivity(intent);
            }
        });
    }

    private String convertDateFormat(String timestamp) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(timestamp.substring(0, 9));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        return dt1.format(date);
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
