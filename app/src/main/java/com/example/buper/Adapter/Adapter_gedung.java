package com.example.buper.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Item.Item_gedung;
import com.example.buper.Item.item_gallery;
import com.example.buper.Menu.Menu_Data_Buper;
import com.example.buper.Menu.Menu_Login;
import com.example.buper.Menu.ui.Menu_Detail.Menu_Detail_Gedung;
import com.example.buper.Menu.ui.keranjang.keranjang;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_gedung extends RecyclerView.Adapter<Adapter_gedung.MyViewHolder> {
    Context context;
    List<Item_gedung> menu;
    ProgressDialog loading;



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
        holder.harga.setText("Rp."+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+menu.get(position).getFoto();
        Log.v("url",urlGambar);
        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data=menu.get(position).getDeskrispi();
//                showdialog(data);

//        Intent varIntent = new Intent(context, Menu_Detail_Gedung.class);
//        varIntent.putExtra("ID", menu.get(position).getId());
//        varIntent.putExtra("NAMA", menu.get(position).getNama_buper());
//        varIntent.putExtra("HARGA", menu.get(position).getHarga());
//        varIntent.putExtra("NAMA_FASILITAS", menu.get(position).getNama_fasilitas());
//        varIntent.putExtra("GAMBAR_MENU", urlGambar);
//        varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//        varIntent.putExtra("KETERANGAN", menu.get(position).getKeterangan());
//        varIntent.putExtra("LOKASI", menu.get(position).getLokasi());
//        varIntent.putExtra("JENIS", menu.get(position).getJenis());
//        varIntent.putExtra("STATUS", menu.get(position).getStatus());
//
//        context.startActivity(varIntent);
            }
        });
//        holder.maps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String sourceLatitude=menu.get(position).getLat();
//                String sourceLongitude=menu.get(position).getLong();
//                String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + sourceLatitude + "," + sourceLongitude;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                intent.setPackage("com.google.android.apps.maps");
//                context.startActivity(intent);
//
//            }
//        });
        holder.Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama_Buper=menu.get(position).getNama_buper();
                String Harga_Buper=menu.get(position).getHarga();
                String Alamat_Buper=menu.get(position).getLokasi();
                String keterangan=menu.get(position).getKeterangan();
                String Jenis=menu.get(position).getJenis();
                showdialog(Nama_Buper,Harga_Buper,Alamat_Buper,keterangan,Jenis);
            }
        });
        holder.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(context,"Loading.....",null,true,true);

                String jenis=menu.get(position).getJenis();
                final String nama= menu.get(position).getNama_buper();
                String Harga= menu.get(position).getHarga();
                String lokasi= menu.get(position).getLokasi();
                String gambar_menu= menu.get(position).getFoto();
                SharedPrefManager sharedPrefManager=new SharedPrefManager(context);
                final String email=sharedPrefManager.getSPEmail();
                final String id= sharedPrefManager.getSpIdbuper();
                Call<ResponseBody> call = Network.getInstance().getApi().addpinjam(id,jenis,email,nama,lokasi,Harga,gambar_menu);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("success").equals("true")) {
                                    String pesan = jsonRESULTS.getString("message");
                                    Toast.makeText(context, "" + pesan, Toast.LENGTH_SHORT).show();
                                    Log.d("response api", jsonRESULTS.toString());
                                    Log.v("ini", pesan);
                                    loading.dismiss();
                                    add_temporary(email,id,nama);
                                    showpesan(pesan);
                                } else {
                                    String pesan = jsonRESULTS.getString("message");
                                    Log.v("ini", pesan);
                                    Toast.makeText(context, "" + pesan, Toast.LENGTH_SHORT).show();
                                    loading.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            loading.dismiss();
                            Toast.makeText(context, "Server Tidak Merespon" , Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void add_temporary(String email, String id, String nama) {
        Call<ResponseBody> call = Network.getInstance().getApi().addsementara(id,nama,email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")) {
                            String pesan = jsonRESULTS.getString("message");
//                            Toast.makeText(context, "" + pesan, Toast.LENGTH_SHORT).show();
//                            Log.d("response api", jsonRESULTS.toString());
//                            Log.v("ini", pesan);
//                            loading.dismiss();
                        } else {
//                            String pesan = jsonRESULTS.getString("message");
//                            Log.v("ini", pesan);
//                            Toast.makeText(context, "" + pesan, Toast.LENGTH_SHORT).show();
//                            loading.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
//                    loading.dismiss();
//                    Toast.makeText(context, "Server Tidak Merespon" , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
        Button Add,Detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambarmenu = (ImageView) itemView.findViewById(R.id.imagegedung);
            nama = (TextView) itemView.findViewById(R.id.namagedung);
            harga = (TextView) itemView.findViewById(R.id.hargagedung);
            Add= (Button) itemView.findViewById(R.id.add_peminjaman);
            Detail= (Button) itemView.findViewById(R.id.detail_fasilitas);
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
    public void showdialog( String nama,String harga,String Alamat,String keterangan,String jenis){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nama Buper:"+nama);
        builder.setMessage("Harga:"+"Rp."+harga+",  "+"Alamat:"+Alamat+",  "+"Keterangan:"+keterangan+",  "+"Jenis Fasilitas:"+jenis);
        builder.setCancelable(true);
        builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
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
    public void showpesan(String data){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pesan");
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent=new Intent(context, Menu_Data_Buper.class);
//                context.startActivities(intent);
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