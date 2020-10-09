package com.example.buper.Item;

import com.google.gson.annotations.SerializedName;

public class item_keranjang {
    @SerializedName("id_peminjaman")
    private String id_peminjaman;

    @SerializedName("id")
    private String id;
    @SerializedName("id_buper")
    private String id_buper;
    @SerializedName("jenis")
    private String jenis;

    @SerializedName("email")
    private String email;

    @SerializedName("nama_buper")
    private String nama_buper;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("harga")
    private String harga;

    @SerializedName("status")
    private String status;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("gambar")
    private String gambar;

    public void setId_peminjaman(String id_peminjaman){

        this.id = id_peminjaman;
    }

    public String getId_peminjaman(){

        return id_peminjaman;
    }

    public void setId(String id){

        this.id = id;
    }

    public String getId(){
        return id;
    }



    public void setjenis(String jenis){

        this.jenis = jenis;
    }

    public String getjenis(){
        return jenis;
    }
    public void setEmail(String email){

        this.email = email;
    }

    public String getEmail(){

        return email;
    }

    public void setNama_buper(String nama_buper){

        this.nama_buper = nama_buper;
    }

    public String getNama_buper(){

        return nama_buper;
    }

    public void setAlamat(String alamat){

        this.alamat = alamat;
    }

    public String getAlamat(){

        return alamat;
    }

    public void setHarga(String harga){

        this.harga = harga;
    }

    public String getHarga(){

        return harga;
    }

    public void setStatus(String status){

        this.status = status;
    }

    public String getStatus(){

        return status;
    }

    public void setTanggal(String tanggal){

        this.tanggal = tanggal;
    }

    public String getTanggal(){

        return tanggal;
    }

    public void setCreated_at(String created_at){

        this.created_at = created_at;
    }

    public String getCreated_at(){

        return created_at;
    }

    public void setUpdated_at(String updated_at){

        this.updated_at = updated_at;
    }

    public String getUpdated_at(){

        return updated_at;
    }

    public void setGambar(String gambar){

        this.gambar = gambar;
    }

    public String getGambar(){

        return gambar;
    }

    public void setId_buper(String id_buper){

        this.id_buper = id_buper;
    }

    public String getId_buper(){
        return id_buper;
    }


    @Override
    public String toString(){
        return
                "Menu_Item{" +
                        "id_peminjaman= '" +id_peminjaman+ '\'' +
                        "id= '" +id+ '\'' +
                        "id_buper= '" +id_buper+ '\'' +
                        "jenis= '" +jenis+ '\'' +
                        " ,email= '" + email+ '\'' +
                        " ,nama_buper= '" + nama_buper+ '\'' +
                        ",alamat= '" + alamat+ '\'' +
                        ",harga= '" + harga+ '\'' +
                        "status= '" +status+ '\'' +
                        "tanggal= '" +tanggal+ '\'' +
                        "gambar= '" +gambar+ '\'' +
                        "created_at= '" +created_at+ '\'' +
                        "updated_at= '" +updated_at+ '\'' +
                        "}";
    }
}
