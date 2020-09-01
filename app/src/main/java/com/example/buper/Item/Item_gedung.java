package com.example.buper.Item;

import com.google.gson.annotations.SerializedName;

public class Item_gedung {
    @SerializedName("id")
    private String id;
    @SerializedName("statusPinjam")
    private String status;
    @SerializedName("nama_buper")
    private String nama_buper;

    @SerializedName("harga")
    private String harga;

    @SerializedName("image")
    private String foto;

    @SerializedName("nama_fasilitas")
    private String nama_fasilitas;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("lat")
    private String Lat;

    @SerializedName("long")
    private String Long;

    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("jenis")
    private String jenis;

    public void setId(String id){

        this.id = id;
    }

    public String getId(){

        return id;
    }

    public void setNama_buper(String nama_buper){

        this.nama_buper = nama_buper;
    }

    public String getNama_buper(){
        return nama_buper;
    }



    public void setHarga(String deskrispi){

        this.harga = harga;
    }

    public String getHarga(){
        return harga;
    }
    public void setFoto(String foto){

        this.foto = foto;
    }

    public String getFoto(){

        return foto;
    }


    public void setNama_fasilitas(String nama_fasilitas){

        this.nama_fasilitas = nama_fasilitas;
    }

    public String getNama_fasilitas(){

        return nama_fasilitas;
    }

    public void setKeterangan(String keterangan){

        this.keterangan = keterangan;
    }

    public String getKeterangan(){

        return keterangan;
    }

    public void setLat(String lat){

        this.Lat = lat;
    }

    public String getLat(){

        return Lat;
    }

    public void setLong(String Long){

        this.Long = Long;
    }

    public String getLong(){

        return Long;
    }

    public void setLokasi(String lokasi){

        this.lokasi = lokasi;
    }

    public String getLokasi(){

        return lokasi;
    }

    public void setJenis(String jenis){

        this.jenis = jenis;
    }

    public String getJenis(){

        return jenis;
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
                "Menu_Item{" +
                        "id= '" +id+ '\'' +
                        "nama= '" +nama_buper+ '\'' +
                        " ,foto= '" + foto+ '\'' +
                        ",harga= '" + harga+ '\'' +
                        ",nama_fasilitas= '" + nama_fasilitas+ '\'' +
                        "keterangan= '" +keterangan+ '\'' +
                        "lat= '" +Lat+ '\'' +
                        "long= '" +Long+ '\'' +
                        "status= '" +status+ '\'' +
                        "lokasi= '" +lokasi+ '\'' +
                        "jenis= '" +jenis+ '\'' +
                        "}";
    }
}
