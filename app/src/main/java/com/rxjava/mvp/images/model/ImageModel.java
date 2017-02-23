package com.rxjava.mvp.images.model;

/**
 * @version 1.0
 *          created by lxf on 2016/8/11 13:28.
 */
public interface ImageModel {
    void loadImageList(String url, ImageModelImpl.OnImageListListener listener);
}
