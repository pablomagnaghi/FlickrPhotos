package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class PhotoInfoResponse implements Parcelable {
    @SerializedName("photo")
    private PhotoInfo photoInfo;
    @SerializedName("stat")
    private String stat;
    public final static Parcelable.Creator<PhotoInfoResponse> CREATOR = new Creator<PhotoInfoResponse>() {
        public PhotoInfoResponse createFromParcel(Parcel in) {
            PhotoInfoResponse instance = new PhotoInfoResponse();
            instance.photoInfo = ((PhotoInfo) in.readValue((Photo.class.getClassLoader())));
            instance.stat = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }
        public PhotoInfoResponse[] newArray(int size) {
            return (new PhotoInfoResponse[size]);
        }
    };

    public PhotoInfo getPhotoInfo() {
        return photoInfo;
    }

    public void setPhotoInfo(PhotoInfo photoInfo) {
        this.photoInfo = photoInfo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(photoInfo);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return 0;
    }

}
