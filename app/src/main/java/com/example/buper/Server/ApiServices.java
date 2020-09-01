package com.example.buper.Server;

import com.example.buper.Response.Respnse_gallery;
import com.example.buper.Response.Respon_keranjang;
import com.example.buper.Response.Response_Gedun;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> userLogin(
            @Field("username") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> userRegister(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("no_hp") String hp,
            @Field("alamat") String alamat
    );
    @FormUrlEncoded
    @POST("lokasi")
    Call<ResponseBody> userRegister(
            @Field("no") String no,
            @Field("nama_tempat") String namatempat
    );


        @GET("tampil_gallery.php")
            Call<Respnse_gallery> tamppil_gallery(
        );


    @FormUrlEncoded
    @POST("Tampil_buper.php")
    Call<Response_Gedun> tampil_buper(
            @Field("jenis") String jenis
    );

    @FormUrlEncoded
    @POST("tampil_outdoor.php")
    Call<Response_Gedun> tampil_outdoor(
            @Field("jenis") String jenis
    );

    @FormUrlEncoded
    @POST("tambah_peminjaman.php")
    Call<ResponseBody> usrpemijaman(
            @Field("id") String id,
            @Field("jenis") String jenis,
            @Field("email") String email,
            @Field("nama_buper") String nama_buper,
            @Field("alamat") String alamat,
            @Field("harga") String harga,
            @Field("tanggal_peminjaman") String tanggal_peminjaman,
            @Field("tanggal_pengembalian") String tanggal_pengembalian,
            @Field("gambar") String gambar

    );

    @FormUrlEncoded
    @POST("tampil_keranjang.php")
    Call<Respon_keranjang> tampil_keranjang(
            @Field("email") String email,
            @Field("status") String status
    );
    @FormUrlEncoded
    @POST("tampil_keranjang.php")
    Call<Respon_keranjang> tampil_notif(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("tampil_transaksi.php")
    Call<Respon_keranjang> tampil_transaksi(
            @Field("email") String email,
            @Field("status") String status
    );
    @FormUrlEncoded
    @POST("edit_profile.php")
    Call<ResponseBody> userEdit(
            @Field("id_user") String id_user,
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("no_hp") String hp,
            @Field("alamat") String alamat
    );
    @FormUrlEncoded
    @POST("delete_peminjaman.php")
    Call<ResponseBody> delete_peminjaman(
            @Field("id") String id
    );
}
