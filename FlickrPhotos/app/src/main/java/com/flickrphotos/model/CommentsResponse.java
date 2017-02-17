package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class CommentsResponse implements Parcelable
{
    @SerializedName("comments")
    private Comments comments;
    @SerializedName("stat")
    private String stat;

    public final static Parcelable.Creator<CommentsResponse> CREATOR = new Creator<CommentsResponse>() {

        public CommentsResponse createFromParcel(Parcel in) {
            CommentsResponse instance = new CommentsResponse();
            instance.comments = ((Comments) in.readValue((Comments.class.getClassLoader())));
            instance.stat = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CommentsResponse[] newArray(int size) {
            return (new CommentsResponse[size]);
        }

    };

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(comments);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return 0;
    }

}
