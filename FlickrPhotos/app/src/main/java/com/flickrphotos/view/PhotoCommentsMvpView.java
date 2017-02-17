package com.flickrphotos.view;

import com.flickrphotos.model.Comments;

/**
 * Created by Pablo on 16/2/2017.
 */
public interface PhotoCommentsMvpView extends MvpView {
    void showPhotoComments(Comments comments);
}
