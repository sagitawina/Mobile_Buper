package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buper.Adapter.Adapeter_detail_keranjang;
import com.example.buper.Adapter.Adapter_keranjang;
import com.example.buper.Item.item_keranjang;
import com.example.buper.Menu.ui.Menu_Detail.Menu_Detail_Gedung;
import com.example.buper.R;
import com.example.buper.Response.Respon_keranjang;
import com.example.buper.Server.ApiServices;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_Detail_Keranjang extends AppCompatActivity {
    GridLayoutManager llm;
    private RecyclerView recyclerView;
    private int waktu_loading=2000;
    Button Upload;
    TextView Total_Bayar;
    int totalsaldo,totalkeluar,totalmasuk;
    ProgressDialog loading;
    Locale localeID = new Locale("in", "ID");NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    @TargetApi(Build.VERSION_CODES.N)
    TextView Tanggal_peminjaman,Tanggal_pengembalian;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    Button btDatePickerpeminjaman,btDatePickerpengembalian,Total;
    LinearLayout L;
    String hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__detail__keranjang);
        recyclerView = (RecyclerView)findViewById(R.id.list_detail_keranjang);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(Menu_Detail_Keranjang.this,1);
        recyclerView.setLayoutManager(llm);
        tampilmenu();
        tampil_totalbayar();
        Tanggal_peminjaman=(TextView) findViewById(R.id.tanggal_peminjaman);
        Tanggal_pengembalian=(TextView) findViewById(R.id.tanggal_pengembalian);
        L=(LinearLayout)findViewById(R.id.bohongan);
        L.setVisibility(View.GONE);
        Total_Bayar=(TextView)findViewById(R.id.total_bayar);
        Upload=(Button)findViewById(R.id.Uploadmekanisme);
        Upload.setVisibility(View.GONE);
        Total=(Button)findViewById(R.id.total);
        Total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= getIntent().getStringExtra("ID");
                if(Tanggal_peminjaman.getText().toString().equals("")){
                    L.setVisibility(View.VISIBLE);
                    Toast.makeText(Menu_Detail_Keranjang.this, "Masukan Tanggal Peminjaman", Toast.LENGTH_SHORT).show();
                }else if (Tanggal_pengembalian.getText().toString().equals("")){
                    L.setVisibility(View.VISIBLE);
                    Toast.makeText(Menu_Detail_Keranjang.this, "Masukan Tanggal Pengembalian", Toast.LENGTH_SHORT).show();
                }else {
                    tampil_totalsaldo();
                    Upload.setVisibility(View.VISIBLE);

                }
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id= getIntent().getStringExtra("ID");
//                if(Tanggal_peminjaman.getText().toString().equals("")){
//                    L.setVisibility(View.VISIBLE);
//                    Toast.makeText(Menu_Detail_Keranjang.this, "Masukan Tanggal Peminjaman", Toast.LENGTH_SHORT).show();
//                }else if (Tanggal_pengembalian.getText().toString().equals("")){
//                    L.setVisibility(View.VISIBLE);
//                    Toast.makeText(Menu_Detail_Keranjang.this, "Masukan Tanggal Pengembalian", Toast.LENGTH_SHORT).show();
//                }else {
                    Kirim();
