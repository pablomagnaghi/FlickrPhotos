package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.flickrphotos.utils.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 12/2/2017.
 */
public class Photo implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private Integer farm;
    @SerializedName("title")
    private String title;
    @SerializedName("ispublic")
    private Integer ispublic;
    @SerializedName("isfriend")
    private Integer isfriend;
    @SerializedName("isfamily")
    private Integer isfamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public Integer getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    public String getUrlImageSource() {
        return String.format(Constants.URL_PHOTO_SOURCE_FORMAT, getFarm(), getServer(), getId(), getSecret());
    }

    public Photo(Parcel parcel) {
        super();
        id = parcel.readString();
        owner = parcel.readString();
        secret = parcel.readString();
        server = parcel.readString();
        farm = parcel.readInt();
        title = parcel.readString();
        ispublic = parcel.readInt();
        isfriend = parcel.readInt();
        isfamily = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(id);
        parcel.writeValue(owner);
        parcel.writeValue(secret);
        parcel.writeValue(server);
        parcel.writeValue(farm);
        parcel.writeValue(title);
        parcel.writeValue(ispublic);
        parcel.writeValue(isfriend);
        parcel.writeValue(isfamily);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Method to recreate a Question from a Parcel
    public static Creator<Photo> CREATOR = new Creator<Photo>() {

        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }

    };
}
