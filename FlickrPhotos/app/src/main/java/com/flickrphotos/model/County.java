package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class County implements Parcelable {

    @SerializedName("_content")
    private String content;

    public final static Parcelable.Creator<County> CREATOR = new Creator<County>() {

        public County createFromParcel(Parcel in) {
            County instance = new County();
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public County[] newArray(int size) {
            return (new County[size]);
        }

    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(content);
    }

    public int describeContents() {
        return 0;
    }

}