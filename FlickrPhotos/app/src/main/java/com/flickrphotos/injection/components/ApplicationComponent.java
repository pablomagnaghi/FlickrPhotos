package com.flickrphotos.injection.components;

import com.flickrphotos.injection.modules.ApplicationModule;
import com.flickrphotos.ui.activities.MainActivity;
import com.flickrphotos.ui.activities.PhotoCommentsActivity;
import com.flickrphotos.ui.activities.PhotoInfoActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(PhotoInfoActivity photoInfoActivity);
    void inject(PhotoCommentsActivity photoCommentsActivity);

}
