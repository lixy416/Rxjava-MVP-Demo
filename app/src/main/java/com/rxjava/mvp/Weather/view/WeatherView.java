package com.rxjava.mvp.Weather.view;

import com.rxjava.mvp.bean.WeatherBean;

import java.util.List;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 17:43.
 */
public interface WeatherView {
    void showProgress();
    void hideProgress();
    void showWeatherLayout();

    void setCity(String city);
    void setToday(String data);
    void setTemperature(String temperature);
    void setWind(String wind);
    void setWeather(String weather);
    void setWeatherImage(int res);
    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);
}
