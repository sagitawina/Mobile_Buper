package com.example.buper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Item.item_keranjang;
import com.example.buper.Menu.Upload_Bukti_Bayar;
import com.example.buper.Menu.Upload_Mekanisme;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Notifikasi extends RecyclerView.Adapter<Adapter_Notifikasi.MyViewHolder> {
    Context context;
    List<item_keranjang> menu;



    public Adapter_Notifikasi(Context context, List<item_keranjang> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Notifikasi.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_notifikasi, parent, false);
        Adapter_Notifikasi.MyViewHolder holder = new Adapter_Notifikasi.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Notifikasi.MyViewHolder holder, final int position) {
        // Set widget
        holder.harga.setText("Rp."+menu.get(position).getHarga());
        holder.tanggal.setText("Tanggal= "+menu.get(position).getUpdated_at());
//        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+menu.get(position).getGambar();
        Log.v("url",urlGambar);
        String Status=menu.get(position).getStatus();
        if (Status.equals("entry")){
            holder.Status.setText("Berhasil Menambah Peminjaman");
        }else if (Status.equals("verifikasi")){
//            holder.bayar.setVisibility(View.VISIBLE);
//            holder.Upload.setVisibility(View.GONE);
            holder.Status.setText("Mekanisme Peminjaman Diterima");
        }else if (Status.equals("havepaid")){
//            holder.bayar.setVisibility(View.VISIBLE);
//            holder.Upload.setVisibility(View.GONE);
        holder.Status.setText("Berhasil Membayar Peminjaman");
        }else if (Status.equals("done")){
//            holder.bayar.setVisibility(View.VISIBLE);
//            holder.Upload.setVisibility(View.GONE);
        holder.Status.setText("Peminjaman Selesai");
        }else if (Status.equals("warning")){
//            holder.bayar.setVisibility(View.VISIBLE);
//            holder.Upload.setVisibility(View.GONE);
        holder.Status.setText("Telat Kembali");
    }


        else{

//            holder.Status.setText("Peminjaman Sukses");
        }

//        Picasso.with(context).load(urlGambar).into(holder.gambarbuper);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data=menu.get(position).getDeskrispi();
//                showdialog(data);
////
//        Intent varIntent = new Intent(context, Detail_Menu.class);
//        varIntent.putExtra("ID", menu.get(position).getId());
//        varIntent.putExtra("NAMA", menu.get(position).getNama());
//        varIntent.putExtra("HARGA", menu.get(position).getHarga());
//        varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//        varIntent.putExtra("GAMBAR_MENU", urlGambar);
//        varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//        context.startActivity(varIntent);
            }
        });
//        holder.bayar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Upload_Bukti_Bayar.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama_buper());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
////                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
////                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
//            }
//        });
//        holder.Upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Upload_Mekanisme.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama_buper());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
////                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
////                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

//        ImageView gambarbuper;
        TextView harga,tanggal,Status;
//        Button bayar,Upload;

        public MyViewHolder(View itemView) {
            super(itemView);
//            gambarbuper = (ImageView) itemView.findViewById(R.id.CheckoutItemImg);
            harga = (TextView) itemView.findViewById(R.id.total_bayar);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            Status = (TextView) itemView.findViewById(R.id.status);
//            bayar = (Button) itemView.findViewById(R.id.Bayar);
//            Upload=(Button)itemView.findViewById(R.id.Uploadmekanisme);

        }
    }
}
