<%@ page import="com.zhude.entity.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/12/3/0003
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询所有用户</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Assets/css/thems.css">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<!--头部盒子-->

<div class="header clearfix">
    <div class="head_l">
        <div class="logo"><a href="">&nbsp;</a></div>
        <span><a href="">BMS管理界面</a></span>
    </div>
    <div class="head_r">
        <span style="color: white;font-size: 15px">欢迎您，${empManager.username}${user.username} 管理员 ！</span>
        <a href="${pageContext.request.contextPath}/Init">图片管理</a>
        <a href="${pageContext.request.contextPath}/manager/Cancellation">注销</a>
    </div>
</div>

<!--主界面盒子-->

<div class="maina">

    <!--查询盒子-->

    <div class="refer">
        <form action="${pageContext.request.contextPath}/manager/safe/searchUser" method="post">
            <span class="name">用户名：<input name="searchUsername" type="text" value="${searchUsername}"></span>
            <span class="tel">联系方式：<input name="searchPhone" type="text" value="${searchPhone}"></span>
<%--            <a href="">查 询</a>--%>
            <input type="submit" value="查询">
        </form>
    </div>

    <!--表框盒子-->

    <table border="0" cellpadding="0" cellspacing="0">
        <tr class="title">
            <th width="125" scope="col">编号</th>
            <th width="165" scope="col">用户名</th>
            <th width="92" scope="col">性别</th>
            <th width="200" scope="col">密码</th>
            <th width="200" scope="col">手机号</th>
            <th width="200" scope="col">邮箱</th>
            <th width="113" scope="col">操作</th>
        </tr>
        <c:forEach var="emp" items="${emps}">
            <tr>
                <td class="empId">${emp.id}</td>
                <td class="empUsername">${emp.username}</td>
                <td class="empSex">${emp.sex}</td>
                <td class="empPassword">${emp.password}</td>
                <td class="empPhone">${emp.phone}</td>
                <td class="empEmail">${emp.email}</td>
                <td>
                    <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showUserController?id=${emp.id}&pageIndex=${page.pageIndex}&userMessage=find'></c:url>">查看</a>&nbsp|
                    <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showUserController?id=${emp.id}&pageIndex=${page.pageIndex}'></c:url>">编辑</a>&nbsp|
                    <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/removeUserController?id=${emp.id}&pageIndex=${page.pageIndex}'></c:url>">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <!--分页-->
    <div class="page">

        <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showAllUser?pageIndex=1' ></c:url> ">首页</a>
        <c:choose>
            <c:when test="${page.pageIndex > 1}">
                <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showAllUser?pageIndex=${page.pageIndex - 1}' />">上一页</a>
            </c:when>
            <c:otherwise>
                <a>上一页</a>
            </c:otherwise>
        </c:choose>
        <a href="">1</a>
        <a href="">2</a>
        <a href="">3</a>
        <a href="">4</a>
        <a href="">5</a>
        <c:choose>
            <c:when test="${page.pageIndex < page.totalPages}">
                <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showAllUser?pageIndex=${page.pageIndex + 1}' />">下一页</a>
            </c:when>
            <c:otherwise>
                <a>下一页</a>
            </c:otherwise>
        </c:choose>
        <a href="<c:url context='${pageContext.request.contextPath}' value='/manager/safe/showAllUser?pageIndex=${page.totalPages}'></c:url> ">尾页</a>
    </div>

</div>
</body>
</html>
