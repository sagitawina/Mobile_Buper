package com.example.buper.Item;

import com.google.gson.annotations.SerializedName;

public class Item_Transaksi {
    @SerializedName("id_transaksi")
    private String id_transaksi;
    @SerializedName("jumlah")
    private String jumlah;
    @SerializedName("nama_buper")
    private String nama_buper;
    @SerializedName("status")
    private String status;
    public void setId_transaksi(String id_transaksi){

        this.id_transaksi = id_transaksi;
    }

    public String getId_transaksi(){

        return id_transaksi;
    }
    public void setJumlah(String jumlah){

        this.jumlah = jumlah;
    }

    public String getJumlah(){

        return jumlah;
    }
    public void setNama_buper(String nama_buper){

        this.nama_buper = nama_buper;
    }

    public String getNama_buper(){

        return nama_buper;
    }
    public void setStatus(String status){

        this.status = status;
    }

    public String getStatus(){

        return status;
    }
    @Override
    public String toString(){
        return
                "Item_Transsaksi{" +
                        "id_transaksi= '" +id_transaksi+ '\'' +
                        "nama_buper= '" +nama_buper+ '\'' +
                        " ,jumlah= '" + jumlah+ '\'' +
                        ",status= '" + status+ '\'' +
                        "}";
    }
}
