package com.androidimptchanges;

import android.app.Application;

/**
 * Created by android on 8/14/2017.
 */

public class BaseApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().baseModule(new BaseModule(this)).build();

        appComponent.inject(this);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
