package com.flickrphotos.restServices;

import com.flickrphotos.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pablo on 17/2/2017.
 */
public class FlickrAdapter {

    private static FlickrService flickrService = null;

    public static FlickrService newFlickrService() {
        if (flickrService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.FLICKR_API)
                    .build();
            return retrofit.create(FlickrService.class);
        }
        return flickrService;
    }
}