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
import com.example.buper.Item.item_gallery;
import com.example.buper.Menu.ui.Menu_Detail.Menu_Detail_Gedung;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_gedung extends RecyclerView.Adapter<Adapter_gedung.MyViewHolder> {
    Context context;
    List<Item_gedung> menu;



    public Adapter_gedung(Context context, List<Item_gedung> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_gedung.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_buper_gedung, parent, false);
        Adapter_gedung.MyViewHolder holder = new Adapter_gedung.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Adapter_gedung.MyViewHolder holder, final int position) {
        // Set widget
        holder.nama.setText(menu.get(position).getNama_buper());
        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+menu.get(position).getFoto();
        Log.v("url",urlGambar);
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data=menu.get(position).getDeskrispi();
//                showdialog(data);

        Intent varIntent = new Intent(context, Menu_Detail_Gedung.class);
        varIntent.putExtra("ID", menu.get(position).getId());
        varIntent.putExtra("NAMA", menu.get(position).getNama_buper());
        varIntent.putExtra("HARGA", menu.get(position).getHarga());
        varIntent.putExtra("NAMA_FASILITAS", menu.get(position).getNama_fasilitas());
        varIntent.putExtra("GAMBAR_MENU", urlGambar);
        varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
        varIntent.putExtra("KETERANGAN", menu.get(position).getKeterangan());
        varIntent.putExtra("LOKASI", menu.get(position).getLokasi());
        varIntent.putExtra("JENIS", menu.get(position).getJenis());
        varIntent.putExtra("STATUS", menu.get(position).getStatus());

        context.startActivity(varIntent);
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
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView gambarmenu;
        TextView nama,harga;
        Button maps;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambarmenu = (ImageView) itemView.findViewById(R.id.imagegedung);
            nama = (TextView) itemView.findViewById(R.id.namagedung);
            harga = (TextView) itemView.findViewById(R.id.hargagedung);
            maps = (Button) itemView.findViewById(R.id.maps);
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