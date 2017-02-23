package com.rxjava.mvp.utils;

import android.util.Log;

import com.rxjava.mvp.BuildConfig;

/**
 * 日志打印工具
 */
public class LogUtils {
    public static final boolean DEBUG = true;

    public static void v(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, message, e);
        }
    }
}
