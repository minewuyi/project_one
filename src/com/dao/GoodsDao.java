package com.dao;

import com.entity.GoodsInfo;
import com.utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author wuyi
 * @date 2019/8/12 12:28
 */
public class GoodsDao {

// 用于主页简短展示的商品信息 不包含全部信息

    public static ArrayList<GoodsInfo> showAll() throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "select id,goodsInfoName,goodsInfoPrice,goodsStock,flag from goodsinfo";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<GoodsInfo> result = new ArrayList<GoodsInfo>();
        while (rs.next()) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(rs.getInt("id"));
            goodsInfo.setGoodsInfoName(rs.getString("goodsInfoName"));
            goodsInfo.setGoodsInfoPrice(rs.getInt("goodsInfoPrice"));
            goodsInfo.setGoodsStock(rs.getInt("goodsStock"));
            if (rs.getString("flag").equals("true")){
                goodsInfo.setFlag("激活");
            }else{
                goodsInfo.setFlag("禁用");
            }
            result.add(goodsInfo);
        }
        conn.close();
        return result;
    }

    // 展示当个商品的所有信息 包括描述图片等
    public static GoodsInfo showOne(int id) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "select * from goodsinfo where id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setId(rs.getInt("id"));
            goodsInfo.setGoodsInfoName(rs.getString("goodsInfoName"));
            goodsInfo.setGoodsInfoPic(rs.getString("goodsInfoPic"));
            goodsInfo.setGoodsInfoPrice(rs.getInt("goodsInfoPrice"));
            goodsInfo.setGoodsInfoDescription(rs.getString("goodsInfoDescription"));
            goodsInfo.setGoodsStock(rs.getInt("goodsStock"));
            if (rs.getString("flag").equals("true")){
                goodsInfo.setFlag("激活");
            }else{
                goodsInfo.setFlag("禁用");
            }
            goodsInfo.setCreated(rs.getInt("created"));
            goodsInfo.setCreatedDate(rs.getTimestamp("createdDate"));
            conn.close();
            return goodsInfo;
        }

        conn.close();
        return null;
    }

    // 通过id删除单个商品
    public static int delete(int id) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "delete from goodsinfo where id= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        int result = pst.executeUpdate();
        conn.close();
        return result;
    }

    // 更新方法
    public static int update(
            int id,
            String goodsInfoName,
            String goodsInfoPic,
            float goodsInfoPrice,
            String goodsInfoDescription,
            int goodsStock,
            String flag
    ) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "update goodsinfo set goodsInfoName=?,goodsInfoPic=?,goodsInfoPrice=?,goodsInfoDescription=?,goodsStock=?,flag=? where id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, goodsInfoName);
        pst.setString(2, goodsInfoPic);
        pst.setFloat(3, goodsInfoPrice);
        pst.setString(4, goodsInfoDescription);
        pst.setInt(5, goodsStock);
        pst.setString(6, flag);
//        pst.setInt(7, created);
        pst.setInt(7, id);
        int resNum = pst.executeUpdate();
        return resNum;
    }

    //新增加商品 有些时区问题
    public static int add(
            String goodsInfoName,
            String goodsInfoPic,
            float goodsInfoPrice,
            String goodsInfoDescription,
            int goodsStock,
            String flag,
            int created
    ) throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "INSERT INTO goodsinfo (goodsInfoName,goodsInfoPic,goodsInfoPrice,goodsInfoDescription," +
                "goodsStock,flag,created,createdDate) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, goodsInfoName);
        pst.setString(2, goodsInfoPic);
        pst.setFloat(3, goodsInfoPrice);
        pst.setString(4, goodsInfoDescription);
        pst.setInt(5, goodsStock);
        pst.setString(6, flag);
        pst.setInt(7, created);
        pst.setTimestamp(8, DateUtil.timestamp()); //因为数据库里日期类型是timestamp类型，所以设置也为相同类型
        int resNum = pst.executeUpdate();
        return resNum;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(showOne(1));
    }
}
