package com.example.buper.Server;

import java.net.URI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static final String  URL="http://192.168.43.236/";
    public static final String BASE_URL = URL+"Api_Skripsi/";
    public static final String Url_gambar=URL+"kwartirlampung/public/img/upload/";
    private static Network mInstance;
    private Retrofit retrofit;
    private Network(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Network getInstance(){
        if (mInstance == null ){
            mInstance = new Network();
        }
        return mInstance;
    }
    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
}
