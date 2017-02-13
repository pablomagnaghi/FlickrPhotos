package com.flickrphotos.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.flickrphotos.R;
import com.flickrphotos.model.Photo;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.view.PhotoInfoMvpView;

import butterknife.BindView;

/**
 * Created by Pablo on 12/2/2017.
 */
public class PhotoInfoActivity extends BaseActivity implements PhotoInfoMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);
        injectView();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Photo photo = (Photo) getIntent().getParcelableExtra(Constants.PHOTO_INTENT);

        int a = 0;
    }
}
