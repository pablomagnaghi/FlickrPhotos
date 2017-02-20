package com.flickrphotos.injection.modules;

import android.app.Application;

import com.flickrphotos.restServices.FlickrService;
import com.flickrphotos.ui.adapters.PhotosAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pablo on 19/2/2017.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    FlickrService provideFlickService() {
        return FlickrService.Creator.newFlickrService();
    }

    @Provides
    @Singleton
    PhotosAdapter providePhotosAdapter() {
        return new PhotosAdapter(mApplication.getApplicationContext());
    }
}
