package com.flickrphotos.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flickrphotos.R;
import com.flickrphotos.model.Comments;
import com.flickrphotos.model.Photo;
import com.flickrphotos.presenter.PhotoCommentsPresenter;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.utils.DialogFactory;
import com.flickrphotos.view.PhotoCommentsMvpView;

import butterknife.BindView;

public class PhotoCommentsActivity extends BaseActivity implements PhotoCommentsMvpView {

    PhotoCommentsPresenter mPhotoCommentsPresenter;

    Photo mPhoto;
    Comments mComments;

    @BindView(R.id.iv_photo)
    ImageView photoImageView;
    @BindView(R.id.tv_user_name)
    TextView userNameTextView;
    @BindView(R.id.tv_title)
    TextView titleTextView;
    @BindView(R.id.tv_count_comments)
    TextView countCommentsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_comments);

        injectView();

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        mPhotoCommentsPresenter = new PhotoCommentsPresenter();
        mPhotoCommentsPresenter.attachView(this);

        mPhoto = (Photo) getIntent().getParcelableExtra(Constants.PHOTO_INTENT);
        showPhotoInfo();

        if (savedInstanceState != null) {
            mComments = savedInstanceState.getParcelable(Constants.PHOTO_COMMENTS);
        }

        if (mComments != null) {
            showPhotoComments(mComments);
        }
        else {
            try {
                mPhotoCommentsPresenter.loadComments(mPhoto.getId());
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
        outState.putParcelable(Constants.PHOTO_COMMENTS, mComments);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoCommentsPresenter.detachView();
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

        userNameTextView.setText(mPhoto.getOwnerName());
        titleTextView.setText(mPhoto.getTitle());
    }

    /** MVP View methods implementation **/

    @Override
    public void showPhotoComments(Comments comments) {
        try {
            if (comments.getComment().size() > 0) {
                countCommentsTextView.setText(comments.getCountComments());
                mComments = comments;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_comments))
                .show();
    }
}
