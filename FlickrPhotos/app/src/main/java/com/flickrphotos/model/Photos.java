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
    private Integer perpage;
    @SerializedName("total")
    private Integer total;
    @SerializedName("photo")
    private List<Photo> photos = null;

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

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
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


    public Photos(Parcel parcel) {
        super();
        page = parcel.readInt();
        pages = parcel.readInt();
        perpage = parcel.readInt();
        total = parcel.readInt();
        photos =  parcel.readArrayList(null);
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(page);
        parcel.writeValue(pages);
        parcel.writeValue(perpage);
        parcel.writeValue(total);
        parcel.writeList(photos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Method to recreate a Question from a Parcel
    public static Creator<Photos> CREATOR = new Creator<Photos>() {

        @Override
        public Photos createFromParcel(Parcel source) {
            return new Photos(source);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }

    };

}





