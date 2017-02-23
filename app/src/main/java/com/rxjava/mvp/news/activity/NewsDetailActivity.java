package com.rxjava.mvp.news.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.rxjava.mvp.R;
import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.news.presenter.NewsDetailPresenter;
import com.rxjava.mvp.news.presenter.NewsDetailPresenterImpl;
import com.rxjava.mvp.news.view.NewsDetailView;
import com.rxjava.mvp.utils.ImageLoaderUtils;
import com.rxjava.mvp.utils.ToolsUtil;
import com.swipebacklayout.activity.SwipeBackActivity;
import com.swipebacklayout.widget.SwipeBackLayout;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * @version 1.0
 *          created by lxf on 2016/8/11 10:51.
 */
public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView{
    private HtmlTextView newsContentTv;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;
    private NewsBean newsBean;
    private NewsDetailPresenter detailPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initActionBar();
        initView();

    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        newsContentTv = (HtmlTextView) findViewById(R.id.htNewsContent);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        newsBean =  getIntent().getParcelableExtra("news");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(newsBean.getTitle());

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage), newsBean.getImgsrc());

        detailPresenter = new NewsDetailPresenterImpl(this);
        detailPresenter.loadNewsDetail(newsBean.getDocid());
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetailContent(String detaiContent) {
        newsContentTv.setHtmlFromString(detaiContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);

    }
}
