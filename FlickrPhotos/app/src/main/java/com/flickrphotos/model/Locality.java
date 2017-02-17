package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class Locality implements Parcelable {

    @SerializedName("_content")
    private String content;

    public final static Parcelable.Creator<Locality> CREATOR = new Creator<Locality>() {
        public Locality createFromParcel(Parcel in) {
            Locality instance = new Locality();
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Locality[] newArray(int size) {
            return (new Locality[size]);
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