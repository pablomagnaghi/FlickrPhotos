package com.flickrphotos.utils;

/**
 * Created by Pablo on 12/2/2017.
 */
public class Constants {
    public final static String API_KEY = "&api_key=d8fc9bcba69a17c05f8da79b4e7531c9";
    public final static String FLICKR_API = "https://api.flickr.com";

    public static final String FORMAT_JSON = "&format=json&nojsoncallback=1";
    public static final String EXTRAS = "&extras=description%2Cowner_name%2Cicon_server%2Cdate_taken";

    public final static String FLICKR_API_GET_RECENT = "/services/rest/?method=flickr.photos.getRecent" + API_KEY + EXTRAS + FORMAT_JSON;
    public final static String FLICKR_API_SEARCH_TEXT = "/services/rest/?method=flickr.photos.search" + API_KEY + EXTRAS + FORMAT_JSON;
    public static final String FLICKR_API_GET_INFO = "/services/rest/?method=flickr.photos.getInfo" + API_KEY + FORMAT_JSON ;

    public static final String FLICKR_API_GET_COMMENTS = "/services/rest/?method=flickr.photos.comments.getList" + API_KEY + FORMAT_JSON ;

    public final static String URL_PHOTO_SOURCE_FORMAT = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s_m.jpg";
    public final static String URL_USER_PHOTO_SOURCE_FORMAT = "http://farm%1$s.staticflickr.com/%2$s/buddyicons/%3$s.jpg";

    public final static int GRID_SPAN_COUNT = 3;
    public final static int LIST_SPAN_COUNT = 1;

    public final static int PER_PAGE = 18;
    public final static String PHOTO_LIST = "PhotoList";
    public final static int GRID_SPAN = 3;
    public final static int THRESHOLD = 3;

    public final static String PHOTO_INTENT = "PHOTO_INTENT";

    public static final String PHOTO_INFO = "PHOTO_INFO";
    public static final int GRID_MODE = 1;
    public static final int LIST_MODE = 2;

    public static final String PHOTO_COMMENTS = "PHOTO_COMMENTS";
}

