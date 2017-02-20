package com.flickrphotos.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flickrphotos.R;
import com.flickrphotos.model.Photo;
import com.flickrphotos.ui.activities.PhotoInfoActivity;
import com.flickrphotos.ui.listener.RecyclerOnItemClickListener;
import com.flickrphotos.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Pablo on 12/2/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerOnItemClickListener {

    private List<Photo> mPhotos;

    private Context mContext;

    private int mMode;

    public PhotosAdapter(Context context){
        mContext = context;
        mPhotos = new ArrayList<>();
        mMode = Constants.GRID_MODE;
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

    public void setMode(int mode) {
        mMode = mode;
    }

    @Override
    public int getItemViewType(int position) {
        return mMode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case Constants.LIST_MODE:
                View itemListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_photo, parent, false);
                return new PhotoListViewHolder(itemListView, this);
            default:
                View itemGridView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_grid_photo, parent, false);
                return new PhotoGridViewHolder(itemGridView, this);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Photo photo = mPhotos.get(position);

        switch (holder.getItemViewType()) {
            case Constants.LIST_MODE:
                PhotoListViewHolder photoListViewHolder = (PhotoListViewHolder)holder;
                Glide.with(mContext)
                        .load(photo.getUrlImageSource())
                        .placeholder(R.drawable.default_photo)
                        .error(R.drawable.default_photo)
                        .fitCenter()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(photoListViewHolder.photoImageView);

                Glide.with(mContext)
                        .load(photo.getUrlUserImageSource())
                        .placeholder(R.drawable.default_user_photo)
                        .error(R.drawable.default_user_photo)
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(photoListViewHolder .userPhotoImageView);

                photoListViewHolder.userNameTextView.setText(photo.getOwnerName());
                photoListViewHolder.titleTextView.setText(photo.getTitle());
                photoListViewHolder.dateTextView.setText(photo.getFormatDate());
                break;
            default:
            case Constants.GRID_MODE:
                PhotoGridViewHolder photoGridViewHolder= (PhotoGridViewHolder)holder;
                Glide.with(mContext)
                        .load(photo.getUrlImageSource())
                        .placeholder(R.drawable.default_photo)
                        .error(R.drawable.default_photo)
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(photoGridViewHolder.photoImageView);
                break;
        }
    }

    public void setGridMode() {

    }

    public void setListMode() {

    }

    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.size() : 0;
    }

    @Override
    public void onItemClicked(View view, int position) {

        if (mPhotos.size() > position) {
            Photo photo = mPhotos.get(position);
            Intent intent = new Intent(mContext, PhotoInfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constants.PHOTO_INTENT, photo);
            mContext.startActivity(intent);
        }
    }

    class PhotoGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView photoImageView;

        public PhotoGridViewHolder(View itemView, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
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

    class PhotoListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView photoImageView;
        @BindView(R.id.iv_user_photo)
        CircleImageView userPhotoImageView;
        @BindView(R.id.tv_user_name)
        TextView userNameTextView;
        @BindView(R.id.tv_title)
        TextView titleTextView;
        @BindView(R.id.tv_date)
        TextView dateTextView;

        public PhotoListViewHolder(View itemView, final RecyclerOnItemClickListener recyclerOnItemClickListener) {
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
