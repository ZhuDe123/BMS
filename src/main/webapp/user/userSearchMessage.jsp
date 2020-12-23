<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/12/3/0003
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userUpdate.css">

</head>
<body>

<div class="rg_layout">
    <div class="rg_left">
        <p>用户信息</p>
        <p>USER CORRECT</p>

    </div>

    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 form-->
            <table>
                <tr>
                    <td class="td_left"><label for="username">用户名</label></td>
                    <td class="td_right"><input type="text" name="username" id="username" value="${searchUsers.username}"
                                                placeholder="请输入用户名"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="password">密码</label></td>
                    <td class="td_right"><input type="text" name="password" id="password" value="${searchUsers.password}"
                                                placeholder="请输入密码"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="email">Email</label></td>
                    <td class="td_right"><input type="email" name="email" id="email" value="${searchUsers.email}"
                                                placeholder="请输入邮箱"></td>
                </tr>

                <tr>
                    <td class="td_left"><label for="tel">手机号</label></td>
                    <td class="td_right"><input type="text" name="phone" id="tel" value="${searchUsers.phone}"
                                                placeholder="请输入手机号"></td>
                </tr>

                <tr>
                    <td class="td_left"><label>性别</label></td>
                    <td class="td_right">
                        <c:choose>
                            <c:when test="${searchUsers.sex == '男'}">
                                <input type="radio" name="sex" value="男" checked="checked"> 男
                                <input type="radio" name="sex" value="女"> 女 </c:when>
                            <c:otherwise>
                                <input type="radio" name="sex" value="男"> 男
                                <input type="radio" name="sex" value="女" checked="checked"> 女
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center"><button class="btn_sub" onclick="window.location.href='${pageContext.request.contextPath}/manager/safe/searchUser?pageIndex=${searchPage.pageIndex}&searchUsername=${searchUsername}&searchPhone=${searchPhone}'">返回</button> </td>

                </tr>
            </table>
        </div>

    </div>


</div>

</body>
</html>
