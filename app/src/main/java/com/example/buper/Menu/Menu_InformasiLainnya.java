package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buper.R;

public class Menu_InformasiLainnya extends AppCompatActivity {
TextView Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__informasi_lainnya);
        Info=(TextView)findViewById(R.id.Informasi);
        Info.setText("1. Penggunaan Catering dan Pelaminan/dekorasi dan atau sejenisnya\n" +
                "    diluar rekanan yang telah Bekerjasama dengan Pihak Gedung,  dikenakan charger/biaya\n" +
                "    masing-masing : \n"+"Penyedia/Pengelola Catering sebesar maksimal Rp.3.000.000,-(tiga juta rupiah) setiap kegiatan, disesuaikan dengan kapasitas dan penilaian Manajer Gedung.\n"
        +"Penyedia/Pengelola Pelaminan/dekorasi sebesar maksimal Rp.2.000.000,-\n" +
                "(dua juta rupiah) setiap kegiatan, disesuaikan dengan kapasitas dan penilaian Manajer Gedungn\n"
                +"2. Apabila pemakaian melebihi jadwal yang telah ditentukan,\n" +
                "maka pengguna dikenakan biaya tambahan Rp.1.000.000,- (Satu juta rupiah) per Jam.\n" +
                "(Deposit Rp.2.000.000,- (dua juta rupiah)) dibayar oleh pengguna bersamaan dengan pelunasan"
                +"3. Untuk persiapan dekorasi dan catering diberikan waktu 4 (empat)\n" +
                "jam sebelum acara dengan penerangan seperlunya dan untuk pembongkaran perlengkapan dekorasi dan\n" +
                "catering diberikan waktu 1-2 jam setelah acara serta tanpa penitipan barang\n"
                +"4. Pengguna dianggap sah sebagai pemesan setelah mengajukan\n" +
                "Permohonaan Peminjaman/Penggunaan Gedung dan fasilitas lainnya dengan mengisi formulir Pemesanan\n" +
                "Gedung  dan menyerahkan uang pendaftaran atau  Booking fee sebesar Rp.500.000,-\n" +
                "(Lima ratus ribu rupiah) dan  foto copy KTP. (lampiran 1)");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Menu_Utama.class));
        finish();
    }
}