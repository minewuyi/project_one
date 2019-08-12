package com.servlet;

import com.dao.SqlDao;
import com.entity.GoodsInfo;
import com.utils.StringToInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author wuyi
 * @date 2019/8/12 11:57
 */
public class BaseServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String opr = req.getParameter("opr");
        if (opr.equals("show")) {
            show(req, resp);
        } else if (opr.equals("showOne")) {
            showOne(req, resp);
        } else if (opr.equals("update")) {
            update(req, resp);
        } else if (opr.equals("doUpdate")) {
            String id = req.getParameter("id");
//            System.out.println(id);
            String goodsInfoName = req.getParameter("goodsInfoName");
            String goodsInfoPic = req.getParameter("goodsInfoPic");
            String goodsInfoPrice = req.getParameter("goodsInfoPrice");
            String goodsInfoDescription = req.getParameter("goodsInfoDescription");
            String goodsStock = req.getParameter("goodsStock");
            String flag = req.getParameter("flag");
            System.out.println(flag);
            System.out.println(goodsStock);


            try {
                SqlDao.update(StringToInt.str2Int(id), goodsInfoName, goodsInfoPic, StringToInt.str2Float(goodsInfoPrice),
                        goodsInfoDescription, StringToInt.str2Int(goodsStock), flag);
                show(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    //此方法只是获取要修改的信息跳转到修改界面 doUpdate才是真正的修改
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        GoodsInfo good = null;
        try {
            good = SqlDao.showOne(StringToInt.str2Int(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("good", good);
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    //展示单个商品信息 详细
    private void showOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            GoodsInfo good = SqlDao.showOne(StringToInt.str2Int(id));
            req.setAttribute("good", good);
            req.getRequestDispatcher("showOne.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //展示所有商品的简易信息
    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<GoodsInfo> list = SqlDao.showAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("show.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
