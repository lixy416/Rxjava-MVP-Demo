package com.rxjava.mvp.images.model;

import com.rxjava.mvp.bean.ImageBean;
import com.rxjava.mvp.httputil.HttpUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 *          created by lxf on 2016/8/11 13:30.
 */
public class ImageModelImpl implements ImageModel {

    @Override
    public void loadImageList(String url, final OnImageListListener listener) {
//        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
//            @Override
//            public void onSuccess(String response) {
//                List<ImageBean> imageBeanList = ImageJsonUtils.readJsonImageBeans(response);
//                listener.onSuccess(imageBeanList);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                listener.onFailure("load data failed",e);
//
//            }
//        };
//        OkHttpUtils.get(url,callback);
        HttpUtil.getInstance().getHttpService().getImageList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ImageBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure("load data failed", (Exception) e);
                    }

                    @Override
                    public void onNext(List<ImageBean> list) {
                        listener.onSuccess(list);
                    }
                });
    }

    public interface OnImageListListener{
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg,Exception e);
    }
}
