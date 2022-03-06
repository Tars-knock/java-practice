package cn.tars.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    static {
        System.out.println("类被实例化");
    }
    public HelloServlet(){
        super();
        System.out.println("构造方法被调用");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer =  resp.getWriter(); //相应流
        writer.println("hello servlet");
        System.out.println("调用了doPost方法");
        ServletContext context = this.getServletContext();
        writer.println(context.getInitParameterNames());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    public void destroy() {
        System.out.println("servlet被销毁");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init()方法被调用");
    }
}
