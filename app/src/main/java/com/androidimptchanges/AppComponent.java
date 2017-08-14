package com.androidimptchanges;

import com.androidimptchanges.activities.MainActivity;
import com.androidimptchanges.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by android on 8/14/2017.
 */

@Singleton @Component(modules = {BaseModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    void inject(MainActivity mainActivity);

    void inject(BaseActivity baseActivity);
}
