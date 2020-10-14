package com.github.josmilan.myapplication;

import android.app.Application;

import com.github.josmilan.myapplication.utils.PreferenceUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(getApplicationContext());
    }
}
