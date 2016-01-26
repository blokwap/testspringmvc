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
    <title>LoginIn</title>
    <jsp:include page="../include.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<div style="width:450px;height:200px;position:relative;margin:100px auto 0px auto;">
    <form class="form-horizontal" action="<%=contextPath%>/login" method="post">
        <div class="form-group">
            <label for="inputUsername" class="col-lg-2 control-label"><p class="gray-lighter">用户名</p></label>
            <div class="col-lg-6">
                <input type="text" id="inputUsername" name="username" class="form-control" placeholder="loginname"
                       required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-lg-2 control-label"><p class="color-swatch gray-lighter">密码</p></label>
            <div class="col-lg-6">
                <input type="password" id="inputPassword" name="password" class="form-control"
                       placeholder="password"
                       required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> <p class="color-swatch gray-lighter">记住密码</p>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-6">
                <button type="submit" class="btn btn-warning btn-block">登陆</button>
            </div>
        </div>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="sr-only">Error:</span>
            <%
                out.print(error);
            %>
        </div>
        <%
            }
        %>
    </form>
</div>

</body>
</html>
