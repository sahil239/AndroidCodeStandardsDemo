package com.androidimptchanges;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by android on 8/14/2017.
 */

@Module
public class BaseModule {

    private final BaseApplication application;

    public BaseModule(BaseApplication app) {
        this.application = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context app) {
        return app.getSharedPreferences("My_Prefs_Title", Context.MODE_PRIVATE);
    }

    @Provides
    Resources providesResources(){
        return application.getResources();
    }
}
