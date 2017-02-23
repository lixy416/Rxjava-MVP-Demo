package com.rxjava.mvp.news.model;

/**
 * 新闻模块model,负责数据相关的操作
 * @version 1.0
 *          created by lxf on 2016/8/9 10:15.
 */
public interface NewsModel {
    void loadNews(String url,int type,NewsModelImpl.OnLoadNewsListListener listener);
    void loadNewsDetail(String docid, NewsModelImpl.OnLoadDetailListener listener);
}
