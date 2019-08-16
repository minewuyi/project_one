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
            if (rs.getString("flag").equals("true")) {
                goodsInfo.setFlag("激活");
            } else {
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
        String sql = "SELECT g.* ,  u.username FROM goodsinfo as g , `user` as u   WHERE  g.id=?  AND u.id=g.created;";
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
            goodsInfo.setCreatedName(rs.getString("username"));
            if (rs.getString("flag").equals("true")) {
                goodsInfo.setFlag("激活");
            } else {
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

    //多条件查询
    public static ArrayList<GoodsInfo> findGoods(GoodsInfo goodsInfo) throws SQLException {
        PreparedStatement prs = null;
        ResultSet rs = null;
        Connection conn = BaseDao.getConnection();
        ArrayList<GoodsInfo> list = new ArrayList<>();
        try {
            // 1、获得连接对象
            // 2、获得sql语句
            StringBuffer sf = new StringBuffer();
            sf.append(" select * from goodsinfo where 1=1 ");
            ArrayList paramList = new ArrayList();
            if (goodsInfo != null) {
                //通过id查询
                if (goodsInfo.getId() > 0) {
                    sf.append(" and id = ? ");
                    paramList.add(goodsInfo.getId());
                }
                //通过名字查询
                if (goodsInfo.getGoodsInfoName() != null && !goodsInfo.getGoodsInfoName().equals("")) {
                    sf.append(" and goodsInfoName = ?");
                    paramList.add(goodsInfo.getGoodsInfoName());
                }
                //通过价格查询
                if (goodsInfo.getGoodsInfoPrice() >= 0) {
                    sf.append(" and goodsInfoPrice = ? ");
                    paramList.add(goodsInfo.getGoodsInfoPrice());
                }
                //通过库存查询
                if (goodsInfo.getGoodsStock() > 0) {
                    sf.append(" and goodsStock = ? ");
                    paramList.add(goodsInfo.getGoodsStock());
                }
                //通过状态查询
                if (goodsInfo.getFlag() != null && !goodsInfo.getFlag().equals("")) {
                    sf.append(" and flag = ? ");
                    paramList.add(goodsInfo.getFlag());
                }
                //通过创建人id查询
                if (goodsInfo.getCreated() > 0) {
                    sf.append(" and created = ? ");
                    paramList.add(goodsInfo.getCreated());
                }
                //通过创建日期查询
                if (goodsInfo.getCreatedDate() != null && !goodsInfo.getCreatedDate().equals("")) {
                    sf.append("and createdDate = ?");
                    paramList.add(goodsInfo.getCreatedDate());
                }
            }
            // 3、获得预编译语句集
            prs = conn.prepareStatement(sf.toString());
            // 4、设置占位符的值
            if (paramList != null && paramList.size() > 0) {
                for (int i = 0; i < paramList.size(); i++) {
                    prs.setObject(i + 1, paramList.get(i));
                }
            }
            // 5、执行sql语句并获得结果集
            rs = prs.executeQuery();

            // 6、遍历
            while (rs.next()) {
                GoodsInfo entity = new GoodsInfo();
                entity.setId(rs.getInt("id"));
                entity.setGoodsInfoName(rs.getString("goodsInfoName"));
                entity.setGoodsInfoPic(rs.getString("goodsInfoPic"));
                entity.setGoodsInfoPrice(rs.getFloat("goodsInfoPrice"));
                entity.setGoodsInfoDescription(rs.getString("goodsInfoDescription"));
                entity.setGoodsStock(rs.getInt("goodsStock"));
                if (rs.getString("flag").equals("true")) {entity.setFlag("激活");}
                else {entity.setFlag("禁用");}
                entity.setCreated(rs.getInt("created"));
                entity.setCreatedDate(rs.getTimestamp("createdDate"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
}

//    public static void main(String[] args) throws SQLException {
//        System.out.println(showOne(1));
//    }
//}
