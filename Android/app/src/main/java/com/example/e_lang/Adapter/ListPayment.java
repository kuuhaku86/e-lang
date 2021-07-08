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
import com.example.e_lang.Activity.MyBidActivity;
import com.example.e_lang.DataSource.Response.PembayaranResponse;
import com.example.e_lang.DataSource.Response.PenawaranBarangResponse;
import com.example.e_lang.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListPayment extends RecyclerView.Adapter<ListPayment.ViewHolder> {

    private ArrayList<PembayaranResponse> rvData;
    private Context context;
    private View view;

    public ListPayment(ArrayList<PembayaranResponse> listPenawaranBarang) {
        rvData = listPenawaranBarang;
    }

    @NonNull
    @Override
    public ListPayment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang_dilelang_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPayment.ViewHolder holder, int position) {
        final PembayaranResponse pembayaran = rvData.get(position);

        final String tanggal = convertDateFormat(pembayaran.getBarang().getLelangStart()) + " - " + convertDateFormat(pembayaran.getBarang().getLelangFinished());

        holder.listBarangName.setText(pembayaran.getBarang().getNama());
        holder.listBarangTanggal.setText(tanggal);
        Glide.with(context)
                .load(pembayaran.getBarang().getImageUrl())
                .apply(new RequestOptions().override(50, 50))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.picture);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MyBidActivity.class);
                intent.putExtra("pembayaran", pembayaran);
                context.startActivity(intent);
            }
        });
    }

    private String convertDateFormat(String timestamp) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(timestamp.substring(0, 10));
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
