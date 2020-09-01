package com.example.buper.Item;

import com.google.gson.annotations.SerializedName;

public class item_gallery {
    @SerializedName("id_galeri")
    private String id_galeri;

    @SerializedName("deskripsi")
    private String deskrispi;

    @SerializedName("foto")
    private String foto;

    public void setId(String id_galeri){

        this.id_galeri = id_galeri;
    }

    public String getId_galeri(){
        return id_galeri;
    }



    public void setDeskrispi(String deskrispi){

        this.deskrispi = deskrispi;
    }

    public String getDeskrispi(){
        return deskrispi;
    }
    public void setFoto(String foto){

        this.foto = foto;
    }

    public String getFoto(){

        return foto;
    }


    @Override
    public String toString(){
        return
                "Menu_Item{" +
                        "id_galeri= '" + id_galeri+ '\'' +
                        " ,foto= '" + foto+ '\'' +
                        ",deskrispi= '" + deskrispi+ '\'' +
                        "}";
    }
}
