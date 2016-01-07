<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %><%--
  Created by IntelliJ IDEA.
  User: baojun
  Date: 2016/1/6
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String contextPath = request.getContextPath();%>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <jsp:include page="../include.jsp"></jsp:include>
    <link href="<%=contextPath%>/static/styles/signin.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<div class="container">
    <form class="form-signin" action="<%=contextPath%>/login" method="post">
        <h2 class="form-signin-heading">登录</h2>
        <%
            String error = (String) request.getAttribute("error");
            if(error != null){
        %>
        <div class="alert alert-error controls input-large">
            <button class="close" data-dismiss="alert">×</button>
            <%
                out.print(error);
            %>
        </div>
        <%
            }
        %>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input type="text" id="inputEmail" name="loginname" class="form-control" placeholder="loginname" required
               autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住密码
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div> <!-- /container -->
</body>
</html>
