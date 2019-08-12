package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wuyi
 * @date 2019/8/12 11:37
 */
public class BaseDao {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/project_one?serverTimezone=Asia/Shanghai&useTimezone=true&useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "123456";
    private  static Connection conn;
    //数据库连接方法
    static Connection getConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }
    //数据库关闭方法
    public static void  closeConnection(Connection conn){
        if (conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

