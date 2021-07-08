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
import com.example.e_lang.Activity.DetailBarangkuActivity;
import com.example.e_lang.DataSource.Response.BarangResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListMyBidAdapter extends RecyclerView.Adapter<ListMyBidAdapter.ViewHolder> {

    private ArrayList<PenawaranBarangResponse> rvData;
    private Context context;
    private View view;

    public ListMyBidAdapter(ArrayList<PenawaranBarangResponse> listPenawaranBarang) {
        rvData = listPenawaranBarang;
    }

    @NonNull
    @Override
    public ListMyBidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang_dilelang_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMyBidAdapter.ViewHolder holder, int position) {
        final PenawaranBarangResponse penawaranBarang = rvData.get(position);

        final String tanggal = convertDateFormat(penawaranBarang.getBarang().getLelangStart()) + " - " + convertDateFormat(penawaranBarang.getBarang().getLelangFinished());

        holder.listBarangName.setText(penawaranBarang.getBarang().getNama());
        holder.listBarangTanggal.setText(tanggal);
        Glide.with(context)
                .load(penawaranBarang.getBarang().getImageUrl())
                .apply(new RequestOptions().override(50, 50))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.picture);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailBarangkuActivity.class);
                intent.putExtra("penawaranBarang", penawaranBarang.getBarang());
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
