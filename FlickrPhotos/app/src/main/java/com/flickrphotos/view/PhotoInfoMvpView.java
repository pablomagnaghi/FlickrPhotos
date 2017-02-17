package com.flickrphotos.view;

import com.flickrphotos.model.Location;

/**
 * Created by Pablo on 12/2/2017.
 */
public interface PhotoInfoMvpView extends MvpView {
    void showPhotoLocation(Location location);

}
