package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 12/2/2017.
 */
public class PhotosResponse implements Parcelable {
    @SerializedName("photos")
    private Photos photosList;
    @SerializedName("stat")
    private String stat;

    public final static Parcelable.Creator<PhotosResponse> CREATOR = new Creator<PhotosResponse>() {
        public PhotosResponse createFromParcel(Parcel in) {
            PhotosResponse instance = new PhotosResponse();
            instance.photosList = ((Photos) in.readValue((Photos.class.getClassLoader())));
            instance.stat = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public PhotosResponse[] newArray(int size) {
            return (new PhotosResponse[size]);
        }
    };

    public Photos getPhotosList() {
        return photosList;
    }

    public void setPhotosList(Photos photosList) {
        this.photosList = photosList;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(photosList);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return 0;
    }

}