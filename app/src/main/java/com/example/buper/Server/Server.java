package com.example.buper.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    public static final String  URL=Network.URL;
    public static final String BASE_URL = "http://192.168.43.236:8000/api/";
    public static final String Url_gambar=URL+"kwartirlampung/public/img/upload/";
    private static Server mInstance;
    private Retrofit retrofit;
    //    private Initretrofit initretrofit;
    private Server(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized Server getInstance(){
        if (mInstance == null ){
            mInstance = new Server();
        }
        return mInstance;
    }
    public Myservice getApi(){
        return retrofit.create(Myservice.class);
    }
}
