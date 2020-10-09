package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buper.Menu.ui.gedung_buper.buper_gedung;
import com.example.buper.R;
import com.example.buper.Storage.SharedPrefManager;

public class Menu_Data_Buper extends AppCompatActivity {
    ImageView gedungbuper, tendabuper,outdoorbuper,morebuper;
    TextView Nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__data__buper);
        gedungbuper=(ImageView)findViewById(R.id.buper_gedung);
        tendabuper=(ImageView)findViewById(R.id.tenda);
        outdoorbuper=(ImageView)findViewById(R.id.outdoor);
        morebuper=(ImageView)findViewById(R.id.more);
        Nama=(TextView)findViewById(R.id.nama_dataBuper);
        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Data_Buper.this);
        Nama.setText(sharedPrefManager.getSpNamabuper());

        gedungbuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Data_Buper.this, buper_gedung.class);
                intent.putExtra("jenis", "gedung");
                startActivity(intent);
                finish();
            }
        });
        tendabuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Data_Buper.this, buper_gedung.class);
                intent.putExtra("jenis", "alat_kemah");
                startActivity(intent);
                finish();
            }
        });
        outdoorbuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Data_Buper.this, buper_gedung.class);
                intent.putExtra("jenis", "alat_outdoor");
                startActivity(intent);
                finish();
            }
        });
        morebuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Data_Buper.this, buper_gedung.class);
                intent.putExtra("jenis", "lain_lain");
                startActivity(intent);
                finish();
            }
        });
    }
}