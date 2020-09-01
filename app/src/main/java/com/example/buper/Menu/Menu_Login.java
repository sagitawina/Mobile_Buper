package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buper.R;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_Login extends AppCompatActivity {
    EditText Username,Password;
    Button Login,Register;
    ProgressDialog loading;
    String  pesan_login,nama;
    SharedPrefManager sharePrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__login);

        String niK= getIntent().getStringExtra("NIK");
        Username=(EditText) findViewById(R.id.LoginEmail);
        Username.setText(niK);
        Password=(EditText) findViewById(R.id.LoginPassword);
        Login=(Button)findViewById(R.id.LoginbtnLogin);
        Register=(Button)findViewById(R.id.LoginbtnRegister);

        sharePrefManager = new SharedPrefManager(Menu_Login.this);
        if (sharePrefManager.getSudahLogin()){
            startActivity(new Intent(Menu_Login.this, Menu_Utama.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu_Login.this,Menu_Register.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Menu_Login.this,Menu_Utama.class);
//                startActivity(intent);
//                finish();
                Chexinputan();
            }
        });

    }

    private void Chexinputan() {
        loading = ProgressDialog.show(Menu_Login.this,"Loading.....",null,true,true);
        String Email = Username.getText().toString();
        String Psw = Password.getText().toString();
        if (Email.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
        } else if (Psw.isEmpty()) {
            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
        } else {
            RequestLogin();
        }

    }

    private void RequestLogin() {
        String Email = Username.getText().toString();
        String Psw = Password.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userLogin(Email,Psw);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            pesan_login=jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            Log.v("ini",pesan_login);
//                            String json=jsonRESULTS.getString("user");
//                            JSONObject jsonuser = new JSONObject(json);
                            nama=jsonRESULTS.getString("nama_lengkap");
                            String id=jsonRESULTS.getString("id");
                            String email=jsonRESULTS.getString("email");
                            String namalengkap=jsonRESULTS.getString("nama_lengkap");
                            String username=jsonRESULTS.getString("username");
                            String password=jsonRESULTS.getString("password");
                            String alamat=jsonRESULTS.getString("alamat");
                            String no_hp=jsonRESULTS.getString("no_hp");
                            sharePrefManager.saveSPString(SharedPrefManager.SP_ID, id);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_NAMA, namalengkap);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_PASSWORD, password);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                            sharePrefManager.saveSPString(SharedPrefManager.SP_TELPON, no_hp);
                            sharePrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            LoginBerhasil(pesan_login);
                        } else{
                            pesan_login=jsonRESULTS.getString("message");
                            Log.v("ini",pesan_login);
                            LoginGagal(pesan_login);
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
                    LoginGagal("Masalah Internet");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void LoginBerhasil(String pesan){
        onsukses(pesan);
        loading.dismiss();
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();

    }
    public void LoginGagal(String pesan){
        ongagal(pesan);
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();
        loading.dismiss();
    }

    public void onsukses(String pesan){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_DARK);
//    new AlertDialog.Builder(this, AlertDialog.BUTTON_NEUTRAL);
        alertDialogBuilder.setMessage(pesan+" : "+ nama);
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent =new Intent(Menu_Login.this,Menu_Utama.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void ongagal(String pesan){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
        alertDialogBuilder.setMessage(pesan+" : "+ pesan_login);
        alertDialogBuilder.setPositiveButton("Ulangi",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}