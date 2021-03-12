package com.tars.jdbc1.lesson02;

import com.tars.jdbc1.lesson02.util.JdbcUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
    public static void main(String[] args) {

        Connection con = null;
        Statement sta = null;
        ResultSet res = null;
        //获取连接
        try {
            con = JdbcUtil.getConnection();
            sta = con.createStatement();
            res = sta.executeQuery("SELECT * FROM users;");
            while(res.next()){
                System.out.println(res.getObject("NAME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.release(con, sta, res);
        }



    }
}