//                }
            }

            private void Kirim() {
                String id= getIntent().getStringExtra("ID");
                String id_keranjang= getIntent().getStringExtra("ID_KERANJANG");
                String nama= getIntent().getStringExtra("NAMA");
                String Total_tagihan=Total_Bayar.getText().toString();
                String tanggal_peminjaman=Tanggal_peminjaman.getText().toString();
                String tanggal_pengembalian=Tanggal_pengembalian.getText().toString();
                Intent intent =new Intent(Menu_Detail_Keranjang.this,Upload_Mekanisme.class);
                intent.putExtra("ID", id);
                intent.putExtra("TGLPINJAM",tanggal_peminjaman);
                intent.putExtra("ID_KERANJANG",id_keranjang);
                intent.putExtra("TGLKEMBALI", tanggal_pengembalian);
                intent.putExtra("HARGA",Total_tagihan);
                intent.putExtra("NAMA",nama);
                startActivity(intent);
                finish();
            }
        });
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
    @SuppressLint("SetTextI18n")
    private void tampil_totalsaldo() {
//        String uangmasuk=saldomasuk.getText().toString();
//        Toast.makeText(this, ""+uangmasuk, Toast.LENGTH_SHORT).show();
//        totalsaldo=totalmasuk-totalkeluar;
        String tanggal_peminjaman=Tanggal_peminjaman.getText().toString();
        String tanggal_pengembalian=Tanggal_pengembalian.getText().toString();
        DateFormat dateAwal = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateAkhir = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date tglAwal = dateAwal.parse(tanggal_peminjaman);
            Date tglAkhir = dateAkhir.parse(tanggal_pengembalian);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(tglAwal);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(tglAkhir);
            hasil = String.valueOf(daysBetween(cal1, cal2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int data=totalmasuk;
        Integer data3= Integer.valueOf(data);
        Integer data4=Integer.valueOf(hasil);
        Integer data2=data3 * data4;
        Total_Bayar.setText(formatRupiah.format((double)data2));
//        Toast.makeText(this, ""+totalmasuk, Toast.LENGTH_SHORT).show();
    }
    private void tampil_totalbayar() {
        loading = ProgressDialog.show(Menu_Detail_Keranjang.this,"Loading.....",null,true,true);
        String id= getIntent().getStringExtra("ID");
//        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Detail_Keranjang.this);
        String status="entry";
        String email=sharedPrefManager.getSPEmail();
        ApiServices api = Network.getInstance().getApi();
        Call<Respon_keranjang> menuCall = api.detailkeranjang(email,status,id);
        menuCall.enqueue(new Callback<Respon_keranjang>() {
            @Override
            public void onResponse(Call<Respon_keranjang> call, Response<Respon_keranjang> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<item_keranjang> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        List<item_keranjang> semuadosenItems = response.body().getMenu();
                        try {
                            loading.dismiss();
                            Locale localeID = new Locale("in", "ID");
                            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                            for (int i = 0; i < semuadosenItems.size(); i++){
//                                    Toast.makeText(SaldoTabungan.this, ""+i, Toast.LENGTH_SHORT).show();
                                if(i == 0){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    totalmasuk=data;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i == 1){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    totalmasuk=data+data1;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==2){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    totalmasuk=data+data1+data2;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==3){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    totalmasuk=data+data1+data2+data3;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==4){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }
                                else if(i==5){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==6){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga());
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    Integer data6= Integer.valueOf(semuadosenItems.get(6).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==7){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga( ));
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    Integer data6= Integer.valueOf(semuadosenItems.get(6).getHarga());
                                    Integer data7= Integer.valueOf(semuadosenItems.get(7).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                } else if(i==8){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga( ));
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    Integer data6= Integer.valueOf(semuadosenItems.get(6).getHarga());
                                    Integer data7= Integer.valueOf(semuadosenItems.get(7).getHarga());
                                    Integer data8= Integer.valueOf(semuadosenItems.get(8).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7+data8;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                } else if(i==9){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga( ));
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    Integer data6= Integer.valueOf(semuadosenItems.get(6).getHarga());
                                    Integer data7= Integer.valueOf(semuadosenItems.get(7).getHarga());
                                    Integer data8= Integer.valueOf(semuadosenItems.get(8).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7+data8;
                                    Integer data9= Integer.valueOf(semuadosenItems.get(9).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7+data8+data9;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i==10){
                                    loading.dismiss();
                                    Integer data= Integer.valueOf(semuadosenItems.get(0).getHarga());
                                    Integer data1= Integer.valueOf(semuadosenItems.get(1).getHarga( ));
                                    Integer data2= Integer.valueOf(semuadosenItems.get(2).getHarga());
                                    Integer data3= Integer.valueOf(semuadosenItems.get(3).getHarga());
                                    Integer data4= Integer.valueOf(semuadosenItems.get(4).getHarga());
                                    Integer data5= Integer.valueOf(semuadosenItems.get(5).getHarga());
                                    Integer data6= Integer.valueOf(semuadosenItems.get(6).getHarga());
                                    Integer data7= Integer.valueOf(semuadosenItems.get(7).getHarga());
                                    Integer data8= Integer.valueOf(semuadosenItems.get(8).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7+data8;
                                    Integer data9= Integer.valueOf(semuadosenItems.get(9).getHarga());
                                    Integer data10= Integer.valueOf(semuadosenItems.get(10).getHarga());
                                    totalmasuk=data+data1+data2+data3+data4+data5+data6+data7+data8+data9+data10;
                                    Total_Bayar.setText(formatRupiah.format((double)totalmasuk));
//                                    tampil_totalsaldo();
                                }else if(i<0){
                                    loading.dismiss();
                                    int uang=0;
//                                    Toast.makeText(Menu_Detail_Keranjang.this, "Belum Memiliki saldo", Toast.LENGTH_SHORT).show();
                                    Total_Bayar.setText(formatRupiah.format((double)uang));
//                                    tampil_totalsaldo();
                                }else{
                                    loading.dismiss();
                                    int uang=0;
                                    Toast.makeText(Menu_Detail_Keranjang.this, "Belum Memiliki saldo", Toast.LENGTH_SHORT).show();
                                    Total_Bayar.setText(formatRupiah.format((double)uang));
//                                    tampil_totalsaldo();
                                }


                            }
                        }
                        catch (Exception e){
                            loading.dismiss();
//                            Toast.makeText(Menu_Detail_Keranjang.this, "Terjadi Kesalahan pada server", Toast.LENGTH_SHORT).show();
//                                saldo.setText("="+formatRupiah.format((double)totalsaldo));
                        }

                    } else {
                        loading.dismiss();
//                        Toast.makeText(Menu_Detail_Keranjang.this, "Tidak Ada data  saat ini", Toast.LENGTH_SHORT).show();
                    }

                }
            }


            @Override
            public void onFailure(Call<Respon_keranjang> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });

    }

    private void tampilmenu() {
        String id= getIntent().getStringExtra("ID");
//        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Detail_Keranjang.this);
        String status="entry";
        String email=sharedPrefManager.getSPEmail();
        ApiServices api = Network.getInstance().getApi();
        Call<Respon_keranjang> menuCall = api.detailkeranjang(email,status,id);
        menuCall.enqueue(new Callback<Respon_keranjang>() {
            @Override
            public void onResponse(Call<Respon_keranjang> call, Response<Respon_keranjang> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<item_keranjang> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapeter_detail_keranjang adapter = new Adapeter_detail_keranjang(Menu_Detail_Keranjang.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Menu_Detail_Keranjang.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Respon_keranjang> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
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
    private static long daysBetween(Calendar tanggalAwal, Calendar tanggalAkhir) {
        long lama = 0;
        Calendar tanggal = (Calendar) tanggalAwal.clone();
        while (tanggal.before(tanggalAkhir)) {
            tanggal.add(Calendar.DAY_OF_MONTH, 1);
            lama++;
        }
        return lama;
    }
}