<%--<jsp:useBean id="condition" scope="request" type="javax.xml.stream.util.StreamReaderDelegate"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head >
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteUser(id){
            //用户安全提示
            if(confirm("您确定要删除吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
            }
        }

        window.onload = function(){
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function(){
                if(confirm("您确定要删除选中条目吗？")){

                   var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked){
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    if(flag){//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }

            }
            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function(){
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;

                }

            }


        }


    </script>
</head>



<body>

<div class="container">
    <h3 style="text-align: center">NBA留言板</h3>

    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">我也写一条留言</a>
    </div>


<%--下面这个表单是核心功能--%>
        <table border="1" class="table table-bordered table-hover">

            <tr class="success">
                <th>编号</th>
                <th>姓名</th>
                <th>     留   言    </th>
                <th>操作</th>
            </tr>


            <c:forEach items="${pb.list}" var="message" varStatus="s">

                <tr>
                    <td>${s.count}</td>
                    <td>
                        ${message.username}
<%--                        <%=session.getAttribute("user")%>--%>
                    </td>
                    <td>${message.message}</td>
                    <td><a href="${pageContext.request.contextPath}/delMessageServlet?id=${message.id}">删除该留言</a></td>
<%--                   这输了个3哈哈哈哈挺好玩儿--%>

                </tr>

            </c:forEach>

        </table>



<%--    下面是页脚控制--%>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">


                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                </c:if>

                <c:if test="${pb.currentPage != 1}">
                    <li>
                </c:if>


                    <a href="${pageContext.request.contextPath}/findMessageByPageServlet?currentPage=${pb.currentPage - 1}&rows=5" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>


<%--使用foreach标签生成页码按键，且选中页显示特殊状态 --%>
                <c:forEach begin="1" end="${pb.totalPage}" var="i" >

<%--                        如果是本页--%>
                        <c:if test="${pb.currentPage == i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/findMessageByPageServlet?currentPage=${i}&rows=5">${i}</a>
                            </li>
                        </c:if>
<%--                        如果是其他页--%>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/findMessageByPageServlet?currentPage=${i}&rows=5">${i}</a>
                            </li>
                        </c:if>

                </c:forEach>





<%--下一页功能--%>
<%--如果最后一页我故意没有设置禁点的效果,为了你和第一页有个对比,要实现的话还是只用一个if的estl标签就可以--%>
                <li>
                    <a href="${pageContext.request.contextPath}/findMessageByPageServlet?currentPage=${pb.currentPage + 1}&rows=5" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>


<%--留言统计--%>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

            </ul>
        </nav>


    </div>


</div>


</body>
</html>
