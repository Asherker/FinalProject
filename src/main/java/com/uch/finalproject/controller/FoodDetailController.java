package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.bean.MySqlConfigBean;
import com.uch.finalproject.model.FoodDetailEntity;
import com.uch.finalproject.model.FoodDetailResponse;
import com.uch.finalproject.model.FoodEntitly;
import com.uch.finalproject.model.FoodResponse;

@RestController
public class FoodDetailController {
    @Autowired
    private MySqlConfigBean mySqlConfigBean;
    
    @RequestMapping(value = "/foodDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodDetailResponse foodDetail(int id) {
        return getFoodDetail(id);
    }

    private FoodDetailResponse getFoodDetail(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(mySqlConfigBean.getDriverClassName());

            conn = DriverManager.getConnection("jdbc:mysql://localhost/shopping?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select * from food_detail fd where id = " + id);

            boolean isDataExist = rs.next();

            // 如果isDataExist == false代表一筆資料都沒有
            if(!isDataExist) {
                return new FoodDetailResponse(2, "無此資料, id=" + id, null);
            } else {
                FoodDetailEntity foodDetailEntity = new FoodDetailEntity();
                foodDetailEntity.setId(rs.getInt("id"));
                foodDetailEntity.setName(rs.getString("name"));
                foodDetailEntity.setCategory(rs.getString("category"));
                foodDetailEntity.setCalories(rs.getInt("calories"));
                foodDetailEntity.setProtein(rs.getFloat("protein"));
                foodDetailEntity.setSaturatedFat(rs.getFloat("saturated_fat"));
                foodDetailEntity.setDietaryFiber(rs.getFloat("dietary_fiber"));
                foodDetailEntity.setTotalCarbohydrates(rs.getFloat("total_carbohydrates"));

                return new FoodDetailResponse(0, "成功", foodDetailEntity);
            }
                
        } catch(SQLException e) {
            return new FoodDetailResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new FoodDetailResponse(1, "無法註冊驅動程式", null);
        }
    }
}
