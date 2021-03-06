package com.example.buper.Response;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiConfig {
    @Multipart
    @POST("UploadBuktiBayar.php")
    Call<ResponseBody> uploadBuktibayar(@Part MultipartBody.Part file,
                                        @Part("file") RequestBody name);
}
