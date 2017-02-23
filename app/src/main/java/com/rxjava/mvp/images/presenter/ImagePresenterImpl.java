package com.rxjava.mvp.images.presenter;

import com.rxjava.mvp.bean.ImageBean;
import com.rxjava.mvp.commoms.Urls;
import com.rxjava.mvp.images.model.ImageModel;
import com.rxjava.mvp.images.model.ImageModelImpl;
import com.rxjava.mvp.images.view.ImageView;

import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 15:57.
 */
public class ImagePresenterImpl implements ImagePresenter , ImageModelImpl.OnImageListListener {
    private ImageView mImageView;
    private ImageModel model;
    public ImagePresenterImpl(ImageView view){
        this.mImageView = view;
        model = new ImageModelImpl();

    }
    @Override
    public void loadImageList() {
        mImageView.showProgress();
        model.loadImageList(Urls.IMAGES_URL,this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.hideProgress();
        mImageView.showImageList(list);

    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showFailureMsg(msg);

    }
}
