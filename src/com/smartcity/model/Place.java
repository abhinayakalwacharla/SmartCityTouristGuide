package com.smartcity.model;

public class Place {

    private int placeId;
    private int cityId;
    private String placeName;
    private String location;
    private String category;
    private double rating;
    private double entryFee;
    private String description;

    // Default constructor
    public Place() {
    }

    // Parameterized constructor
    public Place(
            int placeId,
            int cityId,
            String placeName,
            String location,
            String category,
            double rating,
            double entryFee,
            String description
    ) {
        this.placeId = placeId;
        this.cityId = cityId;
        this.placeName = placeName;
        this.location = location;
        this.category = category;
        this.rating = rating;
        this.entryFee = entryFee;
        this.description = description;
    }

    // Getter and Setter for placeId
    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    // Getter and Setter for cityId
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    // Getter and Setter for placeName
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for rating
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Getter and Setter for entryFee
    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return placeName + " - " + category;
    }
}