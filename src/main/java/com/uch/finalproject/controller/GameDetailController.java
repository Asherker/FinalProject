package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodDetailResponse;
import com.uch.finalproject.model.GameDetailEntity;
import com.uch.finalproject.model.GameDetailResponse;

@RestController
public class GameDetailController {
    @RequestMapping(value = "/gameDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameDetailResponse gameDetail(int id) {  // 這個API需要傳入id參數來找到指定的遊戲資訊
        return getGameDetail(id);
    }

    private GameDetailResponse getGameDetail(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select * from detail fd where id = " + id);

            boolean isDataExist = rs.next();

            // 如果isDataExist == false代表一筆資料都沒有
            if(!isDataExist) {
                return new GameDetailResponse(2, "無此資料, id=" + id, null);
            } else {
                GameDetailEntity gameDetailEntity = new GameDetailEntity();
                gameDetailEntity.setId(rs.getInt("id"));
                gameDetailEntity.setChName(rs.getString("ch_name"));
                gameDetailEntity.setEnName(rs.getString("en_name"));
                gameDetailEntity.setDevYear(rs.getInt("dev_year"));
                gameDetailEntity.setDescription(rs.getString("description"));

                return new GameDetailResponse(0, "成功", gameDetailEntity);
            }
        } catch(SQLException e) {
            return new GameDetailResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new GameDetailResponse(1, "無法註冊驅動程式", null);
        }
    }
}
