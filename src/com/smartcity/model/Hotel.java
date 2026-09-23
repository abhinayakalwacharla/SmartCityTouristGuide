package com.smartcity.model;

public class Hotel {

    private int hotelId;
    private int cityId;
    private String hotelName;
    private String location;
    private double rating;
    private double price;

    public Hotel() {
    }

    public Hotel(
            int hotelId,
            int cityId,
            String hotelName,
            String location,
            double rating,
            double price
    ) {
        this.hotelId = hotelId;
        this.cityId = cityId;
        this.hotelName = hotelName;
        this.location = location;
        this.rating = rating;
        this.price = price;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}