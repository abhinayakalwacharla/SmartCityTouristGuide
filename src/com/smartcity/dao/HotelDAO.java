package com.smartcity.dao;

import com.smartcity.db.DBConnection;
import com.smartcity.model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    // =====================================================
    // GET HOTELS BY CITY
    // =====================================================

    public List<Hotel> getHotelsByCity(int cityId) {

        List<Hotel> hotels = new ArrayList<>();

        String sql =
                "SELECT hotel_id, city_id, hotel_name, " +
                "location, rating, price " +
                "FROM hotels " +
                "WHERE city_id = ? " +
                "ORDER BY hotel_id";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, cityId);

            System.out.println(
                    "Searching hotels for city_id = " + cityId
            );

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    hotels.add(mapHotel(rs));
                }
            }

            System.out.println(
                    "Hotels found: " + hotels.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while loading hotels!"
            );

            e.printStackTrace();
        }

        return hotels;
    }


    // =====================================================
    // GET HOTEL BY ID
    // =====================================================

    public Hotel getHotelById(int hotelId) {

        String sql =
                "SELECT hotel_id, city_id, hotel_name, " +
                "location, rating, price " +
                "FROM hotels " +
                "WHERE hotel_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, hotelId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapHotel(rs);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while loading hotel!"
            );

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // SEARCH HOTELS
    // =====================================================

    public List<Hotel> searchHotels(String keyword) {

        List<Hotel> hotels = new ArrayList<>();

        String sql =
                "SELECT hotel_id, city_id, hotel_name, " +
                "location, rating, price " +
                "FROM hotels " +
                "WHERE hotel_name LIKE ? " +
                "OR location LIKE ? " +
                "ORDER BY rating DESC";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String searchPattern =
                    "%" + keyword + "%";

            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    hotels.add(mapHotel(rs));
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while searching hotels!"
            );

            e.printStackTrace();
        }

        return hotels;
    }


    // =====================================================
    // GET TOP RATED HOTELS
    // =====================================================

    public List<Hotel> getTopRatedHotels() {

        List<Hotel> hotels = new ArrayList<>();

        String sql =
                "SELECT hotel_id, city_id, hotel_name, " +
                "location, rating, price " +
                "FROM hotels " +
                "ORDER BY rating DESC " +
                "LIMIT 10";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    hotels.add(mapHotel(rs));
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while loading top rated hotels!"
            );

            e.printStackTrace();
        }

        return hotels;
    }


    // =====================================================
    // GET HOTELS BY MAXIMUM PRICE
    // =====================================================

    public List<Hotel> getHotelsByBudget(double maxPrice) {

        List<Hotel> hotels = new ArrayList<>();

        String sql =
                "SELECT hotel_id, city_id, hotel_name, " +
                "location, rating, price " +
                "FROM hotels " +
                "WHERE price <= ? " +
                "ORDER BY price ASC, rating DESC";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDouble(1, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    hotels.add(mapHotel(rs));
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while finding hotels by budget!"
            );

            e.printStackTrace();
        }

        return hotels;
    }


    // =====================================================
    // HELPER METHOD
    // CONVERT RESULTSET INTO HOTEL OBJECT
    // =====================================================

    private Hotel mapHotel(ResultSet rs) throws Exception {

        Hotel hotel = new Hotel();

        hotel.setHotelId(
                rs.getInt("hotel_id")
        );

        hotel.setCityId(
                rs.getInt("city_id")
        );

        hotel.setHotelName(
                rs.getString("hotel_name")
        );

        hotel.setLocation(
                rs.getString("location")
        );

        hotel.setRating(
                rs.getDouble("rating")
        );

        hotel.setPrice(
                rs.getDouble("price")
        );

        return hotel;
    }
}

