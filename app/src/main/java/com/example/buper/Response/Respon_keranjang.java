package com.example.buper.Response;

import com.example.buper.Item.Item_gedung;
import com.example.buper.Item.item_keranjang;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Respon_keranjang {
    @SerializedName("keranjang")
    private List<item_keranjang> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<item_keranjang>menu) {
        this.menu = menu;
    }

    public List<item_keranjang> getMenu()
    {
        return menu;
    }

    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Respon_keranjang{" +
                        "menu = '" + menu+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
