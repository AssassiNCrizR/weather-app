package com.example.ghuser.weatherapp.model;

/**
 * Created by ghuser on 2/13/2018.
 */

public class Sys {
    private int type;
    private int id;
    private double meesage;
    private String country;
    private double sunrise;
    private double sunset;

    public Sys(int type, int id, double meesage, String country, double sunrise, double sunset) {
        this.type = type;
        this.id = id;
        this.meesage = meesage;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMeesage() {
        return meesage;
    }

    public void setMeesage(double meesage) {
        this.meesage = meesage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }
}
