<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: baojun
  Date: 2016/1/6
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <jsp:include page="include.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h2>登录成功, <shiro:principal/></h2>
            <h2><a href="/user/list">用户列表</a></h2>
        </div>
    </div>
</div>

</body>
</html>
