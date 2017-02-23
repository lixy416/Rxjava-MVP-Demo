package com.rxjava.mvp.Weather.model;

import android.content.Context;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 17:21.
 */
public interface WeatherModel {
    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);
    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
