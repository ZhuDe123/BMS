<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/11/27/0027
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>用户登录</p><br>
        <p>USER LOGIN</p>
    </div>
    <div class="rg_right">
        <p>没有账号?<a href="${pageContext.request.contextPath}/user/register.jsp">立即注册</a></p>
    </div>

    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 form-->
            <form action="${pageContext.request.contextPath}/manager/userManagerLogin" method="post">
                <table>
                    <tr>
                        <td class="td_left"><label for="username">用户名</label></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="password">密码</label></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="inputVCode" >验证码</label></td>
                        <td class="td_right">
                            <input type="text" name="inputVCode" id="inputVCode" placeholder="请输入验证码">
                            <img src="${pageContext.request.contextPath}/creatCode"><br>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center"><input type="submit" id="btn_sub" value="登录"></td>
                    </tr>

                </table>

            </form>

        </div>

    </div>

</div>
</body>
</html>
