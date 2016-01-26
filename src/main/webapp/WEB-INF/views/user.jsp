<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <jsp:include page="include.jsp"></jsp:include>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div class="page-header">
    <h1>用户列表--<a href="/user/add">添加用户</a>---<a href="/logout">退出登录</a>
        <small></small>
    </h1>
</div>
<h2>权限列表</h2>
<shiro:authenticated>用户已经登录显示此内容</shiro:authenticated>
<shiro:hasRole name="manager">manager角色登录显示此内容</shiro:hasRole>
<shiro:hasRole name="admin">admin角色登录显示此内容</shiro:hasRole>
<shiro:hasRole name="normal">normal角色登录显示此内容</shiro:hasRole>

<shiro:hasAnyRoles name="manager,admin">**manager or admin 角色用户登录显示此内容**</shiro:hasAnyRoles>
<shiro:principal/>-显示当前登录用户名
<shiro:hasPermission name="add">add权限用户显示此内容</shiro:hasPermission>
<shiro:hasPermission name="user:query">query权限用户显示此内容<shiro:principal/></shiro:hasPermission>
<shiro:lacksPermission name="user:del"> 不具有user:del权限的用户显示此内容 </shiro:lacksPermission>
<div class="row">
    <div class="col-md-6">
        <table class="table table-hover table-bordered">
            <thead>
            <th>用户名</th>
            <th>密码</th>
            <th>操作dd</th>
            </thead>
            <c:forEach items="${userList}" var="user">
                <tr class="success">
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td><a href="/user/edit/${user.id}">修改用户</a>
                        <a href="javascript:;" class="del" ref="${user.id}">删除用户</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    $(function () {
        $(".del").click(function () {
            var id = $(this).attr("ref");
            $.ajax({
                type: "delete",
                url: "/user/del/" + id,
                success: function (e) {

                }
            });
        });
    });
</script>
</body>
</html>
