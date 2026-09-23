package com.smartcity;

import com.smartcity.dao.CityDAO;
import com.smartcity.dao.PlaceDAO;
import com.smartcity.model.City;
import com.smartcity.model.Place;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CityDAO cityDAO = new CityDAO();
        PlaceDAO placeDAO = new PlaceDAO();

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println("          SMART CITY TOURIST GUIDE");
            System.out.println("------------------------------------------------");

            System.out.println("1. View Cities");
            System.out.println("2. Search Tourist Places");
            System.out.println("3. Top Rated Places");
            System.out.println("4. Places by Budget");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                // =================================================
                // OPTION 1 - VIEW CITIES
                // =================================================

                case 1:

                    List<City> cities = cityDAO.getAllCities();

                    System.out.println(
                        "\n------------- AVAILABLE CITIES -----------------"
                    );

                    if (cities.isEmpty()) {

                        System.out.println(
                            "No cities found."
                        );

                        break;
                    }

                    for (City city : cities) {

                        System.out.println(
                            city.getCityId()
                            + ". "
                            + city.getCityName()
                            + " - "
                            + city.getState()
                        );
                    }

                    System.out.print("\nEnter City ID: ");

                    int cityId = sc.nextInt();

                    City selectedCity = null;

                    for (City city : cities) {

                        if (city.getCityId() == cityId) {

                            selectedCity = city;
                            break;
                        }
                    }

                    // ------------------------------------------------
                    // CHECK CITY
                    // ------------------------------------------------

                    if (selectedCity == null) {

                        System.out.println(
                            "\nInvalid City ID!"
                        );

                        break;
                    }

                    // ------------------------------------------------
                    // SELECTED CITY
                    // ------------------------------------------------

                    System.out.println(
                        "\n------------------------------------------------"
                    );

                    System.out.println(
                        "Selected City : "
                        + selectedCity.getCityName()
                    );

                    System.out.println(
                        "State         : "
                        + selectedCity.getState()
                    );

                    System.out.println(
                        "------------------------------------------------"
                    );

                    // ------------------------------------------------
                    // GET TOURIST PLACES
                    // ------------------------------------------------

                    List<Place> places =
                        placeDAO.getPlacesByCity(cityId);

                    if (places.isEmpty()) {

                        System.out.println(
                            "\nNo tourist places found for this city."
                        );

                        break;
                    }

                    // ------------------------------------------------
                    // CITY SUMMARY
                    // ------------------------------------------------

                    double averageRating =
                        placeDAO.getAverageRatingByCity(cityId);

                    double minimumFee =
                        placeDAO.getMinimumFeeByCity(cityId);

                    double maximumFee =
                        placeDAO.getMaximumFeeByCity(cityId);

                    System.out.println(
                        "\n--------------- CITY SUMMARY -----------------"
                    );

                    System.out.println(
                        "City           : "
                        + selectedCity.getCityName()
                    );

                    System.out.println(
                        "State          : "
                        + selectedCity.getState()
                    );

                    System.out.println(
                        "Total Places   : "
                        + places.size()
                    );

                    System.out.println(
                        "Average Rating : ⭐ "
                        + String.format("%.2f", averageRating)
                    );

                    System.out.println(
                        "Minimum Fee    : ₹"
                        + minimumFee
                    );

                    System.out.println(
                        "Maximum Fee    : ₹"
                        + maximumFee
                    );

                    System.out.println(
                        "------------------------------------------------"
                    );

                    // ------------------------------------------------
                    // TOURIST PLACES
                    // ------------------------------------------------

                    System.out.println(
                        "\n------------- TOURIST PLACES ------------------"
                    );

                    for (Place place : places) {

                        System.out.println(
                            place.getPlaceId()
                            + ". "
                            + place.getPlaceName()
                        );
                    }

                    // ------------------------------------------------
                    // SELECT PLACE
                    // ------------------------------------------------

                    System.out.print(
                        "\nEnter Place ID to view details: "
                    );

                    int placeId = sc.nextInt();

                    Place selectedPlace =
                        placeDAO.getPlaceById(placeId);

                    // ------------------------------------------------
                    // CHECK PLACE
                    // ------------------------------------------------

                    if (selectedPlace == null) {

                        System.out.println(
                            "\nInvalid Place ID!"
                        );

                    }

                    // ------------------------------------------------
                    // CHECK CITY-PLACE RELATION
                    // ------------------------------------------------

                    else if (
                        selectedPlace.getCityId() != cityId
                    ) {

                        System.out.println(
                            "\nThis place does not belong "
                            + "to the selected city!"
                        );

                    }

                    // ------------------------------------------------
                    // PLACE DETAILS
                    // ------------------------------------------------

                    else {

                        System.out.println(
                            "\n--------------- PLACE DETAILS -----------------"
                        );

                        System.out.println(
                            "Place Name  : "
                            + selectedPlace.getPlaceName()
                        );

                        System.out.println(
                            "Category    : "
                            + selectedPlace.getCategory()
                        );

                        System.out.println(
                            "Location    : "
                            + selectedPlace.getLocation()
                        );

                        System.out.println(
                            "Description : "
                            + selectedPlace.getDescription()
                        );

                        System.out.println(
                            "Entry Fee   : ₹"
                            + selectedPlace.getEntryFee()
                        );

                        System.out.println(
                            "Rating      : ⭐ "
                            + selectedPlace.getRating()
                        );

                        System.out.println(
                            "------------------------------------------------"
                        );
                    }

                    break;


                // =================================================
                // OPTION 2 - SEARCH TOURIST PLACES
                // =================================================

                case 2:

                    sc.nextLine();

                    System.out.print(
                        "\nEnter place name or category to search: "
                    );

                    String keyword = sc.nextLine();

                    List<Place> searchResults =
                        placeDAO.searchPlaces(keyword);

                    System.out.println(
                        "\n--------------- SEARCH RESULTS ----------------"
                    );

                    if (searchResults.isEmpty()) {

                        System.out.println(
                            "No tourist places found for: "
                            + keyword
                        );

                    } else {

                        for (Place place : searchResults) {

                            System.out.println(
                                "\n"
                                + place.getPlaceId()
                                + ". "
                                + place.getPlaceName()
                            );

                            System.out.println(
                                "   Category : "
                                + place.getCategory()
                            );

                            System.out.println(
                                "   Location : "
                                + place.getLocation()
                            );

                            System.out.println(
                                "   Rating   : ⭐ "
                                + place.getRating()
                            );

                            System.out.println(
                                "   Entry Fee: ₹"
                                + place.getEntryFee()
                            );
                        }
                    }

                    break;


                // =================================================
                // OPTION 3 - TOP RATED PLACES
                // =================================================

                case 3:

                    List<Place> topPlaces =
                        placeDAO.getTopRatedPlaces();

                    System.out.println(
                        "\n------------- TOP RATED PLACES ----------------"
                    );

                    if (topPlaces.isEmpty()) {

                        System.out.println(
                            "No tourist places available."
                        );

                    } else {

                        int rank = 1;

                        for (Place place : topPlaces) {

                            System.out.println(
                                "\n"
                                + rank
                                + ". "
                                + place.getPlaceName()
                            );

                            System.out.println(
                                "   Rating   : ⭐ "
                                + place.getRating()
                            );

                            System.out.println(
                                "   Category : "
                                + place.getCategory()
                            );

                            System.out.println(
                                "   Location : "
                                + place.getLocation()
                            );

                            System.out.println(
                                "   Entry Fee: ₹"
                                + place.getEntryFee()
                            );

                            rank++;
                        }
                    }

                    break;


                // =================================================
                // OPTION 4 - PLACES BY BUDGET
                // =================================================

                case 4:

                    System.out.print(
                        "\nEnter maximum entry fee: ₹"
                    );

                    double maxFee = sc.nextDouble();

                    if (maxFee < 0) {

                        System.out.println(
                            "\nEntry fee cannot be negative!"
                        );

                        break;
                    }

                    List<Place> budgetPlaces =
                        placeDAO.getPlacesByBudget(maxFee);

                    System.out.println(
                        "\n---------- PLACES WITHIN BUDGET ---------------"
                    );

                    if (budgetPlaces.isEmpty()) {

                        System.out.println(
                            "No tourist places found within ₹"
                            + maxFee
                        );

                    } else {

                        for (Place place : budgetPlaces) {

                            System.out.println(
                                "\n"
                                + place.getPlaceId()
                                + ". "
                                + place.getPlaceName()
                            );

                            System.out.println(
                                "   Category  : "
                                + place.getCategory()
                            );

                            System.out.println(
                                "   Location  : "
                                + place.getLocation()
                            );

                            System.out.println(
                                "   Entry Fee : ₹"
                                + place.getEntryFee()
                            );

                            System.out.println(
                                "   Rating    : ⭐ "
                                + place.getRating()
                            );
                        }
                    }

                    break;


                // =================================================
                // OPTION 5 - EXIT
                // =================================================

                case 5:

                    running = false;

                    System.out.println(
                        "\n------------------------------------------------"
                    );

                    System.out.println(
                        "Thank you for using Smart City Tourist Guide!"
                    );

                    System.out.println(
                        "------------------------------------------------"
                    );

                    break;


                // =================================================
                // INVALID OPTION
                // =================================================

                default:

                    System.out.println(
                        "\nInvalid choice!"
                    );

                    System.out.println(
                        "Please enter a number from 1 to 5."
                    );

                    break;
            }
        }

        sc.close();
    }
}