package com.example.buper.Menu.ui.notifikasi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Adapter.Adapter_Notifikasi;
import com.example.buper.Adapter.Adapter_keranjang;
import com.example.buper.Item.item_keranjang;
import com.example.buper.R;
import com.example.buper.Response.Respon_keranjang;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class notifikasi extends Fragment {
    GridLayoutManager llm;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.notifikasi);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
        return root;
    }
    private void tampilmenu() {
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getActivity());
//        String status="entry";
        String email=sharedPrefManager.getSPEmail();
        ApiServices api = Network.getInstance().getApi();
        Call<Respon_keranjang> menuCall = api.tampil_notif(email);
        menuCall.enqueue(new Callback<Respon_keranjang>() {
            @Override
            public void onResponse(Call<Respon_keranjang> call, Response<Respon_keranjang> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<item_keranjang> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Notifikasi adapter = new Adapter_Notifikasi(getActivity(), data_menu);
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
