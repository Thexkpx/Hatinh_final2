package com.example.mikasaloli.myapplication;

import android.app.Application;
import android.content.Context;

public class ct extends App {
    public static Context context;

    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getcontext(){
        return context;
    }

}
