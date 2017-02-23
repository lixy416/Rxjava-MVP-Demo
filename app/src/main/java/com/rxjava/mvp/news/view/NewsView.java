package com.rxjava.mvp.news.view;

import com.rxjava.mvp.bean.NewsBean;
import com.rxjava.mvp.commoms.BaseView;

import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/9 10:07.
 */
public interface NewsView extends BaseView {
    void addNews(List<NewsBean> list);
    void showErrorMessage();
}
