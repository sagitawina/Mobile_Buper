package com.example.buper.Menu.ui.log_out;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buper.Menu.Menu_Login;
import com.example.buper.R;
import com.example.buper.Storage.SharedPrefManager;

public class log_out extends Fragment {
    SharedPrefManager sharedPrefManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_log_out, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(getActivity(), Menu_Login.class));
        getActivity().finish();
        return root;
    }
}
