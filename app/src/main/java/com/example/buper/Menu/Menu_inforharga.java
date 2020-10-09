package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buper.Menu.ui.informasi.informasi;
import com.example.buper.R;

public class Menu_inforharga extends AppCompatActivity {
    TextView info1,info2,info3,info4,info5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inforharga);
        info1=(TextView)findViewById(R.id.infoharga1);
        info1.setText("1. Potongan harga dapat diberikan setelah\n" +
                "mendapat persetujuan dari Ka.Kwarda/Pimpinan\n" +
                "Kwarda yang diberikan wewenang untuk itu,\n" +
                "dengan ketentuan sebagai berikut :");
        info2=(TextView)findViewById(R.id.infoharga2);
        info2.setText("Pengurus/Andalan Kwarda yang tercantum dalam Surat Keputusan Pengurus/Andalan dan\n" +
                "Badan Kelengkapan Kwarda masa bhakti berjalan, dapat diberikan potongan Harga maksimal 40 %\n" +
                "dari biaya penggunaan Gedung (tidak termasuk fasilitas pendukung dan biaya operasional), atau sesuai dengan\n" +
                "kebijaksanaan Pimpinan Kwarda Lampung");
        info3=(TextView)findViewById(R.id.infoharga3);
        info3.setText("Pengurus/Andalan Cabang yang tercantum dalam Surat Keputusan\n" +
                "Pengurus/Andalan dan Badan Kelengkapan Kwarcab masa bhakti berjalan, dapat diberikan\n" +
                "potongan Harga maksimal 20 % dari biaya penggunaan Gedung (tidak termasuk fasilitas pendukung\n" +
                "dan biaya operasional), atau sesuai dengan kebijaksanaan Pimpinan Kwarda Lampung");
        info4=(TextView)findViewById(R.id.infoharga4);
        info4.setText("2. Potongan harga dapat diberikan kepada yang bersangkutan beserta batih atau keluarganya.\n" +
                "Yang dimaksud batih/keluarga adalah Orang tua kandung, Kakak/adik kandung, dan anak kandung yang masih menjadi tanggung jawabnya");
        info5=(TextView)findViewById(R.id.infoharga5);
        info5.setText("3. Potongan harga diberikan kepada Pengguna Gedung Graha Bhakti Pramuka sebesar 10 % (sepuluh persen) bagi pengguna selain hari Sabtu dan Minggu");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(Menu_inforharga.this, Menu_Utama.class));
        finish();
    }
}