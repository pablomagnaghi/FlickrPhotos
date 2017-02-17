package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class Location implements Parcelable
{
    @SerializedName("locality")
    private Locality locality;
    @SerializedName("county")
    private County county;

    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {

        public Location createFromParcel(Parcel in) {
            Location instance = new Location();
            instance.locality = ((Locality) in.readValue((Locality.class.getClassLoader())));
            instance.county = ((County) in.readValue((County.class.getClassLoader())));
            return instance;
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    };

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(locality);
        dest.writeValue(county);
    }

    public int describeContents() {
        return 0;
    }

    public String getDescription() {
        if (locality.getContent().length() > 0 && county.getContent().length() > 0) {
            return locality.getContent() + ", " + county.getContent();
        }
        else return "";
    }
}