package com.tars.jdbc1.lesson02;

import com.tars.jdbc1.lesson02.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class JdbcSignup {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = null;
        String email = null;
        String password = null;
        System.out.println("进入注册程序");
        System.out.println("输入您的用户名");
        name = in.nextLine();
        System.out.println("输入您的密码");
        password = in.nextLine();
        System.out.println("输入您的电子邮箱");
        email = in.nextLine();

        int id = signup(name, password, email);
        System.out.println("注册成功，您的id为"+id+"\n");


    }


    public static int signup(String username, String password, String email){

        Connection con = null;
        PreparedStatement sta = null;
        int result = 0;
        //获取连接
        try {
            String sql = "INSERT INTO users (id, NAME, PASSWORD, emai1, birthday) VALUES (?,?,?,?,?);";
            con = JdbcUtil.getConnection();
            sta = con.prepareStatement(sql);//预编译sql语句， 先写sql但不执行

            //手动给参数赋值
            ResultSet resset = con.prepareStatement("SELECT MAX(id) AS id FROM users;").executeQuery();
            resset.next();
            int id = resset.getInt("id");//id值自增
            result = id+1;
            sta.setInt(1, id+1);
            sta.setString(2,username);
            sta.setString(3,password);
            sta.setString(4,email);
            sta.setDate(5,new java.sql.Date(new Date().getTime()) );

            sta.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.release(con, sta, null);
        }
        return result;
    }
}
