package com.example.buper.Response;

import com.example.buper.Item.item_gallery;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Respnse_gallery {
    @SerializedName("gallery")
    private List<item_gallery> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<item_gallery>menu) {
        this.menu = menu;
    }

    public List<item_gallery> getMenu()
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
