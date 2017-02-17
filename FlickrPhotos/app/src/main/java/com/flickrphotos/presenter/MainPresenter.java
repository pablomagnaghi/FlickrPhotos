package com.flickrphotos.presenter;

import com.flickrphotos.model.PhotosResponse;
import com.flickrphotos.restServices.FlickrService;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.view.MainMvpView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Pablo on 12/2/2017.
 */
public class MainPresenter extends BasePresenter<MainMvpView> {

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadRecentPhotos(int currentPage) {
        checkViewAttached();

        FlickrService flickrService = FlickrService.Creator.newFlickrService();
        Observable<PhotosResponse> photosObservable = flickrService.getRecentPhotos(Constants.PER_PAGE,
                currentPage);

        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotosResponse>() {
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
                    public void onNext(PhotosResponse photosResponse) {
                        try
                        {
                            if (photosResponse.getPhotosList().getPhotos().isEmpty()) {
                                getMvpView().showPhotosEmpty();
                            } else {
                                getMvpView().showPhotos(photosResponse.getPhotosList());
                            }
                        }
                        catch (Exception exc) {
                            getMvpView().showError();
                        }
                    }
                });
    }

    public void searchPhotosByText(int currentPage, String text) {
        checkViewAttached();

        FlickrService flickrService = FlickrService.Creator.newFlickrService();
        Observable<PhotosResponse> photosObservable = flickrService.getPhotosByText(Constants.PER_PAGE,
                currentPage, text);

        photosObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotosResponse>() {
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
                    public void onNext(PhotosResponse photosResponse) {
                        try {
                            if (photosResponse.getPhotosList().getPhotos().isEmpty()) {
                                getMvpView().showPhotosEmpty();
                            } else {
                                getMvpView().showPhotos(photosResponse.getPhotosList());
                            }
                        }
                        catch (Exception exc) {
                            getMvpView().showError();
                        }
                    }
                });
    }

}
