package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 14/2/2017.
 */
public class Description implements Parcelable {
    @SerializedName("_content")
    private String content;
    public final static Parcelable.Creator<Description> CREATOR = new Creator<Description>() {

        public Description createFromParcel(Parcel in) {
            Description instance = new Description();
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Description[] newArray(int size) {
            return (new Description[size]);
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