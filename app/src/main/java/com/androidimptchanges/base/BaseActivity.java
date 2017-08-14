package com.androidimptchanges.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidimptchanges.BaseApplication;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject public SharedPreferences sp;
    @Inject public Context context;
    @Inject public Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication)getApplication()).getAppComponent().inject(this);
    }

}
