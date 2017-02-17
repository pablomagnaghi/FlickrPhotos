package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pablo on 16/2/2017.
 */
public class Comments implements Parcelable {

    @SerializedName("photo_id")
    private String photoId;
    @SerializedName("comment")
    private List<Comment> comment = null;

    public final static Parcelable.Creator<Comments> CREATOR = new Creator<Comments>() {
        public Comments createFromParcel(Parcel in) {
            Comments instance = new Comments();
            instance.photoId = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.comment, (Comment.class.getClassLoader()));
            return instance;
        }

        public Comments[] newArray(int size) {
            return (new Comments[size]);
        }
    };

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(photoId);
        dest.writeList(comment);
    }

    public int describeContents() {
        return 0;
    }

    public String getCountComments() {
        return String.valueOf(comment.size()) + " comments";
    }

}
