package com.rxjava.mvp.utils;

import android.content.Context;
import android.widget.Toast;

import com.rxjava.mvp.Application.DemoApplication;

/**
 * @version 1.0
 *          created by lxf on 2016/8/8 14:03.
 */
public class ToastUtils {
    private static Toast toast;
    public static void showLong(String msg){
        Toast.makeText(DemoApplication.getInstance(),msg,Toast.LENGTH_LONG).show();
    }
    public static void showShort(String msg){
        Toast.makeText(DemoApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void showOnceToast(Context mContext,String msg){
        if (toast == null){
            toast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();

    }

}
