package com.smartcity.model;

public class City {

    private int cityId;
    private String cityName;
    private String state;
    private String description;

    public City(int cityId, String cityName, String state, String description) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.state = state;
        this.description = description;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }
}