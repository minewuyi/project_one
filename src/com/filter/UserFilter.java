package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author wuyi
 * @date 2019/8/13 18:52
 */


//@WebFilter(
//        filterName = "userFilter",
//        urlPatterns = "/"
//)
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //基本三步
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpSession session=request.getSession();

        String username=(String) session.getAttribute("username");

        String path = request.getRequestURI();
        System.out.println(path);
        if (path.equals("/login.jsp")||path.equals("/error.jsp")||path.equals("/loginServlet")||path.equals("/register.jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if ("".equals(username)||username==null){
            System.out.println("do");
            request.setAttribute("error","<h1>请登陆后查看</h1>");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }













    }

    @Override
    public void destroy() {

    }
}
