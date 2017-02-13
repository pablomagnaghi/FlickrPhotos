package com.flickrphotos.presenter;

import com.flickrphotos.model.PhotosResponse;
import com.flickrphotos.restServices.FlickrService;
import com.flickrphotos.view.MainMvpView;
import com.flickrphotos.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    public void loadPhotos(int currentPage) {
        checkViewAttached();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.FLICKR_API)
                .build();

        FlickrService flickrService = retrofit.create(FlickrService.class);
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
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(PhotosResponse photosResponse) {
                        if (photosResponse.getPhotosList().getPhotos().isEmpty()) {
                            getMvpView().showPhotosEmpty();
                        } else {
                            getMvpView().showPhotos(photosResponse.getPhotosList());
                        }
                    }
                });
    }

}
