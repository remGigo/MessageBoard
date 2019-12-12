package cn.itcast.web.servlet;

import cn.itcast.domain.MessageToShow;
import cn.itcast.domain.PageBean;
import cn.itcast.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/findMessageByPageServlet")
public class FindMessageByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //自己写的
        HttpSession session = request.getSession();
        session.setAttribute("user_id",request.getParameter("username"));


        //1.从客户端获取参数，如果没有传入则设定初始默认值
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数


        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }


        //2.调用service查询
//        UserService service = new UserServiceImpl();
        MessageServiceImpl service = new MessageServiceImpl();
        PageBean<MessageToShow> pb = service.findMessageByPage(currentPage,rows);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);

        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
