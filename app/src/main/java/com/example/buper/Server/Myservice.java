package com.example.buper.Server;

import com.example.buper.Response.Response_Gedun;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Myservice {
    @GET("getapriori/{email}")
    Call<Response_Gedun> Tampil_Rekomendasi (@Path("email") String email);
}
