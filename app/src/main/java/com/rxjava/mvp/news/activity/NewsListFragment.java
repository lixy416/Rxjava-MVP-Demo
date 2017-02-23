package com.rxjava.mvp.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxjava.mvp.R;
import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.commoms.BaseFragment;
import com.rxjava.mvp.commoms.Urls;
import com.rxjava.mvp.news.adapter.NewsRecyclerAdapter;
import com.rxjava.mvp.news.presenter.NewsPresenter;
import com.rxjava.mvp.news.presenter.NewsPresenterImpl;
import com.rxjava.mvp.news.view.NewsView;
import com.rxjava.mvp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/8 15:00.
 */
public class NewsListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,NewsView{
    private int tabId;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;
    private NewsPresenter mNewsPresenter;
    private NewsRecyclerAdapter adapter;
    private List<NewsBean> mData;
    private int pageIndex = 0;
    private LinearLayoutManager manager;

    public NewsListFragment(){

    }

    public static NewsListFragment newInstance(String title,int tabId,int position){
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putInt("id",tabId);
        bundle.putInt("index",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        tabId = data.getInt("id");
        mNewsPresenter = new NewsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_newslist,null);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        refreshLayout.setColorSchemeColors(R.color.colorPrimaryDark,R.color.colorPrimary,R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new NewsRecyclerAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener(listener);
        mRecyclerView.addOnScrollListener(scrollListener);
        onRefresh();
        return view;
    }

    private NewsRecyclerAdapter.OnItemClickListener listener = new NewsRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean bean = adapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", bean);
            startActivity(intent);
        }
    };

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()
                    && adapter.ismShowFooter()) {
                //加载更多
                LogUtils.d("newsFragment", "loading more data");
                mNewsPresenter.loadNews(tabId, pageIndex + Urls.PAZE_SIZE);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = manager.findLastVisibleItemPosition();
        }
    };
    @Override
    public void onRefresh() {
        pageIndex = 0;
        if( mData != null) {
            mData.clear();
        }
        mNewsPresenter.loadNews(tabId, pageIndex);
    }
    @Override
    public void addNews(List<NewsBean> list) {
        adapter.setmShowFooter(true);
        if (mData == null){
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        if (pageIndex == 0){
            adapter.setData(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(list == null || list.size() == 0) {
                adapter.setmShowFooter(false);
            }
            adapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;

    }
    @Override
    public void showErrorMessage() {
        if(pageIndex == 0) {
            adapter.setmShowFooter(false);
            adapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }
    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }
}
