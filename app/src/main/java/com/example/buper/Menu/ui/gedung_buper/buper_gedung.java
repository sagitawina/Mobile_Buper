package com.example.buper.Menu.ui.gedung_buper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buper.Adapter.Adapter_Gallery;
import com.example.buper.Adapter.Adapter_gedung;
import com.example.buper.Item.Item_gedung;
import com.example.buper.Item.item_gallery;
import com.example.buper.Menu.Menu_Data_Buper;
import com.example.buper.Menu.Menu_Utama;
import com.example.buper.Menu.ui.informasi.informasi;
import com.example.buper.R;
import com.example.buper.Response.Respnse_gallery;
import com.example.buper.Response.Response_Gedun;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class buper_gedung extends AppCompatActivity {
    GridLayoutManager llm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buper_gedung);
        recyclerView = (RecyclerView)findViewById(R.id.list_buper);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(buper_gedung.this,1);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
    }

    private void tampilmenu() {
        String jenis=getIntent().getStringExtra("jenis");
        SharedPrefManager sharedPrefManager=new SharedPrefManager(buper_gedung.this);
        ApiServices api = Network.getInstance().getApi();
        Call<Response_Gedun> menuCall = api.tampil_buper(jenis,sharedPrefManager.getSpNamabuper());
        menuCall.enqueue(new Callback<Response_Gedun>() {
            @Override
            public void onResponse(Call<Response_Gedun> call, Response<Response_Gedun> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_gedung> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_gedung adapter = new Adapter_gedung(buper_gedung.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(buper_gedung.this, "Mohon Maaf Buper ini Tidak Memiliki Fasilitas", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Gedun> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(buper_gedung.this, Menu_Data_Buper.class));
        finish();
    }
}