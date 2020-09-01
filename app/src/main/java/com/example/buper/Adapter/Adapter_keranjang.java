package com.example.buper.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Item.item_gallery;
import com.example.buper.Item.item_keranjang;
import com.example.buper.Menu.Menu_Login;
import com.example.buper.Menu.Menu_Register;
import com.example.buper.Menu.Menu_Utama;
import com.example.buper.Menu.Upload_Bukti_Bayar;
import com.example.buper.Menu.Upload_Mekanisme;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_keranjang extends RecyclerView.Adapter<Adapter_keranjang.MyViewHolder> {
    Context context;
    List<item_keranjang> menu;
    ProgressDialog loading ;
    public Adapter_keranjang(Context context, List<item_keranjang> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }
    @Override
    public Adapter_keranjang.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_keranjang, parent, false);
        Adapter_keranjang.MyViewHolder holder = new Adapter_keranjang.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Adapter_keranjang.MyViewHolder holder, final int position) {
        // Set widget
        holder.harga.setText(menu.get(position).getHarga());
        holder.nama.setText(menu.get(position).getNama_buper());
//        holder.harga.setText("Rp"+menu.get(position).getHarga());
        final String urlGambar = Network.Url_gambar+menu.get(position).getGambar();
        Log.v("url",urlGambar);
        final String Status=menu.get(position).getStatus();
//        if (Status.equals("entry")){
//            holder.bayar.setVisibility(View.GONE);
//            holder.Upload.setVisibility(View.VISIBLE);
//            holder.Status.setText("Silahkan Upload Mekanisme Peminjaman");
//        }else if (Status.equals("verifikasi")){
//            holder.bayar.setVisibility(View.VISIBLE);
//            holder.Upload.setVisibility(View.GONE);
//            holder.Status.setText("Mekanisme Peminjaman Di Terima, Silahkan Bayar");
//        }else{
//            holder.bayar.setVisibility(View.GONE);
//            holder.Upload.setVisibility(View.GONE);
//            holder.Status.setText("Peminjaman Sukses");
//        }
//
//        holder.hitung.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int Total=0;
                if (holder.checkBox.isChecked()){
                    String jumlahtotal=menu.get(position).getHarga();
                    int Total=Integer.parseInt(jumlahtotal);
                    Total++;
                    Toast.makeText(context, ""+Total++, Toast.LENGTH_SHORT).show();
//                    holder.total.setText(Total);

                }
//            }
//        });
        Picasso.with(context).load(urlGambar).into(holder.gambarbuper);
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
//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String totalbayar=menu.get(position).getHarga();
//            }
//        });
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(context,"Loading.....",null,true,true);
                String idkeranjang=menu.get(position).getId();
//                Delete(idkeranjang);
                showdialog(idkeranjang);
            }
        });
    }

    private void Delete(String idkeranjang) {
//        Toast.makeText(context, ""+idkeranjang, Toast.LENGTH_SHORT).show();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().delete_peminjaman(idkeranjang);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan=jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            loading.dismiss();
                            Toast.makeText(context, ""+pesan, Toast.LENGTH_SHORT).show();
//                            Log.v("ini",pesan_regsiter);
//                            RegisterBerhasil(pesan_regsiter);
                            Intent intent=new Intent(context, Menu_Utama.class);
//                            intent.putExtra("NIK",Username.getText().toString());
                            context.startActivity(intent);
//                            context.finish();
                        } else{
                            loading.dismiss();
                           String pesan=jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            Toast.makeText(context, ""+pesan, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(context, "Gangguan Pada Server", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
//                    RegisterGagal(pesan_regsiter);
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

        ImageView gambarbuper;
        TextView harga,nama,Status,total;
//        Button bayar,Upload,hitung;
        CheckBox checkBox;
        ImageButton hapus;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambarbuper = (ImageView) itemView.findViewById(R.id.CheckoutItemImg);
            harga = (TextView) itemView.findViewById(R.id.harga_keranjang);
            nama = (TextView) itemView.findViewById(R.id.nama_keranjang);
            Status = (TextView) itemView.findViewById(R.id.status);
            total = (TextView) itemView.findViewById(R.id.total_bayar);
//            bayar = (Button) itemView.findViewById(R.id.Bayar);
//            Upload=(Button)itemView.findViewById(R.id.Uploadmekanisme);
//            hitung = (Button) itemView.findViewById(R.id.Hitung_total);
            checkBox = (CheckBox) itemView.findViewById(R.id.pilihkeranjang);
            hapus = (ImageButton) itemView.findViewById(R.id.delChkoutItemBut);

        }
    }

    public void showdialog(final String data){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Apakah Anda Yakin Akan Menghapus Keranjang ini?");
//        builder.setMessage(data);
        builder.setCancelable(true);
        builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Menu_Login.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Delete(data);
//                Toast.makeText(context,"Terima Kasih",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
