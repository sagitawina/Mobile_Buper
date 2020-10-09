package com.example.buper.Menu.ui.data_buper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Adapter.Adapter_Semua_Buper;
import com.example.buper.Adapter.Adapter_gedung;
import com.example.buper.Item.Item_gedung;
import com.example.buper.Menu.Menu_Utama;
import com.example.buper.Menu.ui.alat_outdoor.alat_outdoor;
import com.example.buper.Menu.ui.gedung_buper.buper_gedung;
import com.example.buper.Menu.ui.tenda_buper.tenda_buper;
import com.example.buper.R;
import com.example.buper.Response.Response_Gedun;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class data_buper extends Fragment {
    GridLayoutManager llm;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_data_buper, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.list_semuabuper);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
        return root;
    }

    private void tampilmenu() {
        ApiServices api = Network.getInstance().getApi();
        Call<Response_Gedun> menuCall = api.tamppil_semuabuper();
        menuCall.enqueue(new Callback<Response_Gedun>() {
            @Override
            public void onResponse(Call<Response_Gedun> call, Response<Response_Gedun> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_gedung> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Semua_Buper adapter = new Adapter_Semua_Buper(getContext(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
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
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        goBackMenu();
//    }
//
//    public void goBackMenu(){
//        startActivity(new Intent(buper_gedung.this, Menu_Utama.class));
//        finish();
//    }
}
