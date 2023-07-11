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
import com.uch.finalproject.model.GameDetailEntity;
import com.uch.finalproject.model.GameDetailResponse;
import com.uch.finalproject.model.GameResponse;
import com.uch.finalproject.model.StringArrayResponse;

@RestController
@RequestMapping("/game")
public class DemoRemoteSelect {
    @Autowired
    private MySqlConfigBean mySqlConfigBean;
    
    @RequestMapping(value = "/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringArrayResponse getGameName(String keyword) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(mySqlConfigBean.getDriverClassName());

            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");

            stmt = conn.createStatement();

            // ToDo: 改query:  select name, category, buy_date, exp_date, quantity  from foods f join food_detail fd where f.food_id = fd.id;
            rs = stmt.executeQuery("select name from storesystem where name like '%" + keyword + "%'");

            ArrayList<String> data = new ArrayList<>();
            while(rs.next()) {
                
                data.add(rs.getString("name"));
            }

            return new StringArrayResponse(0, "成功", data);
        } catch(SQLException e) {
            return new StringArrayResponse(e.getErrorCode(), e.getMessage(), null);
        } catch(ClassNotFoundException e) {
            return new StringArrayResponse(1, "無法註冊驅動程式", null);
        }
    }
}
