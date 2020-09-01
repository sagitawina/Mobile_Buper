package com.example.buper.Response;

import com.example.buper.Item.Item_gedung;
import com.example.buper.Item.item_gallery;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Response_Gedun {
    @SerializedName("fasilitas")
    private List<Item_gedung> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<Item_gedung>menu) {
        this.menu = menu;
    }

    public List<Item_gedung> getMenu()
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
                "Response_Menu{" +
                        "menu = '" + menu+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
