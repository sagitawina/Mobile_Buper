package com.example.buper.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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

public class Menu_edit_profile extends AppCompatActivity {
EditText email,nama,nohp,alamat,username,password;
Button simpan;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_edit_profile);
        email=(EditText)findViewById(R.id.Email);
        nama=(EditText)findViewById(R.id.Nama);
        nohp=(EditText)findViewById(R.id.Telphone);
        alamat=(EditText)findViewById(R.id.Alamat);
        username=(EditText)findViewById(R.id.Username);
        password=(EditText)findViewById(R.id.Password);
        simpan=(Button)findViewById(R.id.button_save);

        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_edit_profile.this);
        email.setText(sharedPrefManager.getSPEmail());
        nama.setText(sharedPrefManager.getSPNama());
        nohp.setText(sharedPrefManager.getSPTelpon());
        alamat.setText(sharedPrefManager.getSPAlamat());
        username.setText(sharedPrefManager.getSpUsername());
//        password.setText(sharedPrefManager.getSpPassword());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpan_data();

            }
        });
    }

    private void simpan_data() {
        loading = ProgressDialog.show(Menu_edit_profile.this,"Loading .....",null,true,true);

        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_edit_profile.this);
        String id_user=sharedPrefManager.getSpId();
        String email_user=email.getText().toString();
        String nama_user=nama.getText().toString();
        String nohp_user=nohp.getText().toString();
        String alamat_user=alamat.getText().toString();
        String username_user=username.getText().toString();
        String password_user=password.getText().toString();

        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userEdit(id_user,username_user,password_user,nama_user,email_user,nohp_user,alamat_user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan=jsonRESULTS.getString("message");
                            Log.d("response api", jsonRESULTS.toString());
                            Log.v("ini",pesan);
                            loading.dismiss();
                            Toast.makeText(Menu_edit_profile.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Menu_edit_profile.this,Menu_Login.class);
                            SharedPrefManager sharedPrefManager1=new SharedPrefManager(Menu_edit_profile.this);
                            sharedPrefManager1.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                            startActivity(intent);
                            finish();
                        } else{
                            loading.dismiss();
                            String pesan=jsonRESULTS.getString("message");
                            Toast.makeText(Menu_edit_profile.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan);
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
                    loading.dismiss();
                    Toast.makeText(Menu_edit_profile.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
//                    RegisterGagal(pesan_regsiter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}