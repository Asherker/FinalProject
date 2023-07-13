package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.bean.MySqlConfigBean;
import com.uch.finalproject.model.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class LoginController {
    @Autowired
    private MySqlConfigBean mySqlConfigBean;

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "登入API")
    @Operation(summary = "登入系統", description = "登入這個超級系統")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "API執行成功", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LoginResponse.class)))
        }),
        @ApiResponse(responseCode = "401", description = "沒有權限", content = {
            @Content()
        }),
        @ApiResponse(responseCode = "999", description = "伺服器內部錯誤", content = {
            @Content()
        })
    })
    public LoginResponse Login(
        @Parameter(description = "帳號名稱", example = "andy") @RequestParam(defaultValue = "") String username, 
        @Parameter(description = "密碼", example = "1234") @RequestParam(defaultValue = "") String password) {
        return checkAccount(username, password);
    }

    // 判斷帳號是否存在
    private LoginResponse checkAccount(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // 註冊mySQL資料庫驅動程式
        try {
            Class.forName(mySqlConfigBean.getDriverClassName());

            // 連線資料庫
            conn = DriverManager.getConnection(mySqlConfigBean.getUrlShopping() + "?user=" + mySqlConfigBean.getUsername() + "&password=" + mySqlConfigBean.getPassword());

            // 取得Statement物件
            stmt = conn.createStatement();

            // 查詢該帳號是否存在
            rs = stmt.executeQuery("select count(*) as c from account where name='" + username + "'");

            // 判斷帳號是否存在
            rs.next(); // 跳到查詢結果的第一筆資料
            int c = rs.getInt("c"); // 查詢到的資料筆數

            // if(c == 0) {
            //     return new LoginResponse(2, "帳號不存在");
            // } else {
            //     return new LoginResponse(0, "成功");
            // }

            // 帳號不存在，直接返回錯誤
            if(c == 0) {
                // 把資料庫相關資源釋放
                rs.close();
                stmt.close();
                conn.close();

                return new LoginResponse(2, "帳號不存在");
            }

            // 帳號存在，繼續判斷密碼
            rs = stmt.executeQuery("select count(*) as c from account " +
                "where name='" + username + "' and password='" + password + "';");

            // 移動到第一筆資料
            rs.next();
            c = rs.getInt("c"); // 查詢到的資料筆數

            // 把資料庫相關資源釋放
            rs.close();
            stmt.close();
            conn.close();

            // 密碼錯誤
            if(c == 0) {
                return new LoginResponse(3, "密碼錯誤");
            }

            return new LoginResponse(0, "登入成功");
            // return c == 0 ? new LoginResponse(2, "帳號不存在") : new LoginResponse(0, "登入成功");

        } catch (ClassNotFoundException e) {
            // 無法註冊(錯誤代碼1)
            return new LoginResponse(1, "無法註冊驅動程式");
        } catch (SQLException e) {
            // SQL操作錯誤(代碼2)
            return new LoginResponse(e.getErrorCode(), e.getMessage());
        }
    }
}
