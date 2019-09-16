package com.suncaption.kpoptubemy;

import android.app.Application;

import io.realm.Realm;

public class MyApp extends Application {
    private static MyApp appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        Realm.init(this);
    }

    public static MyApp getInstance() {
        return appInstance;
    }
}
