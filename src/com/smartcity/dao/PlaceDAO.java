package com.smartcity.dao;

import com.smartcity.db.DBConnection;
import com.smartcity.model.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {

    // =========================================================
    // GET ALL PLACES BY CITY
    // =========================================================

    public List<Place> getPlacesByCity(int cityId) {

        List<Place> places = new ArrayList<>();

        String sql =
                "SELECT place_id, city_id, place_name, " +
                "location, category, rating, entry_fee, description " +
                "FROM places " +
                "WHERE city_id = ? " +
                "ORDER BY place_id";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                places.add(mapPlace(rs));
            }

        } catch (Exception e) {

            System.out.println("Error while loading places!");
            e.printStackTrace();
        }

        return places;
    }


    // =========================================================
    // GET PLACE BY ID
    // =========================================================

    public Place getPlaceById(int placeId) {

        String sql =
                "SELECT place_id, city_id, place_name, " +
                "location, category, rating, entry_fee, description " +
                "FROM places " +
                "WHERE place_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, placeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapPlace(rs);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while loading place details!"
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // GET AVERAGE RATING BY CITY
    // =========================================================

    public double getAverageRatingByCity(int cityId) {

        String sql =
                "SELECT AVG(rating) AS average_rating " +
                "FROM places " +
                "WHERE city_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cityId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double averageRating =
                        rs.getDouble("average_rating");

                if (rs.wasNull()) {
                    return 0.0;
                }

                return averageRating;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while calculating average rating!"
            );

            e.printStackTrace();
        }

        return 0.0;
    }


    // =========================================================
    // GET MINIMUM ENTRY FEE BY CITY
    // =========================================================

    public double getMinimumFeeByCity(int cityId) {

        String sql =
                "SELECT MIN(entry_fee) AS minimum_fee " +
                "FROM places " +
                "WHERE city_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cityId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double minimumFee =
                        rs.getDouble("minimum_fee");

                if (rs.wasNull()) {
                    return 0.0;
                }

                return minimumFee;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while finding minimum entry fee!"
            );

            e.printStackTrace();
        }

        return 0.0;
    }


    // =========================================================
    // GET MAXIMUM ENTRY FEE BY CITY
    // =========================================================

    public double getMaximumFeeByCity(int cityId) {

        String sql =
                "SELECT MAX(entry_fee) AS maximum_fee " +
                "FROM places " +
                "WHERE city_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cityId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double maximumFee =
                        rs.getDouble("maximum_fee");

                if (rs.wasNull()) {
                    return 0.0;
                }

                return maximumFee;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while finding maximum entry fee!"
            );

            e.printStackTrace();
        }

        return 0.0;
    }


    // =========================================================
    // SEARCH PLACES
    // =========================================================

    public List<Place> searchPlaces(String keyword) {

        List<Place> places = new ArrayList<>();

        String sql =
                "SELECT place_id, city_id, place_name, " +
                "location, category, rating, entry_fee, description " +
                "FROM places " +
                "WHERE place_name LIKE ? " +
                "OR category LIKE ? " +
                "OR location LIKE ? " +
                "OR description LIKE ? " +
                "ORDER BY rating DESC";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String searchPattern = "%" + keyword + "%";

            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                places.add(mapPlace(rs));
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while searching tourist places!"
            );

            e.printStackTrace();
        }

        return places;
    }


    // =========================================================
    // GET TOP RATED PLACES
    // =========================================================

    public List<Place> getTopRatedPlaces() {

        List<Place> places = new ArrayList<>();

        String sql =
                "SELECT place_id, city_id, place_name, " +
                "location, category, rating, entry_fee, description " +
                "FROM places " +
                "ORDER BY rating DESC " +
                "LIMIT 10";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                places.add(mapPlace(rs));
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while loading top rated places!"
            );

            e.printStackTrace();
        }

        return places;
    }


    // =========================================================
    // GET PLACES BY BUDGET
    // =========================================================

    public List<Place> getPlacesByBudget(double budget) {

        List<Place> places = new ArrayList<>();

        String sql =
                "SELECT place_id, city_id, place_name, " +
                "location, category, rating, entry_fee, description " +
                "FROM places " +
                "WHERE entry_fee <= ? " +
                "ORDER BY entry_fee ASC, rating DESC";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDouble(1, budget);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                places.add(mapPlace(rs));
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while finding places by budget!"
            );

            e.printStackTrace();
        }

        return places;
    }


    // =========================================================
    // HELPER METHOD
    // CONVERT RESULTSET ROW INTO PLACE OBJECT
    // =========================================================

    private Place mapPlace(ResultSet rs) throws Exception {

        Place place = new Place();

        place.setPlaceId(
                rs.getInt("place_id")
        );

        place.setCityId(
                rs.getInt("city_id")
        );

        place.setPlaceName(
                rs.getString("place_name")
        );

        place.setLocation(
                rs.getString("location")
        );

        place.setCategory(
                rs.getString("category")
        );

        place.setRating(
                rs.getDouble("rating")
        );

        place.setEntryFee(
                rs.getDouble("entry_fee")
        );

        place.setDescription(
                rs.getString("description")
        );

        return place;
    }
}