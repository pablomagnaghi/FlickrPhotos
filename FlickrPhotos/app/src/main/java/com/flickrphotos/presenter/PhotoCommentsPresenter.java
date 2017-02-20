package com.flickrphotos.presenter;

import com.flickrphotos.model.CommentsResponse;
import com.flickrphotos.restServices.FlickrService;
import com.flickrphotos.view.PhotoCommentsMvpView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pablo on 16/2/2017.
 */
public class PhotoCommentsPresenter extends BasePresenter<PhotoCommentsMvpView> {

    FlickrService mFlickService;

    @Inject
    public PhotoCommentsPresenter(FlickrService flickrService) {
        mFlickService = flickrService;
    }

    @Override
    public void attachView(PhotoCommentsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadComments(String photoId ) {
        checkViewAttached();

        Observable<CommentsResponse> photoCommentsObservable = mFlickService.getPhotoComments(photoId);

        photoCommentsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getMvpView().showError();
                        }
                    }

                    @Override
                    public void onNext(CommentsResponse commentsResponse) {
                        try {
                            getMvpView().showPhotoComments(commentsResponse.getComments());
                        } catch (Exception exc) {
                            getMvpView().showError();
                        }
                    }
                });
    }
}
