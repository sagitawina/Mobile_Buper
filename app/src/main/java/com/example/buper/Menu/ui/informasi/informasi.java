package com.example.buper.Menu.ui.informasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buper.Menu.Menu_InformasiLainnya;
import com.example.buper.Menu.Menu_Login;
import com.example.buper.Menu.Menu_infobatal;
import com.example.buper.Menu.Menu_infolarangan;
import com.example.buper.Menu.Menu_infomekanisme;
import com.example.buper.Menu.Menu_inforharga;
import com.example.buper.R;

public class informasi extends Fragment {
    TextView Lainya,Larangan,Batal,Harga,Mekanisme;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_informasi_more, container, false);
        Lainya=(TextView)root.findViewById(R.id.lainya);
        Larangan=(TextView)root.findViewById(R.id.Larangan);
        Batal=(TextView)root.findViewById(R.id.Batal);
        Harga =(TextView)root.findViewById(R.id.Harga);
        Mekanisme=(TextView)root.findViewById(R.id.Mekanisme);

        Lainya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Menu_InformasiLainnya.class));
                getActivity().finish();
            }
        });
        Larangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Menu_infolarangan.class));
                getActivity().finish();
            }
        });
        Batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Menu_infobatal.class));
                getActivity().finish();
            }
        });
        Harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Menu_inforharga.class));
                getActivity().finish();
            }
        });
        Mekanisme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Menu_infomekanisme.class));
                getActivity().finish();
            }
        });
        return root;
    }
}
