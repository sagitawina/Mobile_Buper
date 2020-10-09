package com.example.buper.Response;

import com.example.buper.Item.Item_Transaksi;
import com.example.buper.Item.item_keranjang;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Respon_Transaksi {
    @SerializedName("transaksi")
    private List<Item_Transaksi> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<Item_Transaksi>menu) {
        this.menu = menu;
    }

    public List<Item_Transaksi> getMenu()
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
                "Response_Transaksi{" +
                        "menu = '" + menu+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
