package com.flickrphotos.restServices;

import com.flickrphotos.model.CommentsResponse;
import com.flickrphotos.model.PhotoInfoResponse;
import com.flickrphotos.model.PhotosResponse;
import com.flickrphotos.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Pablo on 12/2/2017.
 */
public interface FlickrService {

    @GET(Constants.FLICKR_API_GET_RECENT)
    Observable<PhotosResponse> getRecentPhotos(
        @Query("per_page") int perPage,
        @Query("page") int page
    );

    @GET(Constants.FLICKR_API_SEARCH_TEXT)
    Observable<PhotosResponse> getPhotosByText(
            @Query("per_page") int perPage,
            @Query("page") int page,
            @Query("text") String text
    );

    @GET(Constants.FLICKR_API_GET_INFO )
    Observable<PhotoInfoResponse> getPhotoInfo(
            @Query("photo_id") String photoId
    );

    @GET(Constants.FLICKR_API_GET_COMMENTS )
    Observable<CommentsResponse> getPhotoComments(
            @Query("photo_id") String photoId
    );

    class Creator {

        public static FlickrService newFlickrService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.FLICKR_API)
                    .build();
            return retrofit.create(FlickrService.class);
        }
    }
}
