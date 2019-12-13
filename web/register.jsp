<%--
  Created by IntelliJ IDEA.
  User: 王若添
  Date: 2019/12/13
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>管理员登录</title>

        <!-- 1. 导入CSS的全局样式 -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
        <script src="js/jquery-2.1.0.min.js"></script>
        <!-- 3. 导入bootstrap的js文件 -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            //切换验证码
            function refreshCode(){
                //1.获取验证码图片对象
                var vcode = document.getElementById("vcode");

                //2.设置其src属性，加时间戳
                vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
            }
        </script>
    </head>
</head>
<body>
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">注册</h3>

        <form action="${pageContext.request.contextPath}/registerServlet" method="post">
            <div class="form-group">
                <%--            为啥原来for和id后面都是user了--%>
                <label for="username">用户名：</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
            </div>


            <hr/>
            <div class="form-inline" style="text-align: center;">
                <input class="btn btn btn-primary" type="submit" value="注册">

            </div>

        </form>


    </div>
</body>

</body>
</html>
