package com.example.buper.Menu.ui.Menu_Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buper.Menu.ui.data_buper.data_buper;
import com.example.buper.Menu.ui.keranjang.keranjang;
import com.example.buper.R;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_Detail_Gedung extends AppCompatActivity {
    ImageView gambarmenu;
    TextView namagedung,lokasigedung,namafasilitas,keterangan,harga,jumlah;
    Button savedata;
    TextView Tanggal_peminjaman,Tanggal_pengembalian;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    Button btDatePickerpeminjaman,btDatePickerpengembalian;
    LinearLayout L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__detail__gedung);
        namagedung=(TextView)findViewById(R.id.Nama_gedung);
        lokasigedung=(TextView)findViewById(R.id.lokasi);
        namafasilitas=(TextView)findViewById(R.id.nama_fasilitas);
        keterangan=(TextView)findViewById(R.id.keterangan);
        harga=(TextView)findViewById(R.id.Harga_Menu);
        Tanggal_peminjaman=(TextView) findViewById(R.id.tanggal_peminjaman);
        Tanggal_pengembalian=(TextView) findViewById(R.id.tanggal_pengembalian);
        L=(LinearLayout)findViewById(R.id.bohongan);
        L.setVisibility(View.GONE);
//        jumlah=(TextView)findViewById(R.id.Jumlah_belanja);
        savedata=(Button) findViewById(R.id.tambahkeranjang);
//        savedata.setVisibility(View.GONE);
//        btDatePickerpeminjaman.setVisibility(View.GONE);
//        btDatePickerpengembalian.setVisibility(View.GONE);
//        Tanggal_pengembalian.setVisibility(View.GONE);
//        Tanggal_peminjaman.setVisibility(View.GONE);


        String id= getIntent().getStringExtra("ID");
        String jenis= getIntent().getStringExtra("JENIS");
        gambarmenu=(ImageView)findViewById(R.id.Gambarmenudetail);
        String gambar_menu= getIntent().getStringExtra("GAMBAR_MENU");
        String nama= getIntent().getStringExtra("NAMA");
        namagedung.setText(nama);
        String Nama_fasilitas= getIntent().getStringExtra("NAMA_FASILITAS");
        namafasilitas.setText(Nama_fasilitas);
        String Keterangan= getIntent().getStringExtra("KETERANGAN");
        keterangan.setText(Keterangan);
        String Harga= getIntent().getStringExtra("HARGA");
        harga.setText(Harga);
        String lokasi= getIntent().getStringExtra("LOKASI");
        lokasigedung.setText(lokasi);
        String status= getIntent().getStringExtra("STATUS");
//        Toast.makeText(this, ""+status, Toast.LENGTH_SHORT).show();
//        if (status.equals("1")){
//            savedata.setVisibility(View.GONE);
//            Toast.makeText(this, "Mohon Maaf Fasilitas Tidak Tersedia", Toast.LENGTH_SHORT).show();
//        }else if(status.equals("0")){
//            savedata.setVisibility(View.VISIBLE);
//        }



//        String Jumlah= getIntent().getStringExtra("LOKASI");
//        lokasigedung.setText(lokasi);
        Picasso.with(this).load(gambar_menu).into(gambarmenu);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.setVisibility(View.VISIBLE);
//                btDatePickerpeminjaman.setVisibility(View.VISIBLE);
//                btDatePickerpengembalian.setVisibility(View.VISIBLE);
//                Tanggal_pengembalian.setVisibility(View.VISIBLE);
//                Tanggal_peminjaman.setVisibility(View.VISIBLE);
                String status= getIntent().getStringExtra("STATUS");
//        Toast.makeText(this, ""+status, Toast.LENGTH_SHORT).show();
                if (status.equals("1")){
//                    savedata.setVisibility(View.GONE);
                    Toast.makeText(Menu_Detail_Gedung.this, "Maaf Fasilitas Sedang Di pinjma ", Toast.LENGTH_SHORT).show();
                }else if(status.equals("0")){
                    Kirim();
//                    savedata.setVisibility(View.VISIBLE);
                }

            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePickerpeminjaman = (Button) findViewById(R.id.bt_datepickerp);
        btDatePickerpengembalian = (Button) findViewById(R.id.bt_datepickerk);
        btDatePickerpeminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        btDatePickerpengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialogtgl();
            }
        });
    }

    private void Kirim() {
        if(Tanggal_peminjaman.getText().toString().equals("")){
            Toast.makeText(Menu_Detail_Gedung.this, "Data Tanggal Peminjaman", Toast.LENGTH_SHORT).show();
        }else if (Tanggal_pengembalian.getText().toString().equals("")){
            Toast.makeText(Menu_Detail_Gedung.this, "Masukan Tanggal Pengembalian", Toast.LENGTH_SHORT).show();
        }else {
            Save_keranjang();
        }
    }

    private void showDateDialogtgl() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                Tanggal_pengembalian.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }


    private void Save_keranjang() {
        String id= getIntent().getStringExtra("ID");
        String jenis= getIntent().getStringExtra("JENIS");
        String nama= getIntent().getStringExtra("NAMA");
        String Harga= getIntent().getStringExtra("HARGA");
        String lokasi= getIntent().getStringExtra("LOKASI");
        String gambar_menu= getIntent().getStringExtra("GAMBAR");
        String tanggal_peminjaman=Tanggal_peminjaman.getText().toString();
        String tanggal_pengembalian=Tanggal_pengembalian.getText().toString();
        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Detail_Gedung.this);
        String email=sharedPrefManager.getSPEmail();
//        Toast.makeText(this, ""+email, Toast.LENGTH_SHORT).show();
        Call<ResponseBody> call = Network.getInstance().getApi().usrpemijaman(id,jenis,email,nama,lokasi,Harga,tanggal_peminjaman,tanggal_pengembalian,gambar_menu);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")) {
                            String pesan = jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            Log.v("ini", pesan);
//                            RegisterBerhasil(pesan_regsiter);
//                            Toast.makeText(Menu_Detail_Gedung.this, "" + pesan, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Menu_Detail_Gedung.this, Menu_Utama.class);
//                            startActivity(intent);
//                            finish();
//                            Intent i = new Intent(Menu_Detail_Gedung.this,data_.class);
//                            startActivity(i);
//                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                            keranjang myFragment = new keranjang();
//                            transaction.replace(R.id.mykeranjang, myFragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
//                            Intent intent = new Intent(Menu_Detail_Gedung.this, keranjang.class);
//                            overridePendingTransition(0, 0);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            finish();
//                            startActivity(intent);
//                            loadFragment(new keranjang());
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            keranjang fragment = new keranjang();
                            fragmentTransaction.add(R.id.mykeranjangaku, fragment);
                            fragmentTransaction.commit();
                        } else {
                            String pesan = jsonRESULTS.getString("message");
                            Log.v("ini", pesan);
                            Toast.makeText(Menu_Detail_Gedung.this, "" + pesan, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    Toast.makeText(Menu_Detail_Gedung.this, "Server Tidak Merespon" , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                Tanggal_peminjaman.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
//    private void loadFragment(Fragment fragment) {
//
    }
