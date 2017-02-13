package com.flickrphotos.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flickrphotos.R;
import com.flickrphotos.model.Photo;
import com.flickrphotos.ui.listener.RecyclerOnItemClickListener;
import com.flickrphotos.ui.activities.PhotoInfoActivity;
import com.flickrphotos.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pablo on 12/2/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> implements RecyclerOnItemClickListener {

    private List<Photo> mPhotos;

    private Context mContext;

    public PhotosAdapter(Context context){
        mContext = context;
        mPhotos = new ArrayList<>();
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void addPhotos(List<Photo> photos) {
        int previousSize = mPhotos.size();
        mPhotos.addAll(photos);
        notifyItemRangeInserted(previousSize, photos.size());
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        final Photo photo = mPhotos.get(position);

        Glide.with(mContext)
                .load(photo.getUrlImageSource())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.size() : 0;
    }

    @Override
    public void onItemClicked(View view, int position) {

        Photo photo = mPhotos.get(position);
        Intent intent = new Intent(mContext, PhotoInfoActivity.class);
        intent.putExtra(Constants.PHOTO_INTENT, photo);
        mContext.startActivity(intent);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView photoImageView;

        public PhotoViewHolder(View itemView, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnItemClickListener != null) {
                        recyclerOnItemClickListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
