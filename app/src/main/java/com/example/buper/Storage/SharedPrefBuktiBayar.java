package com.example.buper.Storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefBuktiBayar {
    public static final String SP_LOGIN_APP = "spbuper";
    public static final String SP_ID = "spid";
    public static final String SP_BUKTIBAYAR = "spBuktiBayar";
    public static final String SP_SUDAH_LOGIN = "SudahLogin";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefBuktiBayar(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpId(){
        return sp.getString(SP_ID, "");
    }

    public String getSPNama(){
        return sp.getString(SP_BUKTIBAYAR, "");
    }

    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
