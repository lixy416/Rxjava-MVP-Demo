package com.rxjava.mvp.news.model;

import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.bean.NewsDetailBean;
import com.rxjava.mvp.commoms.Urls;
import com.rxjava.mvp.news.activity.NewsFragment;
import com.rxjava.mvp.news.NewsJsonUtils;
import com.rxjava.mvp.utils.OkHttpUtils;

import java.util.List;

/**
 *  新闻model实现类，对数据操作具体实现
 * @version 1.0
 *          created by lxf on 2016/8/9 10:16.
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> list = NewsJsonUtils.readJsonNewsBeans(response,getID(type));
                listener.onSuccess(list);

            }
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url,callback);

    }

    @Override
    public void loadNewsDetail(final String docid, final OnLoadDetailListener listener) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(url,resultCallback);

    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    public interface OnLoadNewsListListener{
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg,Exception e);

    }

    public interface OnLoadDetailListener{
        void onSuccess(NewsDetailBean detailBean);
        void onFailure(String msg,Exception e);
    }
}
