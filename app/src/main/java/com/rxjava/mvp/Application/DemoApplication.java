package com.rxjava.mvp.Application;

import android.app.Application;

/**
 * @version 1.0
 *          created by lxf on 2016/8/8 14:05.
 */
public class DemoApplication extends Application {
    private static DemoApplication instance;
    private DemoApplication(){
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static DemoApplication getInstance(){
        if (instance == null){
            synchronized (DemoApplication.class){
                if (instance == null){
                    instance = new DemoApplication();
                }
            }
        }
        return instance;
    }
}
