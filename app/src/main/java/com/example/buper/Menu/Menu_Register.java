package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buper.R;
import com.example.buper.Server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_Register extends AppCompatActivity {
    EditText Username,Password,Nama,Telpon,Alamat,Email;
    Button Login,Register;
    ProgressDialog loading;
    String  pesan_regsiter,nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__register);
        Nama=(EditText) findViewById(R.id.RegisterNama);
        Username=(EditText) findViewById(R.id.RegisterUsername);
        Password=(EditText) findViewById(R.id.RegisterPassword);
        Telpon=(EditText) findViewById(R.id.RegisterTelphone);
        Alamat=(EditText) findViewById(R.id.RegisterAlamat);
        Email=(EditText) findViewById(R.id.RegisterEmail);
        Login=(Button)findViewById(R.id.RegisterbtnLogin);
        Register=(Button)findViewById(R.id.RegisterbtnRegister);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chex();

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Register.this,Menu_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void chex() {
        loading = ProgressDialog.show(Menu_Register.this,"Loading.....",null,true,true);
        String email = Email.getText().toString();
        String Psw = Password.getText().toString();
        String Nm = Nama.getText().toString();
        String tlp = Telpon.getText().toString();
        String Alm = Alamat.getText().toString();
        String Usr = Username.getText().toString();
        if (email.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        } else if (Psw.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (tlp.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Telpon tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (Nm.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (Alm.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Alamat tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else if (Usr.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Username tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            Register();
        }
    }

    private void Register() {
        String email = Email.getText().toString();
        String Psw = Password.getText().toString();
        String Nm = Nama.getText().toString();
        String tlp = Telpon.getText().toString();
        String Alm = Alamat.getText().toString();
        String Usr = Username.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userRegister(Usr,Psw,Nm,email,tlp,Alm);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            pesan_regsiter=jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            Log.v("ini",pesan_regsiter);
                            RegisterBerhasil(pesan_regsiter);
                            Intent intent=new Intent(Menu_Register.this,Menu_Login.class);
                            intent.putExtra("NIK",Username.getText().toString());
                            startActivity(intent);
                            finish();
                        } else{
                            pesan_regsiter=jsonRESULTS.getString("message");
                            Log.v("ini",pesan_regsiter);
                            RegisterGagal(pesan_regsiter);
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
                    RegisterGagal(pesan_regsiter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void RegisterBerhasil(String pesan_regsiter){
//        onsukses();
        Toast.makeText(this, ""+ pesan_regsiter, Toast.LENGTH_SHORT).show();
        loading.dismiss();

    }
    public void RegisterGagal(String pesan_regsiter){
//        ongagal();
        Toast.makeText(this, ""+ pesan_regsiter, Toast.LENGTH_SHORT).show();
        loading.dismiss();
    }

}