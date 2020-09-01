package com.example.buper.Menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buper.R;
import com.example.buper.Response.ApiConfig;
import com.example.buper.Response.AppConfig;
import com.example.buper.Server.Network;
import com.example.buper.Storage.SharedPrefBuktiBayar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload_Bukti_Bayar extends AppCompatActivity {

    ImageView GambarBukti;
    Button Pilih, Simpan;
    EditText NamaGambar;
    String mediaPath;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;
//    private Activity EasyPermissions;
    private Reference call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__bukti__bayar2);

        progressDialog =new ProgressDialog(Upload_Bukti_Bayar.this);
        progressDialog.setMessage("Loading...");

        GambarBukti = (ImageView)findViewById(R.id.gambarBukti);
        Pilih = (Button) findViewById(R.id.btn_pilih);
        Simpan = (Button) findViewById(R.id.btnSimpan);
        NamaGambar = (EditText) findViewById(R.id.namagambar);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (EasyPermissions.hasPermissions(Upload_Bukti_Bayar.this, galleryPermissions)) {
                    uploadFile();
                } else {
                    EasyPermissions.requestPermissions(Upload_Bukti_Bayar.this, "Access for storage",
                            101, galleryPermissions);
                }

            }
        });

        Pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                NamaGambar.setText(mediaPath);
                // Set the Image in ImageView for Previewing the Media
                GambarBukti.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } // When an Video is picked
            else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

//                mediaPath1 = cursor.getString(columnIndex);
//                str2.setText(mediaPath1);
//                // Set the Video Thumb in ImageView Previewing the Media
//                imgView.setImageBitmap(getThumbnailPathForLocalFile(MainActivity.this, selectedVideo));
                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    private void uploadFile() {
        progressDialog.show();
        File file = new File(String.valueOf(mediaPath));
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
        Call<ResponseBody> call = getResponse.uploadBuktibayar(fileToUpload, filename);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Toast.makeText(Input_Datamenu.this, ""+response, Toast.LENGTH_SHORT).show();
                ResponseBody responseBody=response.body();
                Log.v("data",responseBody.toString());
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            String NamaGambar=jsonRESULTS.getString("tmp_name");
//                            RequestMenu(NamaGambar);
                            progressDialog.dismiss();
                            Intent intent=new Intent(Upload_Bukti_Bayar.this,Menu_Utama.class);
                            startActivity(intent);
                            finish();

//                            Toast.makeText(Input_Datamenu.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("false")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Upload_Bukti_Bayar.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan_login);
                            progressDialog.dismiss();
//                            InputGagal();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        String pesan=jsonRESULTS.getString("message");
                        Toast.makeText(Upload_Bukti_Bayar.this, ""+pesan, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

//                ResponseBody responseBody=response.body();
                try {
                    Toast.makeText(Upload_Bukti_Bayar.this, "", Toast.LENGTH_SHORT).show();
                    Log.v("response:","gagal");
                    InputGagal();
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void InputGagal() {
        Toast.makeText(this, "GAGAL", Toast.LENGTH_SHORT).show();
    }
}