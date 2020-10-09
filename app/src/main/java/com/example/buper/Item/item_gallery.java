package com.example.buper.Item;

import com.google.gson.annotations.SerializedName;

public class item_gallery {
    @SerializedName("id_galeri")
    private String id_galeri;

    @SerializedName("deskripsi")
    private String deskrispi;

    @SerializedName("tittle")
    private String tittle;

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

    public String getTittle(){
        return tittle;
    }

    public void setTittle(String tittle){

        this.tittle = tittle;
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
                        ",tittle= '" + tittle+ '\'' +
                        "}";
    }
}
