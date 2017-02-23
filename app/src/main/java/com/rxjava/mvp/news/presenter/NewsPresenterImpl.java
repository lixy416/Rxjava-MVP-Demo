package com.rxjava.mvp.news.presenter;

import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.commoms.Urls;
import com.rxjava.mvp.news.activity.NewsFragment;
import com.rxjava.mvp.news.model.NewsModel;
import com.rxjava.mvp.news.model.NewsModelImpl;
import com.rxjava.mvp.news.view.NewsView;
import com.rxjava.mvp.utils.LogUtils;

import java.util.List;

/**
 * @version 1.0
 * created by lxf on 2016/8/9 10:13.
 */
public class NewsPresenterImpl implements NewsPresenter,NewsModelImpl.OnLoadNewsListListener {
    private static final String TAG = "NewsPresenterImpl";
    private NewsView newsView;
    private NewsModel newsModel;
    public NewsPresenterImpl(NewsView newsView){
        this.newsView = newsView;
        newsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, int page) {
        String url = getUrl(type, page);
        LogUtils.d(TAG, url);
        if (page == 1){
            newsView.showProgress();
        }
        newsModel.loadNews(url,type,this);

    }
    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }
    @Override
    public void onSuccess(List<NewsBean> list) {
        newsView.hideProgress();
        newsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsView.hideProgress();
        newsView.showErrorMessage();

    }
}
