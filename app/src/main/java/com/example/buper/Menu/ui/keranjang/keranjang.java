package com.example.buper.Menu.ui.keranjang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Adapter.Adapter_Gallery;
import com.example.buper.Adapter.Adapter_gedung;
import com.example.buper.Adapter.Adapter_keranjang;
import com.example.buper.Item.Item_gedung;
import com.example.buper.Item.item_gallery;
import com.example.buper.Item.item_keranjang;
import com.example.buper.Menu.Menu_Login;
import com.example.buper.Menu.Splash_Screen;
import com.example.buper.R;
import com.example.buper.Response.Respnse_gallery;
import com.example.buper.Response.Respon_keranjang;
import com.example.buper.Response.Response_Gedun;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Myservice;
import com.example.buper.Server.Network;
import com.example.buper.Server.Server;
import com.example.buper.Storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class keranjang extends Fragment {
    GridLayoutManager llm;
    private RecyclerView recyclerView,Rekomendasi;
    private int waktu_loading=2000;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        String strtext = getArguments().getString("edttext");
        View root = inflater.inflate(R.layout.fragment_keranjang, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.list_keranjang);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(llm);
        tampilmenu();

        Rekomendasi = (RecyclerView) root.findViewById(R.id.list_rekomendasi);
        Rekomendasi.setHasFixedSize(true);
        GridLayoutManager rekom=new GridLayoutManager(getActivity(),1);
        Rekomendasi.setLayoutManager(rekom);
        tampilrekomdasi();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(getContext(),getActivity());
//
//            }
//        },waktu_loading);
        return root;
    }

    private void tampilrekomdasi() {
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getActivity());
        String status="entry";
        String email=sharedPrefManager.getSPEmail();
        Myservice api = Server.getInstance().getApi();
        Call<Response_Gedun> menuCall = api.Tampil_Rekomendasi(email);
        menuCall.enqueue(new Callback<Response_Gedun>() {
            @Override
            public void onResponse(Call<Response_Gedun> call, Response<Response_Gedun> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_gedung> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_gedung adapter = new Adapter_gedung(getActivity(), data_menu);
                        Rekomendasi.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada Rekomendasi Buper saat ini", Toast.LENGTH_SHORT).show();
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

    private void tampilmenu() {
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getActivity());
        String status="entry";
        String email=sharedPrefManager.getSPEmail();
        ApiServices api = Network.getInstance().getApi();
        Call<Respon_keranjang> menuCall = api.view_keranjang(email,status);
        menuCall.enqueue(new Callback<Respon_keranjang>() {
            @Override
            public void onResponse(Call<Respon_keranjang> call, Response<Respon_keranjang> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<item_keranjang> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_keranjang adapter = new Adapter_keranjang(getActivity(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Respon_keranjang> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });



    }

}