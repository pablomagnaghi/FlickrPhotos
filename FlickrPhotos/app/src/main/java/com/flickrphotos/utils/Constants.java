package com.flickrphotos.utils;

/**
 * Created by Pablo on 12/2/2017.
 */
public class Constants {
    public final static String API_KEY = "d8fc9bcba69a17c05f8da79b4e7531c9";
    public final static String FLICKR_API = "https://api.flickr.com";
    public final static String FLICKR_API_GET_RECENT_PHOTOS = "/services/rest/?method=flickr.photos.getRecent&api_key=" + API_KEY + "&format=json&nojsoncallback=1";
    public final static String URL_PHOTO_SOURCE_FORMAT = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s.jpg";

    public final static int GRID_SPAN_COUNT = 3;
    public final static int LIST_SPAN_COUNT = 1;

    public final static int PER_PAGE = 18;
    public final static String PHOTO_LIST = "PhotoList";
    public final static int GRID_SPAN = 3;
    public final static int THRESHOLD = 3;

    public final static String PHOTO_INTENT = "PHOTO_INTENT";
}
