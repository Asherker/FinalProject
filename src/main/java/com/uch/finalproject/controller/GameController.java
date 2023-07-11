package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.bean.MySqlConfigBean;
import com.uch.finalproject.model.BaseResponse;
import com.uch.finalproject.model.GameEntity;
import com.uch.finalproject.model.GameResponse;

@RestController
public class GameController {
    @Autowired
    private MySqlConfigBean mySqlConfigBean;

    @RequestMapping(value = "/games", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse games() {
        return getGameList();
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,  // 傳入的資料格式
        produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addGame(@RequestBody GameEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            Class.forName(mySqlConfigBean.getDriverClassName());
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");
        
            stmt = conn.prepareStatement("INSERT INTO storesystem VALUES(null, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getCategory());
            stmt.setString(3, data.getDeveloper());
            stmt.setInt(4, data.getPrice());
            stmt.setInt(5, data.getQuantity());
            stmt.setDate(6, data.getInchange());
            stmt.setDate(7, data.getOutchange());

            stmt.executeUpdate();

            return new BaseResponse(0, "新增成功");

        }catch(SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        }catch(ClassNotFoundException e) {
            return new BaseResponse(1,"無法註冊驅動程式");
        }
    }

    @RequestMapping(value = "/game", method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,  // 傳入的資料格式
        produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateGame(@RequestBody GameEntity data) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            Class.forName(mySqlConfigBean.getDriverClassName());
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");
        
            stmt = conn.prepareStatement("UPDATE storesystem SET name=?, category=?, developer=?, price=?, quantity=?, inchange=?, outchange=? WHERE id=?");
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getCategory());
            stmt.setString(3, data.getDeveloper());
            stmt.setInt(4, data.getPrice());
            stmt.setInt(5, data.getQuantity());
            stmt.setDate(6, data.getInchange());
            stmt.setDate(7, data.getOutchange());
            stmt.setInt(8, data.getId());

            stmt.executeUpdate();

            return new BaseResponse(0, "更新成功");

        }catch(SQLException e) {
            return new BaseResponse(e.getErrorCode(), e.getMessage());
        }catch(ClassNotFoundException e) {
            return new BaseResponse(1,"無法註冊驅動程式");
        }
    }

    private GameResponse getGameList() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName(mySqlConfigBean.getDriverClassName());
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdata?user=root&password=0000");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from storesystem");//這裡後續要修改資料庫路徑以及要修改的項目

            ArrayList<GameEntity> games = new ArrayList<>();
            while(rs.next()){
                GameEntity gameEntity = new GameEntity();
                gameEntity.setId(rs.getInt("id"));
                gameEntity.setName(rs.getString("name"));
                gameEntity.setCategory(rs.getString("category"));
                gameEntity.setPrice(rs.getInt("price"));
                gameEntity.setQuantity(rs.getInt("quantity"));
                gameEntity.setDeveloper(rs.getString("developer"));
                gameEntity.setInchange(rs.getDate("inchange"));
                gameEntity.setOutchange(rs.getDate("outchange"));

                games.add(gameEntity);
            }
            return new GameResponse(0,"成功",games);
        }catch(SQLException e) {
            return new GameResponse(e.getErrorCode(), e.getMessage(), null);
        }catch(ClassNotFoundException e) {
            return new GameResponse(1,"無法註冊驅動程式",null);
        }
    }
}
