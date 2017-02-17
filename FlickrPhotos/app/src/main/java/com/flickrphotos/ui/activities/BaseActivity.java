package com.flickrphotos.ui.activities;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity {

    protected void injectView()
    {
        ButterKnife.bind(this);
    }

}
