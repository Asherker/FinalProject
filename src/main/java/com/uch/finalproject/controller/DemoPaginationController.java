package com.uch.finalproject.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.bean.MySqlConfigBean;
import com.uch.finalproject.model.FoodDetailEntity;
import com.uch.finalproject.model.FoodDetailListResponse;

@RestController
@RequestMapping("/demo")
public class DemoPaginationController {
    @Autowired
    private MySqlConfigBean mySqlConfigBean;

    @RequestMapping(value = "/foods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodDetailListResponse foods(int page, int count, int caloriesSortMode) {
        return getFoodList(page, count, caloriesSortMode);
    }

    private FoodDetailListResponse getFoodList(int page, int count, int caloriesSortMode) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(mySqlConfigBean.getDriverClassName());

            conn = DriverManager.getConnection("jdbc:mysql://localhost/food?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber " + 
                                   "from food_detail fd join category c on c.category_no = fd.category_no " + 
                                   (caloriesSortMode == 0 ? "" : (caloriesSortMode == 1 ? "order by calories ASC":"order by calories DESC") ) + 
                                   " limit " + count + " offset " + ((page-1) * count));
            
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

            // 取得全部數量
            rs = stmt.executeQuery("select count(*) as c from food_detail");
            rs.next();
            int total = rs.getInt("c");

            return new FoodDetailListResponse(0, "成功", foods, total);
        } catch(SQLException e) {
            return new FoodDetailListResponse(e.getErrorCode(), "select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber " + 
                                   "from food_detail fd join category c on c.category_no = fd.category_no " + 
                                   (caloriesSortMode == 0 ? "" : (caloriesSortMode == 1 ? "order by calories ASC":"order by calories DESC") ) + 
                                   " limit " + count + " offset " + ((page-1) * count), null, 0);
        } catch(ClassNotFoundException e) {
            return new FoodDetailListResponse(1, "無法註冊驅動程式", null, 0);
        }
    }
}
