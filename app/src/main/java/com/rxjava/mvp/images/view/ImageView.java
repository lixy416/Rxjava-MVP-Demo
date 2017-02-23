package com.rxjava.mvp.images.view;

import com.rxjava.mvp.bean.ImageBean;
import com.rxjava.mvp.commoms.BaseView;

import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/11 13:47.
 */
public interface ImageView extends BaseView{
    void showImageList(List<ImageBean> dataList);
    void showFailureMsg(String msg);
}
