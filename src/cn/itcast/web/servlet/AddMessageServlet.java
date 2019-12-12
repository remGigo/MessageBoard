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
        //3.封装对象
        Message message = new Message();
        try {
            BeanUtils.populate(message,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //添加留言功能也没那么简单
        HttpSession session = request.getSession();

//        String username = session.getAttribute("user").username; 这真没办法 必须分开写

        User user = (User)session.getAttribute("user"); //这两句真的考javaSE基本功
        String username = user.getUsername();

        //还要再写个service把username对应的user_id查出来
        MessageServiceImpl nameToIdService = new MessageServiceImpl();
        int id = nameToIdService.nameToId(username);
        message.setUser_id(id); //只有清楚了populate方法的功能才能写出这样一句代码


        //4.调用Service保存
        MessageService service = new MessageServiceImpl();
        service.addMessage(message);

        //5.跳转到userListServlet   哥！你这不是坑我呢？
//        response.sendRedirect(request.getContextPath()+"/userListServlet");
        response.sendRedirect(request.getContextPath()+"/findMessageByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
