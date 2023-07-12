package com.uch.finalproject.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodDetailEntity;
import com.uch.finalproject.model.FoodDetailListResponse;

@RestController
@RequestMapping("/v1")
public class SearchController extends BaseController {
    @RequestMapping(value = "/food/{columnName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodDetailListResponse searchFood(@PathVariable String columnName, String keyword, String keyvalue, int page, int count, int caloriesSortMode) {
        return search(columnName, keyword, keyvalue, page, count, caloriesSortMode);
    }

    private FoodDetailListResponse search(String columnName, String keyword, String keyvalue, int page, int count, int caloriesSortMode) {
        try {
            // 連線資料料庫
            connect(mySqlConfigBean.getUrlFood());
            stmt = conn.createStatement();

            String queryString = "select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber " + 
            "from food_detail fd join category c on c.category_no = fd.category_no " + 
            (caloriesSortMode == 0 ? "" : (caloriesSortMode == 1 ? "order by calories ASC":"order by calories DESC") ) + 
            " where " + columnName + " like '%" + keyword + "%'" + 
            " limit " + count + " offset " + ((page-1) * count);

            // 字串搜尋
            if(keyvalue.length() == 0) {
                queryString = "select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber " + 
                    "from food_detail fd join category c on c.category_no = fd.category_no " + 
                    (caloriesSortMode == 0 ? "" : (caloriesSortMode == 1 ? "order by calories ASC":"order by calories DESC") ) + 
                    " where " + columnName + " like '%" + keyword + "%'" + 
                    " limit " + count + " offset " + ((page-1) * count);
            } else {
                // 數字搜尋
                queryString = "select fd.food_id , name, category, calories , protein , saturated_fat, total_carbohydrates , dietary_fiber " + 
                    "from food_detail fd join category c on c.category_no = fd.category_no " + 
                    (caloriesSortMode == 0 ? "" : (caloriesSortMode == 1 ? "order by calories ASC":"order by calories DESC") ) + 
                    " where " + columnName + " = " + keyvalue + 
                    " limit " + count + " offset " + ((page-1) * count);
            }

            rs = stmt.executeQuery(queryString);

            // 將搜尋結果存到ArrayList
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
            rs = stmt.executeQuery("select count(*) as c from food_detail where " + columnName + " like '%" + keyword + "%'");
            rs.next();
            int total = rs.getInt("c");

            return new FoodDetailListResponse(0, "搜尋成功", foods, total);
        } catch (ClassNotFoundException e) {
            return new FoodDetailListResponse(1, "找不到驅動程式", null, 0);
        } catch (SQLException e) {
            return new FoodDetailListResponse(e.getErrorCode(), e.getMessage(), null, 0);
        }
    }
}
