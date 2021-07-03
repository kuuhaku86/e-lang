package com.example.e_lang.DataSource;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth {
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("auth", Context.MODE_PRIVATE);
    }
}
