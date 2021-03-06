package com.flickrphotos.presenter;

import com.flickrphotos.model.PhotoInfoResponse;
import com.flickrphotos.restServices.FlickrService;
import com.flickrphotos.view.PhotoInfoMvpView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pablo on 12/2/2017.
 */
public class PhotoInfoPresenter extends BasePresenter<PhotoInfoMvpView> {

    FlickrService mFlickService;

    @Inject
    public PhotoInfoPresenter(FlickrService flickrService) {
        mFlickService = flickrService;
    }

    @Override
    public void attachView(PhotoInfoMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadLocation(String photoId ) {
        checkViewAttached();

        Observable<PhotoInfoResponse> photosObservable = mFlickService.getPhotoInfo(photoId);

        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoInfoResponse>() {
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
                    public void onNext(PhotoInfoResponse photosResponse) {
                        try {
                            getMvpView().showPhotoLocation(photosResponse.getPhotoInfo().getLocation());
                        } catch (Exception exc) {
                            getMvpView().showError();
                        }
                    }
                });
    }
}
