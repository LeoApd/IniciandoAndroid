package com.example.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mshaSharedPreferences;

    public SecurityPreferences(Context mContext) {

        //pega uma instancia do shared preferences
        //ngm fora da aplicação(FestaFimAno) tem acesso ao o shared prefereces
        this.mshaSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);

    }

    public void storeString(String key, String value) {
        this.mshaSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoreString(String key) {
        return this.mshaSharedPreferences.getString(key, "");
    }


}
