package com.example.buper.Menu.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buper.Adapter.Adapter_Gallery;
import com.example.buper.Item.item_gallery;
import com.example.buper.R;
import com.example.buper.Response.Respnse_gallery;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    GridLayoutManager llm;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.list_gallery);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
        return root;
    }
    private void tampilmenu() {
        ApiServices api = Network.getInstance().getApi();
        Call<Respnse_gallery> menuCall = api.tamppil_gallery();
        menuCall.enqueue(new Callback<Respnse_gallery>() {
            @Override
            public void onResponse(Call<Respnse_gallery> call, Response<Respnse_gallery> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<item_gallery> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Gallery adapter = new Adapter_Gallery(getActivity(), data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Respnse_gallery> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });



    }

}