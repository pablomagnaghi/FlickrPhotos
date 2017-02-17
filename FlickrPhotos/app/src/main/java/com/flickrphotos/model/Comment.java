package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 16/2/2017.
 */
public class Comment implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("author")
    private String author;
    @SerializedName("path_alias")
    private String pathAlias;
    @SerializedName("realname")
    private String realName;
    @SerializedName("_content")
    private String content;

    public final static Parcelable.Creator<Comment> CREATOR = new Creator<Comment>() {

        public Comment createFromParcel(Parcel in) {
            Comment instance = new Comment();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.author = ((String) in.readValue((String.class.getClassLoader())));
            instance.pathAlias = ((String) in.readValue((String.class.getClassLoader())));
            instance.realName = ((String) in.readValue((String.class.getClassLoader())));
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Comment[] newArray(int size) {
            return (new Comment[size]);
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPathAlias() {
        return pathAlias;
    }

    public void setPathAlias(String pathAlias) {
        this.pathAlias = pathAlias;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(author);
        dest.writeValue(pathAlias);
        dest.writeValue(realName);
        dest.writeValue(content);
    }

    public int describeContents() {
        return 0;
    }

}
