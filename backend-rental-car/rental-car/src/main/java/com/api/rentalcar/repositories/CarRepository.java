package com.api.rentalcar.repositories;

import com.api.rentalcar.models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;


public class CarRepository extends Repository {
    String query;

    public List<Car> readAllBy(String field, String value) {
        List<Car> carsList = new ArrayList<>();
        query = "SELECT * FROM Visited WHERE " + field + " = " + "'" + value + "'";
        PreparedStatement ps = null;

        try {
            ps = this.DatabaseConn.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Could not generate query" + e);
        }

        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String model = rs.getString("model");
                Double rent = rs.getDouble("rent");
                String make = rs.getString("make");
                String color = rs.getString("color");
                String type = rs.getString("type");
                String transmission = rs.getString("transmission");
                String horsepower = rs.getString("horsepower");
                Double acceleration = rs.getDouble("acceleration");
                Integer seats = rs.getInt("seats");
                Integer trunk = rs.getInt("trunk");
                Double consumption = rs.getDouble("consumption");

                Car car = new Car (model, rent, make, color, type, transmission, horsepower, acceleration, seats, trunk, consumption);
                carsList.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Could not execute query" + e);
        }

        return carsList;
    }
}
