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
<div class="container">
    <div class="row">
        <div class="col-md-6">
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
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <table class="table table-hover">
                <thead>
                <th>用户名</th>
                <th>密码</th>
                <th>操作dd</th>
                </thead>
                <c:forEach items="${userList}" var="user">
                    <tr class="success">
                        <td style="vertical-align: middle">${user.username}</td>
                        <td valign="bottom">${user.password}</td>
                        <td>
                            <button id="update" type="button" class="btn btn-primary btn-sm">修改</button>
                            <button id="delete" type="button" class="btn btn-warning btn-sm">删除</button>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#update").click(function(){
            $('#myModal').modal();
        });
        $("#delete").click(function () {
            bootbox.confirm("Are you sure?", function(result) {
                alert(result)
            });

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
