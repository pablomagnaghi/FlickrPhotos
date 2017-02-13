package com.flickrphotos.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.flickrphotos.R;
import com.flickrphotos.model.Photo;
import com.flickrphotos.model.Photos;
import com.flickrphotos.presenter.MainPresenter;
import com.flickrphotos.ui.adapters.PhotosAdapter;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.utils.DialogFactory;
import com.flickrphotos.view.MainMvpView;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import java.util.Collections;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Pablo on 12/2/2017.
 */
public class MainActivity extends BaseActivity implements MainMvpView, Paginate.Callbacks {

    //@Inject
    //MainPresenter mMainPresenter;
    //@Inject RibotsAdapter mRibotsAdapter;

    MainPresenter mMainPresenter;
    PhotosAdapter mPhotosAdapter;

    private boolean loading = false;
    private Paginate paginate;
    private Photos mPhotos;
    private int currentPage = 1;

    private Menu mMenu;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        injectView();

        mPhotosAdapter = new PhotosAdapter(this);
        mRecyclerView.setAdapter(mPhotosAdapter);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);

        if (savedInstanceState != null) {
            mPhotos = savedInstanceState.getParcelable(Constants.PHOTO_LIST);
        }

        if (mPhotos != null) {
            if (mPhotos.getPhotos().isEmpty()) {
                showPhotosEmpty();
            } else {
                showPhotos(mPhotos);
            }
        }
        else {
            mMainPresenter.loadPhotos(currentPage);
        }

        if (paginate != null) {
            paginate.unbind();
        }

        paginate = Paginate.with(mRecyclerView, this)
                .setLoadingTriggerThreshold(Constants.THRESHOLD)
                .addLoadingListItem(true)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return Constants.GRID_SPAN;
                    }
                })
                .build();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Photos photos = mPhotos;
        photos.setPhotos(mPhotosAdapter.getPhotos());
        outState.putParcelable(Constants.PHOTO_LIST, photos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.i_grid) {
            if(mMenu != null) {
                MenuItem listMenuItem = mMenu.findItem(R.id.i_list);
                listMenuItem.setVisible(true);
                item.setVisible(false);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            }
            return true;
        }

        if (id == R.id.i_list) {
            if(mMenu != null) {
                MenuItem gridMenuItem = mMenu.findItem(R.id.i_grid);
                gridMenuItem.setVisible(true);
                item.setVisible(false);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** MVP View methods implementation **/

    @Override
    public void showPhotos(Photos photos) {
        mPhotos = photos;
        mPhotosAdapter.addPhotos(photos.getPhotos());
        mPhotosAdapter.notifyDataSetChanged();
        loading = false;
    }

    @Override
    public void showPhotosEmpty() {
        mPhotosAdapter.setPhotos(Collections.<Photo>emptyList());
        mPhotosAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_photos, Toast.LENGTH_LONG).show();
        loading = false;
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_photos))
                .show();
    }

    /** Pagination **/
    @Override
    public void onLoadMore() {
        loading = true;
        if (mPhotos != null) {
            if (currentPage * mPhotos.getPerpage() < mPhotos.getTotal()) {
                currentPage++;
                mMainPresenter.loadPhotos(currentPage);
            }
        }
    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        if (mPhotos != null) {
            return currentPage * mPhotos.getPerpage() >= mPhotos.getTotal();
        }
        return false;
    }

}






