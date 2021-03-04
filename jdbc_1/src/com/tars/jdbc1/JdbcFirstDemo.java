package com.tars.jdbc1;

import java.sql.*;

public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //使用forName的方式加载驱动，驱动文件中的static域会被执行；从而达到驱动的目的
        Class.forName("com.mysql.cj.jdbc.Driver");

        //用户信息与url
        String URL =
                "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "password";
        //进行连接, connection代表数据库对象
        Connection connection = DriverManager.getConnection(URL, username, password);
        //执行sql的对象 statement
        Statement statement = connection.createStatement();
        //执行sql的对象去执行算起来语句，可能存在结果，查看返回结果
        String sql = "SELECT * FROM product";

        ResultSet resultSet = statement.executeQuery(sql);//返回的结果集包含了所有查询的结果

        while(resultSet.next()){
            System.out.println("id = "+resultSet.getObject("product_id"));
            System.out.println("name = "+resultSet.getObject("product_name"));
            System.out.println("type = "+resultSet.getObject("product_type"));
            System.out.println("sale_price = "+resultSet.getObject("sale_price"));
            System.out.println("purchase_price = "+resultSet.getObject("purchase_price"));
            System.out.println("regist_date = "+resultSet.getObject("regist_date"));
            System.out.println("=============");
        }

        //释放连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
