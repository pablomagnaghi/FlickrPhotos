package com.flickrphotos.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flickrphotos.R;
import com.flickrphotos.model.Location;
import com.flickrphotos.model.Photo;
import com.flickrphotos.presenter.PhotoInfoPresenter;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.utils.DialogFactory;
import com.flickrphotos.view.PhotoInfoMvpView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pablo on 12/2/2017.
 */
public class PhotoInfoActivity extends BaseActivity implements PhotoInfoMvpView {

    PhotoInfoPresenter mPhotoInfoPresenter;
    Photo mPhoto;
    Location mLocation;

    @BindView(R.id.iv_photo)
    ImageView photoImageView;
    @BindView(R.id.iv_user_photo)
    CircleImageView userPhotoImageView;
    @BindView(R.id.tv_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_title)
    TextView titleTextView;
    @BindView(R.id.tv_description)
    TextView descriptionTextView;
    @BindView(R.id.tv_date)
    TextView dateTextView;
    @BindView(R.id.tv_location)
    TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);
        injectView();

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPhotoInfoPresenter = new PhotoInfoPresenter();
        mPhotoInfoPresenter.attachView(this);

        mPhoto = (Photo) getIntent().getParcelableExtra(Constants.PHOTO_INTENT);
        showPhotoInfo();

        photoImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoCommentsActivity.class);
                intent.putExtra(Constants.PHOTO_INTENT, mPhoto);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            mLocation = savedInstanceState.getParcelable(Constants.PHOTO_INFO);
        }

        if (mLocation != null) {
            showPhotoLocation(mLocation);
        }
        else {
            try {
                mPhotoInfoPresenter.loadLocation(mPhoto.getId());
            }
            catch (Exception e)
            {
                showError();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.PHOTO_INFO, mLocation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoInfoPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void showPhotoInfo() {
        Glide.with(this)
                .load(mPhoto.getUrlImageSource())
                .placeholder(R.drawable.default_photo)
                .error(R.drawable.default_photo)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(photoImageView);

        Glide.with(this)
                .load(mPhoto.getUrlUserImageSource())
                .placeholder(R.drawable.default_user_photo)
                .error(R.drawable.default_user_photo)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(userPhotoImageView);

        userNameTextView.setText(mPhoto.getOwnerName());
        titleTextView.setText(mPhoto.getTitle());
        dateTextView.setText(mPhoto.getFormatDate());
        descriptionTextView.setClickable(true);
        descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
        descriptionTextView.setText(Html.fromHtml(mPhoto.getDescription().getContent()));

    }

    /** MVP View methods implementation **/

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_location))
                .show();
    }

    @Override
    public void showPhotoLocation(Location location) {
        try {
            if (location.getDescription().length() > 0) {
                locationTextView.setVisibility(View.VISIBLE);
                locationTextView.setText(location.getDescription());
                mLocation = location;
            }
            else {
                locationTextView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            locationTextView.setVisibility(View.GONE);
        }
    }
}
