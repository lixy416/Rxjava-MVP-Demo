package com.rxjava.mvp.main.presenter;

import com.rxjava.mvp.R;
import com.rxjava.mvp.main.view.MainView;

/**
 * @version 1.0
 *          created by lxf on 2016/8/8 13:56.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;
    public MainPresenterImpl(MainView view){
        this.mMainView = view;
    }
    @Override
    public void switchNavigation(int itemId) {
        switch (itemId){
            case R.id.navigation_item_news:
                mMainView.switch2News();
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_about:
                mMainView.switch2About();
                break;
        }
    }
}
