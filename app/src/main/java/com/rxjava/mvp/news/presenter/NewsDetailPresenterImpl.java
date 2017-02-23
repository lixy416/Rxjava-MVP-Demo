package com.rxjava.mvp.news.presenter;

import com.rxjava.mvp.bean.NewsDetailBean;
import com.rxjava.mvp.news.model.NewsModel;
import com.rxjava.mvp.news.model.NewsModelImpl;
import com.rxjava.mvp.news.view.NewsDetailView;

/**
 * @version 1.0
 *          created by lxf on 2016/8/11 11:07.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter,NewsModelImpl.OnLoadDetailListener {
    private NewsDetailView detailView;
    private NewsModel newsModel;

    public NewsDetailPresenterImpl(NewsDetailView detailView){
        this.detailView = detailView;
        newsModel = new NewsModelImpl();
    }
    @Override
    public void loadNewsDetail(String docId) {
        detailView.showProgress();
        newsModel.loadNewsDetail(docId,this);

    }

    @Override
    public void onSuccess(NewsDetailBean detailBean) {
        if (detailBean != null){
            detailView.showDetailContent(detailBean.getBody());
        }
        detailView.hideProgress();

    }

    @Override
    public void onFailure(String msg, Exception e) {
        detailView.hideProgress();

    }
}
