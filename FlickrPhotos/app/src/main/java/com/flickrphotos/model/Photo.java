package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.flickrphotos.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    @SerializedName("description")
    private Description description;
    @SerializedName("datetaken")
    private String dateTaken;
    @SerializedName("ownername")
    private String ownerName;
    @SerializedName("iconserver")
    private String iconServer;
    @SerializedName("iconfarm")
    private Integer iconFarm;

    public final static Parcelable.Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            Photo instance = new Photo();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.owner = ((String) in.readValue((String.class.getClassLoader())));
            instance.secret = ((String) in.readValue((String.class.getClassLoader())));
            instance.server = ((String) in.readValue((String.class.getClassLoader())));
            instance.farm = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((Description) in.readValue((Description.class.getClassLoader())));
            instance.dateTaken = ((String) in.readValue((String.class.getClassLoader())));
            instance.ownerName = ((String) in.readValue((String.class.getClassLoader())));
            instance.iconServer = ((String) in.readValue((String.class.getClassLoader())));
            instance.iconFarm = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public Photo[] newArray(int size) {
            return (new Photo[size]);
        }
    };

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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIconServer() {
        return iconServer;
    }

    public void setIconserver(String iconServer) {
        this.iconServer = iconServer;
    }

    public Integer getIconFarm() {
        return iconFarm;
    }

    public void setIconfarm(Integer iconFarm) {
        this.iconFarm = iconFarm;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(owner);
        dest.writeValue(secret);
        dest.writeValue(server);
        dest.writeValue(farm);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(dateTaken);
        dest.writeValue(ownerName);
        dest.writeValue(iconServer);
        dest.writeValue(iconFarm);
    }

    public int describeContents() {
        return 0;
    }

    public String getUrlImageSource() {
        return String.format(Constants.URL_PHOTO_SOURCE_FORMAT, farm, server, id, secret);
    }

    public String getUrlUserImageSource() {
        return String.format(Constants.URL_USER_PHOTO_SOURCE_FORMAT, iconFarm, iconServer, owner);
    }

    public String getFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateTaken);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String monthString = new DateFormatSymbols().getMonths()[month];
            return monthString.substring(0,3) + " " + String.valueOf(day);
        } catch (ParseException ex) {
            return "";
        }
    }
}
