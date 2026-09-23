package com.smartcity.dao;

import com.smartcity.db.DBConnection;
import com.smartcity.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

    public List<City> getAllCities() {

        List<City> cities = new ArrayList<>();

        String sql = "SELECT * FROM cities";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int cityId = resultSet.getInt("city_id");
                String cityName = resultSet.getString("city_name");
                String state = resultSet.getString("state");
                String description = resultSet.getString("description");

                City city = new City(
                    cityId,
                    cityName,
                    state,
                    description
                );

                cities.add(city);
            }

        } catch (Exception e) {

            System.out.println("Error retrieving cities!");
            e.printStackTrace();
        }

        return cities;
    }
}