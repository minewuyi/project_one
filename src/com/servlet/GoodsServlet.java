package com.servlet;

import com.dao.GoodsDao;
import com.entity.GoodsInfo;
import com.utils.StringToInt;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        //创建工厂
        DiskFileItemFactory factoy = new DiskFileItemFactory();
        //创建解析器
        ServletFileUpload sfu = new ServletFileUpload(factoy);
        //接收获得的结果集
        try {
            List<FileItem> list = sfu.parseRequest(req);
            String goodsInfoName = list.get(0).getString("utf-8");
            String goodsInfoPrice = list.get(2).getString("utf-8");
            String goodsInfoDescription = list.get(3).getString("utf-8");
            String goodsStock = list.get(4).getString("utf-8");
            String created = list.get(5).getString("utf-8");
            String flag = list.get(6).getString("utf-8");
            String name;

            if (!"".equals(list.get(1).getName())) {
                //获取要写入的目录
                String path = this.getServletContext().getRealPath("/upload");
//        判断该目录是否存在
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String fileName = list.get(1).getName();
//            获取文件后缀名
                int dot = fileName.lastIndexOf('.');
                String last = fileName.substring(dot);
                System.out.println(last);
                //设置随机文件名 避免重复文件名

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = df.format(new Date());
                String newFileName = goodsInfoName + "+" + created + "+" + date + last;
                //使用目录和文件名创建目标文件
                File newFile = new File(path, newFileName);
                //保存文件
                list.get(1).write(newFile);
                //获取相对路径已存到数据库
                name = "/upload/" + newFile.getName();
                System.out.println(name);
            } else {
                name=null;
            }
            int result = GoodsDao.add(goodsInfoName, name, StringToInt.str2Float(goodsInfoPrice),
                    goodsInfoDescription, StringToInt.str2Int(goodsStock), flag, StringToInt.str2Int(created));
            if (result > 0) {
                out.print("<script>alert(\"添加成功！\");location.href='/baseServlet?opr=show'</script>");
            } else {
                out.print("<script>alert(\"添加失败！\");history.back()</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //更新方法
    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        //创建工厂
        DiskFileItemFactory factoy = new DiskFileItemFactory();
        //创建解析器
        ServletFileUpload sfu = new ServletFileUpload(factoy);


        try {
            List<FileItem> list = sfu.parseRequest(req);
            String id = list.get(0).getString("utf-8");
            String goodsInfoName = list.get(1).getString("utf-8");
            String goodsInfoPrice = list.get(3).getString("utf-8");
            String goodsInfoDescription = list.get(4).getString("utf-8");
            String goodsStock = list.get(5).getString("utf-8");
            String created = list.get(6).getString("utf-8");
            String flag = list.get(7).getString("utf-8");
            String old = list.get(8).getString("utf-8");


            //获取要写入的目录
            String name;
            if (!"".equals(list.get(2).getName())) {
                String path = this.getServletContext().getRealPath("/upload");

                //获取文件后缀名
                String fileName = list.get(2).getName();
                int dot = fileName.lastIndexOf('.');
                String last = fileName.substring(dot);
                System.out.println(last);


                //设置随机文件名 避免重复文件名
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = df.format(new Date());
                String newFileName = goodsInfoName + "+" + created + "+" + date + last;
                //使用目录和文件名创建目标文件
                File newFile = new File(path, newFileName);
                //保存文件
                list.get(2).write(newFile);
                //获取相对路径已存到数据库
                name = "/upload/" + newFile.getName();
            } else {
                name = old;
            }

            int result = GoodsDao.update(StringToInt.str2Int(id), goodsInfoName, name, StringToInt.str2Float(goodsInfoPrice),
                    goodsInfoDescription, StringToInt.str2Int(goodsStock), flag);
            if (result > 0) {
                out.print("<script>alert(\"更新成功！\");location.href='/baseServlet?opr=show'</script>");
            } else {
                out.print("<script>alert(\"更新失败！\");history.back()</script>");
            }
        } catch (Exception e) {
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
