package com.example.buper.Menu.ui.alat_outdoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buper.Adapter.Adapter_gedung;
import com.example.buper.Item.Item_gedung;
import com.example.buper.Menu.Menu_Utama;
import com.example.buper.Menu.ui.gedung_buper.buper_gedung;
import com.example.buper.R;
import com.example.buper.Response.Response_Gedun;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class alat_outdoor extends AppCompatActivity {
    GridLayoutManager llm;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alat_outdoor);
        recyclerView = (RecyclerView)findViewById(R.id.list_alat_outdoor);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(alat_outdoor.this,2);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
    }

    private void tampilmenu() {
        String jenis=getIntent().getStringExtra("jenis");
        ApiServices api = Network.getInstance().getApi();
        Call<Response_Gedun> menuCall = api.tampil_outdoor(jenis);
        menuCall.enqueue(new Callback<Response_Gedun>() {
            @Override
            public void onResponse(Call<Response_Gedun> call, Response<Response_Gedun> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_gedung> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_gedung adapter = new Adapter_gedung(alat_outdoor.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(alat_outdoor.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(alat_outdoor.this, Menu_Utama.class));
        finish();

    }
}