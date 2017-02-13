package com.flickrphotos.restServices;

import com.flickrphotos.model.PhotosResponse;
import com.flickrphotos.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Pablo on 12/2/2017.
 */
public interface FlickrService {

    @GET(Constants.FLICKR_API_GET_RECENT_PHOTOS )
    Observable<PhotosResponse> getRecentPhotos(
        @Query("per_page") int perPage,
        @Query("page") int page
    );
}


