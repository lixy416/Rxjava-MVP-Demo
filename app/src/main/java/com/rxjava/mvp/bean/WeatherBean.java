package com.rxjava.mvp.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class WeatherBean implements Parcelable {

    /**
     * temperature
     */
    private String temperature;
    /**
     * weather
     */
    private String weather;
    /**
     * wind
     */
    private String wind;
    /**
     * week
     */
    private String week;
    /**
     * date
     */
    private String date;

    private int imageRes;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.temperature);
        dest.writeString(this.weather);
        dest.writeString(this.wind);
        dest.writeString(this.week);
        dest.writeString(this.date);
        dest.writeInt(this.imageRes);
    }

    public WeatherBean() {
    }

    protected WeatherBean(Parcel in) {
        this.temperature = in.readString();
        this.weather = in.readString();
        this.wind = in.readString();
        this.week = in.readString();
        this.date = in.readString();
        this.imageRes = in.readInt();
    }

    public static final Parcelable.Creator<WeatherBean> CREATOR = new Parcelable.Creator<WeatherBean>() {
        @Override
        public WeatherBean createFromParcel(Parcel source) {
            return new WeatherBean(source);
        }

        @Override
        public WeatherBean[] newArray(int size) {
            return new WeatherBean[size];
        }
    };
}
