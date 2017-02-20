package com.flickrphotos.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flickrphotos.FlickrApplication;
import com.flickrphotos.injection.components.ApplicationComponent;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((FlickrApplication) getApplication()).getApplicationComponent();
    }

}
