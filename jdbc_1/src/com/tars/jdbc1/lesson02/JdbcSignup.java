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
        if(id == 0) System.out.println("您的用户名已经被占用，注册失败；");
        else System.out.println("注册成功，您的id为"+id+"\n");


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



                //id值自增
            PreparedStatement temPreSta = con.prepareStatement("SELECT MAX(id) AS id FROM users;");
            ResultSet resset = temPreSta.executeQuery();
            resset.next();
            int id = resset.getInt("id");
            result = id+1;
            JdbcUtil.release(null, temPreSta, resset);//防止内存泄漏
                //验证用户名是否重复
            PreparedStatement temPreSta2 = con.prepareStatement("SELECT NAME FROM users WHERE NAME = ?;");
            temPreSta2.setString(1, username);
            ResultSet tempRes = temPreSta2.executeQuery();
            if(tempRes.next())  return 0;

            //手动给参数赋值
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
