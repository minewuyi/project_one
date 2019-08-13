package com.dao;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wuyi
 * @date 2019/8/13 15:41
 */
public class UserDao {
    public static User selectUser(String userName, String passWord) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "SELECT id,userName from user where userName= ? and passWord= ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, passWord);
        ResultSet rs = pstm.executeQuery();
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("userName"));
            conn.close();
            return user;
        } else {
            conn.close();
            return null;
        }
    }

    public static int addUser(String username, String password, String sex,
                              String hobby, String phone, String email, String addrs, String flag) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "INSERT INTO user(username, password,sex,hobbys, phone, email, addrs, flag) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, sex);
        pstm.setString(4, hobby);
        pstm.setString(5, phone);
        pstm.setString(6, email);
        pstm.setString(7, addrs);
        pstm.setString(8, flag);
        int result=pstm.executeUpdate();
        conn.close();
        return result;
    }
}
