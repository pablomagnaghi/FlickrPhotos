package com.flickrphotos;

import android.app.Application;

import com.flickrphotos.injection.components.ApplicationComponent;
import com.flickrphotos.injection.components.DaggerApplicationComponent;
import com.flickrphotos.injection.modules.ApplicationModule;

/**
 * Created by Pablo on 19/2/2017.
 */
public class FlickrApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}