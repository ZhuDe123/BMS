<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/12/19/0019
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
</head>
<body>

<div class="rg_layout">
    <div class="rg_left">
        <p>新用户注册</p>
        <p>USER REGISTER</p>

    </div>

    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 form-->
            <form action="${pageContext.request.contextPath}/manager/Register" method="post">
                <table>
                    <tr>
                        <td class="td_left"><label for="username">用户名</label></td>
                        <td class="td_right"><input type="text" name="username" id="username" onblur="checkUsername()" placeholder="请输入用户名"><span id="usernamespan"></span></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="password">密码</label></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="password2">确认密码</label></td>
                        <td class="td_right">
                            <input type="password" name="password" id="password2" placeholder="请再次输入密码" onblur="checkRePassword()">
                            <span id="checkRePassword"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="email">Email</label></td>
                        <td class="td_right">
                            <input type="email" name="email" id="email" placeholder="请输入邮箱">
                            <span id="EmailSpan"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="tel">手机号</label></td>
                        <td class="td_right"><input type="text" name="phone" id="tel" placeholder="请输入手机号"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label>性别</label></td>
                        <td class="td_right">
                            <input type="radio" name="sex" value="男"> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="checkCode">验证码</label></td>

                        <td class="td_right">
                            <input type="text" name="emailCode"  id="checkCode" placeholder="请输入验证码" onblur="identifyEmailCode()">
                            <button id="sendEmailButton" type="button" onclick="sendEmail()">发送验证码</button><br>
                            <span id="emailButtonSpan"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center" onclick="register()"><input type="submit" id="btn_sub" value="注册"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>已有账号?<a href="${pageContext.request.contextPath}/user/login.jsp">立即登录</a></p>
    </div>
</div>
</body>
<script type="text/javascript" src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/register.js"></script>
</html>
