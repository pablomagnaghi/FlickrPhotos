package com.flickrphotos.view;

import com.flickrphotos.model.Photos;

public interface MainMvpView extends MvpView {

    void showPhotos(Photos photos);

    void showPhotosEmpty();

    void showError();

}
