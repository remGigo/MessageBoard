package cn.itcast.web.servlet;

import cn.itcast.domain.Message;
import cn.itcast.domain.User;
import cn.itcast.service.MessageService;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.MessageServiceImpl;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addMessageServlet")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象，其实这步只得到了一个message的属性
        Message message = new Message();
        try {
            BeanUtils.populate(message,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //其他的message的属性(user_id)需要借助域对象session中获取
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user"); //这个user当然是当前已登录用户的啦
        String username = user.getUsername();

        //还要再调用个service把username对应的user_id查出来
        MessageServiceImpl nameToIdService = new MessageServiceImpl();
        int id = nameToIdService.nameToId(username);
        message.setUser_id(id); //只有清楚了populate方法的功能才能写出这样一句代码

        //4.调用Service保存
        MessageService service = new MessageServiceImpl();
        service.addMessage(message);

        //5.跳转到userListServlet
        response.sendRedirect(request.getContextPath()+"/findMessageByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
