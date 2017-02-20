package com.flickrphotos.ui.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flickrphotos.R;
import com.flickrphotos.model.Photos;
import com.flickrphotos.presenter.MainPresenter;
import com.flickrphotos.ui.adapters.PhotosAdapter;
import com.flickrphotos.utils.Constants;
import com.flickrphotos.utils.DialogFactory;
import com.flickrphotos.view.MainMvpView;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemCreator;
import com.paginate.recycler.LoadingListItemSpanLookup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Pablo on 12/2/2017.
 */
public class MainActivity extends BaseActivity implements MainMvpView, Paginate.Callbacks {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    PhotosAdapter mPhotosAdapter;

    private boolean loading = false;
    private Paginate paginate;
    private Photos mPhotos;
    private int currentPage = 1;
    private boolean searchMode = false;
    private String mQuery;
    private Menu mMenu;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getApplicationComponent().inject(this);

        mRecyclerView.setAdapter(mPhotosAdapter);
        mPhotosAdapter.setMode(Constants.GRID_MODE);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.GRID_SPAN_COUNT));
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        mMainPresenter.attachView(this);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                mPhotosAdapter.getPhotos().clear();
                searchMode = false;
                mMainPresenter.loadRecentPhotos(currentPage);
            }
        });

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
            mMainPresenter.loadRecentPhotos(currentPage);
        }

        if (paginate != null) {
            paginate.unbind();
        }

        paginate = Paginate.with(mRecyclerView, this)
                .setLoadingTriggerThreshold(Constants.THRESHOLD)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
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

        final MenuItem searchItem = menu.findItem(R.id.i_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getText(R.string.search_text));
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    mPhotosAdapter.getPhotos().clear();
                    currentPage = 1;
                    searchMode = true;
                    mQuery = query;
                    mMainPresenter.searchPhotosByText(currentPage, query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMode = false;
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                currentPage = 1;
                searchMode = true;
                mMainPresenter.loadRecentPhotos(currentPage);
                return false;
            }
        });

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
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.LIST_SPAN_COUNT));
                mPhotosAdapter.setMode(Constants.LIST_MODE);
                mPhotosAdapter.notifyDataSetChanged();
            }
            return true;
        }

        if (id == R.id.i_list) {
            if(mMenu != null) {
                MenuItem gridMenuItem = mMenu.findItem(R.id.i_grid);
                gridMenuItem.setVisible(true);
                item.setVisible(false);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.GRID_SPAN_COUNT));
                mPhotosAdapter.setMode(Constants.GRID_MODE);
                mPhotosAdapter.notifyDataSetChanged();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** MVP View methods implementation **/

    @Override
    public void showPhotos(Photos photos) {
        mSwipeRefresh.setRefreshing(false);
        mPhotos = photos;
        mPhotosAdapter.addPhotos(photos.getPhotos());
        mPhotosAdapter.notifyDataSetChanged();
        loading = false;
    }

    @Override
    public void showPhotosEmpty() {
        mSwipeRefresh.setRefreshing(false);
        mPhotosAdapter.getPhotos().clear();
        mPhotosAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_photos, Toast.LENGTH_LONG).show();
        loading = false;
    }

    @Override
    public void showError() {
        mSwipeRefresh.setRefreshing(false);
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_photos))
                .show();
    }

    /** Pagination **/
    @Override
    public void onLoadMore() {
        loading = true;
        if (mPhotos != null) {
            if (currentPage * mPhotos.getPerPage() < mPhotos.getTotal()) {
                currentPage++;
                if (searchMode) {
                    mMainPresenter.searchPhotosByText(currentPage, mQuery);
                }
                else {
                    mMainPresenter.loadRecentPhotos(currentPage);
                }
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
            return currentPage * mPhotos.getPerPage() >= mPhotos.getTotal();
        }
        return false;
    }

    private class CustomLoadingListItemCreator implements LoadingListItemCreator {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.custom_loading_list_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            VH vh = (VH) holder;
        }
    }

    static class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }
    }

}






