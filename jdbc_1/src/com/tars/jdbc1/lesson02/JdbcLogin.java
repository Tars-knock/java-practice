package com.tars.jdbc1.lesson02;

import com.tars.jdbc1.lesson02.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcLogin {

    public static void main(String[] args) {
        //输入用户名 密码
        //SQL注入  则输入：name:“or” 1=1 password:"or" 1=1
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();
        System.out.println("password:");
        String password = in.nextLine();

        if(login(name, password)) System.out.println("sign in successfully");
        else System.out.println("Please check your username or password");
    }

    //连接数据库进行检查
    public static boolean login(String username, String password) {

        Connection con = null;
        Statement sta = null;
        ResultSet res = null;
        boolean result = false;

        try {
            con = JdbcUtil.getConnection();
            sta = con.createStatement();
            res = sta.executeQuery("SELECT * FROM users WHERE NAME = \""+username+"\" AND PASSWORD = \""+password+"\";");

            result = res.next();
            while(res.next()){
                System.out.println(res.getObject("name"));
                System.out.println(res.getObject("password"));
                System.out.println("==========");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.release(con, sta, res);
        }
        return  result;

    }
}
