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

    public Photos getPhotosList() {
        return photosList;
    }

    public void setPhotosList(Photos photos) {
        this.photosList = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public PhotosResponse(Parcel parcel) {
        super();
        photosList = ((Photos) parcel.readValue((Photos.class.getClassLoader())));
        stat = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(photosList);
        parcel.writeValue(stat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Method to recreate a Question from a Parcel
    public static Creator<PhotosResponse> CREATOR = new Creator<PhotosResponse>() {

        @Override
        public PhotosResponse createFromParcel(Parcel source) {
            return new PhotosResponse(source);
        }

        @Override
        public PhotosResponse[] newArray(int size) {
            return new PhotosResponse[size];
        }

    };
}
