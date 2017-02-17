package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class PhotoInfo implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("location")
    private Location location;

    public final static Parcelable.Creator<PhotoInfo> CREATOR = new Creator<PhotoInfo>() {

        public PhotoInfo createFromParcel(Parcel in) {
            PhotoInfo instance = new PhotoInfo();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            return instance;
        }

        public PhotoInfo[] newArray(int size) {
            return (new PhotoInfo[size]);
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(location);
    }

    public int describeContents() {
        return 0;
    }

}