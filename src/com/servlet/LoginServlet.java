package com.servlet;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author wuyi
 * @date 2019/8/13 15:34
 */
public class LoginServlet extends HttpServlet {

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
        if (opr.equals("login")){
            login(req, resp);
        }else if (opr.equals("register")){
            addUser(req, resp);
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String hobby = req.getParameter("hobby");
        String tel = req.getParameter("tel");
        String email = req.getParameter("email");
        String addrs = req.getParameter("addrs");
        String flag = req.getParameter("flag");
        PrintWriter out=resp.getWriter();
        try {
           int result= UserDao.addUser(username,password,sex,hobby,tel,email,addrs,flag);
            if (result > 0) {
                out.print("<script>alert(\"注册成功！\");location.href='/login.jsp'</script>");
            } else {
                out.print("<script>alert(\"注册失败！\");history.back()</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = UserDao.selectUser(username, password);
            if (user == null) {
                req.setAttribute("error","<h1>用户名或密码错误</h1>");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            } else {
                //创建会话对象
                HttpSession session=req.getSession();
                //将username值加入会话内，接下来多个页面都可以使用
                session.setAttribute("user",user);
                req.getRequestDispatcher("/baseServlet?opr=show").forward(req, resp);
            }
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }
}
