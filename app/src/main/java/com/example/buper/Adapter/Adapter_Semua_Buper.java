package com.example.buper.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Item.Item_gedung;
import com.example.buper.Menu.Menu_Data_Buper;
import com.example.buper.Menu.ui.Menu_Detail.Menu_Detail_Gedung;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefBuktiBayar;
import com.example.buper.Storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Semua_Buper  extends RecyclerView.Adapter<Adapter_Semua_Buper .MyViewHolder> {
    Context context;
    List<Item_gedung> menu;



    public Adapter_Semua_Buper (Context context, List<Item_gedung> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Semua_Buper .MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_semuabuper, parent, false);
        Adapter_Semua_Buper .MyViewHolder holder = new Adapter_Semua_Buper .MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_Semua_Buper .MyViewHolder holder, final int position) {
        // Set widget
        holder.nama.setText(menu.get(position).getNama_buper());
//        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+menu.get(position).getFoto();
        Log.v("url",urlGambar);
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data=menu.get(position).getDeskrispi();
//                showdialog(data);

//                Intent varIntent = new Intent(context, Menu_Detail_Gedung.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama_buper());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
//                varIntent.putExtra("NAMA_FASILITAS", menu.get(position).getNama_fasilitas());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
//                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                varIntent.putExtra("KETERANGAN", menu.get(position).getKeterangan());
//                varIntent.putExtra("LOKASI", menu.get(position).getLokasi());
//                varIntent.putExtra("JENIS", menu.get(position).getJenis());
//                varIntent.putExtra("STATUS", menu.get(position).getStatus());
//
//                context.startActivity(varIntent);
            }
        });
        holder.maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceLatitude=menu.get(position).getLat();
                String sourceLongitude=menu.get(position).getLong();
                String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + sourceLatitude + "," + sourceLongitude;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);

            }
        });
        holder.fasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager sharedPrefManager=new SharedPrefManager(context);
//                sharePrefManager.saveSPString(SharedPrefManager.SP_ID, id);
                sharedPrefManager.saveSPString(sharedPrefManager.SP_IDBUPER,menu.get(position).getId());
                sharedPrefManager.saveSPString(sharedPrefManager.SP_NAMABUPER,menu.get(position).getNama_buper());
                Intent intent=new Intent(context, Menu_Data_Buper.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView gambarmenu;
        TextView nama,harga;
        Button maps,fasilitas;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambarmenu = (ImageView) itemView.findViewById(R.id.imagegedungsemua);
            nama = (TextView) itemView.findViewById(R.id.nama_semua);
//            harga = (TextView) itemView.findViewById(R.id.hargagedung);
            maps = (Button) itemView.findViewById(R.id.maps_semua);
            fasilitas = (Button) itemView.findViewById(R.id.lihat_fasilitas);
//            maps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String sourceLatitude="-5.3789826";
//                    String sourceLongitude="105.2497678";
//                    String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + sourceLatitude + "," + sourceLongitude;
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                    intent.setPackage("com.google.android.apps.maps");
//                    context.startActivity(intent);
//                }
//            });

        }
    }
    public void showdialog( String data){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deskripsi");
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Menu_Login.super.onBackPressed();
            }
        });
        builder.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context,"Terima Kasih",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}