package com.uch.finalproject.controller;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodEntitly;
import com.uch.finalproject.model.FoodResponse;

@RestController
public class FoodController {
    @RequestMapping(value = "/foods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodResponse foods() {
        return getFoodList();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodResponse add() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/shopping?user=root&password=0000");

            stmt = conn.prepareStatement("INSERT INTO foods VALUES(null,?,?,?, ?)");
            stmt.setInt(1, 1);
            stmt.setDate(2, java.sql.Date.valueOf("2013-09-04"));
            stmt.setDate(3, java.sql.Date.valueOf("Sun Jul 16 2023 00:00:00 GMT+0800 (\u53F0\u5317\u6A19\u6E96\u6642\u9593)"));
            stmt.setInt(4, 999);

            stmt.executeUpdate();

            return new FoodResponse(0, "成功", null);
        } catch(SQLException e) {
            return new FoodResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new FoodResponse(1, "無法註冊驅動程式", null);
        }
    }

    private FoodResponse getFoodList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/shopping?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select fd.id, name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;");

            ArrayList<FoodEntitly> foods = new ArrayList<>();
            while(rs.next()) {
                FoodEntitly foodEntitly = new FoodEntitly();
                foodEntitly.setId(rs.getInt("id"));
                foodEntitly.setName(rs.getString("name"));
                foodEntitly.setCategory(rs.getString("category"));
                foodEntitly.setBuyDate(rs.getDate("buy_date"));
                foodEntitly.setExpDate(rs.getDate("exp_date"));
                foodEntitly.setQuantity(rs.getInt("quantity"));

                foods.add(foodEntitly);
            }

            return new FoodResponse(0, "成功", foods);
        } catch(SQLException e) {
            return new FoodResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new FoodResponse(1, "無法註冊驅動程式", null);
        }
    }
}
