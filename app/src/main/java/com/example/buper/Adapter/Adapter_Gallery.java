package com.example.buper.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Item.item_gallery;
import com.example.buper.Menu.Menu_Login;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Gallery extends RecyclerView.Adapter<Adapter_Gallery.MyViewHolder> {
        Context context;
        List<item_gallery> menu;



public Adapter_Gallery(Context context, List<item_gallery> data_menu) {
        this.context = context;
        this.menu= data_menu;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_gallery, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
        }

@Override
public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.keterangan.setText(menu.get(position).getTittle());
//        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+ menu.get(position).getFoto();
    Log.v("url",urlGambar);
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=menu.get(position).getDeskrispi();
                showdialog(data);
//
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
            }

            @Override
            public int getItemCount() {
                return menu.size();
            }

            public class MyViewHolder extends RecyclerView.ViewHolder {

                ImageView gambarmenu;
                TextView keterangan;

                public MyViewHolder(View itemView) {
                    super(itemView);
                    gambarmenu = (ImageView) itemView.findViewById(R.id.thumb);
                    keterangan = (TextView) itemView.findViewById(R.id.title);

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
//                Toast.makeText(context,"Terima Kasih",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
            }
        }
