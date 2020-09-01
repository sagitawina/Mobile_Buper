package com.example.buper.Menu.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buper.Menu.Menu_edit_profile;
import com.example.buper.R;
import com.example.buper.Storage.SharedPrefManager;

public class profile extends Fragment {
    TextView namapengguna,usernamepengguna,passwordpengguna,emailpengguna,alamatpengguna,nohp_pengguna;
    Button edit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        namapengguna=(TextView) root.findViewById(R.id.nama_pengguna);
        usernamepengguna=(TextView) root.findViewById(R.id.username_pengguna);
//        passwordpengguna=(TextView) root.findViewById(R.id.password_pengguna);
        emailpengguna=(TextView) root.findViewById(R.id.email_pengguna);
        alamatpengguna=(TextView) root.findViewById(R.id.alamat_pengguna);
        nohp_pengguna=(TextView) root.findViewById(R.id.nohp_pengguna);
        edit=(Button) root.findViewById(R.id.btnedit);

        SharedPrefManager sharedPrefManager=new SharedPrefManager(getActivity());
        namapengguna.setText("Nama Lengkap      : "+sharedPrefManager.getSPNama());
        usernamepengguna.setText("Username               : "+sharedPrefManager.getSpUsername());
        emailpengguna.setText("Email                      : "+sharedPrefManager.getSPEmail());
        alamatpengguna.setText("Alamat                   : "+sharedPrefManager.getSPAlamat());
        nohp_pengguna.setText("No HP                     : "+sharedPrefManager.getSPTelpon());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Menu_edit_profile.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
