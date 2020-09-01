package com.example.buper.Menu.ui.data_buper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buper.Menu.ui.alat_outdoor.alat_outdoor;
import com.example.buper.Menu.ui.gedung_buper.buper_gedung;
import com.example.buper.Menu.ui.tenda_buper.tenda_buper;
import com.example.buper.R;

public class data_buper extends Fragment {
    ImageView gedungbuper, tendabuper,outdoorbuper,morebuper;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_data_buper, container, false);
        gedungbuper=(ImageView)root.findViewById(R.id.buper_gedung);
        tendabuper=(ImageView)root.findViewById(R.id.tenda);
        outdoorbuper=(ImageView)root.findViewById(R.id.outdoor);
        morebuper=(ImageView)root.findViewById(R.id.more);

        gedungbuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), buper_gedung.class);
                intent.putExtra("jenis", "gedung");
                startActivity(intent);
                getActivity().finish();
            }
        });
        tendabuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), buper_gedung.class);
                intent.putExtra("jenis", "alat_kemah");
                startActivity(intent);
                getActivity().finish();
            }
        });
        outdoorbuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), buper_gedung.class);
                intent.putExtra("jenis", "alat_outdoor");
                startActivity(intent);
                getActivity().finish();
            }
        });
        morebuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), buper_gedung.class);
                intent.putExtra("jenis", "lain_lain");
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
