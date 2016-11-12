package com.androidimptchanges.activities;

import android.app.Application;

/**
 * Created by android on 11/11/2016.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}