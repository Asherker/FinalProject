package com.uch.finalproject.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodDetailEntity;
import com.uch.finalproject.model.FoodDetailListResponse;
import com.uch.finalproject.model.FoodDetailResponse;
import com.uch.finalproject.model.FoodEntitly;
import com.uch.finalproject.model.FoodResponse;

@RestController
@RequestMapping("/demo")
public class DemoPagination {
    @RequestMapping(value = "/foods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodDetailListResponse foods(int page, int count) {
        return getFoodList(page, count);
    }

    private FoodDetailListResponse getFoodList(int page, int count) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/food?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber  from food_detail fd join category c on c.category_no = fd.category_no limit " + count + " offset " + page);

            ArrayList<FoodDetailEntity> foods = new ArrayList<>();
            while(rs.next()) {
                FoodDetailEntity foodDetailEntity = new FoodDetailEntity();
                foodDetailEntity.setId(rs.getInt("food_id"));
                foodDetailEntity.setName(rs.getString("name"));
                foodDetailEntity.setCategory(rs.getString("category"));
                foodDetailEntity.setCalories(rs.getInt("calories"));
                foodDetailEntity.setProtein(rs.getFloat("protein"));
                foodDetailEntity.setSaturatedFat(rs.getFloat("saturated_fat"));
                foodDetailEntity.setDietaryFiber(rs.getFloat("dietary_fiber"));
                foodDetailEntity.setTotalCarbohydrates(rs.getFloat("total_carbohydrates"));

                foods.add(foodDetailEntity);
            }

            return new FoodDetailListResponse(0, "成功", foods);
        } catch(SQLException e) {
            return new FoodDetailListResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new FoodDetailListResponse(1, "無法註冊驅動程式", null);
        }
    }
}
