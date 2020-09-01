package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buper.R;

public class Menu_infomekanisme extends AppCompatActivity {
TextView info1,info2,info3,info4,info5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_infomekanisme);
        info1=(TextView)findViewById(R.id.mekanismeinfo);
        info1.setText("1.Calon Pengguna mengajukan permohonan kepada Manajer Pengelolan Gedung dan apabila Gedung dan atau Sarana lainnya pada tanggal dan hari pemesanan tidak sedang " +
                "dalam digunakan untuk kepentingan internal Gerakan Pramuka, maka permohonan dapat disetujui.");
        info2=(TextView)findViewById(R.id.info2);
        info2.setText("2. Pemesanan waktu (Booking Fee) dikenakan biaya sebesar Rp. 500.000,- (Lima ratus ribu rupiah), dan tidak termasuk\n" +
                "dalam Paket Sewa, kecuali Penyewa melakukan pembayaran Uang muka minimal 50 % dari Nilai Sewa.");
        info3=(TextView)findViewById(R.id.info3);
        info3.setText("3. Penggunaan Gedung pada Malam hari,hanya untuk hari Senin sampai dengan hari Kamis.");
        info4=(TextView)findViewById(R.id.info4);
        info4.setText("4. Pembayaran Uang muka dilakukan tiga hari setelah pemesanan Waktu atau selambat-lambatnya satu bulan sebelum pelaksanaan sebesar 50 % dari Nilai sewa\n" +
                "yang disepakati.");
        info5=(TextView)findViewById(R.id.info5);
        info5.setText("5.\tPelunasan biaya Gedung telah diselesaikan/dibayar paling lambat 15 (lima belas) hari sebelum pelaksanaan kegiatan.");
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

    public static class Upload_Bukti_Bayar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload__bukti__bayar);
        }
    }
}