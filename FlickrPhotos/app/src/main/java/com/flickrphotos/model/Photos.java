package com.flickrphotos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pablo on 12/2/2017.
 */
public class Photos implements Parcelable {
    @SerializedName("page")
    private Integer page;
    @SerializedName("pages")
    private Integer pages;
    @SerializedName("perpage")
    private Integer perPage;
    @SerializedName("total")
    private Integer total;
    @SerializedName("photo")
    private List<Photo> photos = null;

    public final static Parcelable.Creator<Photos> CREATOR = new Creator<Photos>() {
        public Photos createFromParcel(Parcel in) {
            Photos instance = new Photos();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.pages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.photos, (Photo.class.getClassLoader()));
            return instance;
        }
        public Photos[] newArray(int size) {
            return (new Photos[size]);
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(pages);
        dest.writeValue(perPage);
        dest.writeValue(total);
        dest.writeList(photos);
    }

    public int describeContents() {
        return 0;
    }

}




