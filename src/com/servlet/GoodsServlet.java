package com.servlet;

import com.dao.GoodsDao;
import com.entity.GoodsInfo;
import com.utils.StringToInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author wuyi
 * @date 2019/8/12 11:57
 */
public class GoodsServlet extends HttpServlet {


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
            doUpdate(req, resp);
        } else if (opr.equals("add")) {
            add(req, resp);
        } else if (opr.equals("delete")) {
            delete(req, resp);
        } else {
            show(req, resp);
        }


    }

    //删除货物
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        try {
            int result = GoodsDao.delete(StringToInt.str2Int(id));
            if (result > 0) {
                out.print("<script>alert(\"删除成功！\");location.href='/baseServlet?opr=show'</script>");
                System.out.println("hellp");
            } else {
                out.print("<script>alert(\"删除失败！\");history.back()</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsInfoName = req.getParameter("goodsInfoName");
        String goodsInfoPic = req.getParameter("goodsInfoPic");
        String goodsInfoPrice = req.getParameter("goodsInfoPrice");
        String goodsInfoDescription = req.getParameter("goodsInfoDescription");
        String goodsStock = req.getParameter("goodsStock");
        String flag = req.getParameter("flag");
        String created = req.getParameter("created");
        PrintWriter out = resp.getWriter();

        try {
            int result = GoodsDao.add(goodsInfoName, goodsInfoPic, StringToInt.str2Float(goodsInfoPrice),
                    goodsInfoDescription, StringToInt.str2Int(goodsStock), flag, StringToInt.str2Int(created));
            if (result > 0) {
                out.print("<script>alert(\"添加成功！\");location.href='/baseServlet?opr=show'</script>");
            } else {
                out.print("<script>alert(\"添加失败！\");history.back()</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新方法
    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String goodsInfoName = req.getParameter("goodsInfoName");
        String goodsInfoPic = req.getParameter("goodsInfoPic");
        String goodsInfoPrice = req.getParameter("goodsInfoPrice");
        String goodsInfoDescription = req.getParameter("goodsInfoDescription");
        String goodsStock = req.getParameter("goodsStock");
        String flag = req.getParameter("flag");
        PrintWriter out = resp.getWriter();
        try {
            int result = GoodsDao.update(StringToInt.str2Int(id), goodsInfoName, goodsInfoPic, StringToInt.str2Float(goodsInfoPrice),
                    goodsInfoDescription, StringToInt.str2Int(goodsStock), flag);
            if (result > 0) {
                out.print("<script>alert(\"添加成功！\");location.href='/baseServlet?opr=show'</script>");
                System.out.println("hellp");
            } else {
                out.print("<script>alert(\"添加失败！\");history.back()</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //此方法只是获取要修改的信息跳转到修改界面 doUpdate才是真正的修改
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        GoodsInfo good = null;
        try {
            good = GoodsDao.showOne(StringToInt.str2Int(id));
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
            GoodsInfo good = GoodsDao.showOne(StringToInt.str2Int(id));
            req.setAttribute("good", good);
            req.getRequestDispatcher("showOne.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //展示所有商品的简易信息
    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<GoodsInfo> list = GoodsDao.showAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("show.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
