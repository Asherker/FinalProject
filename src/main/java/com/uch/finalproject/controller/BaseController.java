package com.uch.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.uch.finalproject.bean.MySqlConfigBean;

public class BaseController {
    @Autowired
    protected MySqlConfigBean mySqlConfigBean;

    protected Connection conn = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;

    protected void connect(String connectionString) throws ClassNotFoundException, SQLException {
        Class.forName(mySqlConfigBean.getDriverClassName());

        conn = DriverManager.getConnection(mySqlConfigBean.getUrlFood() + "?user=" + mySqlConfigBean.getUsername() 
            + "&password=" + mySqlConfigBean.getPassword());
    }
}
