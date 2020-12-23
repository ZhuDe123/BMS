<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/12/18/0018
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/test.css">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        .shortselect{
            background:#fafdfe;
            height:28px;
            width:180px;
            line-height:28px;
            border:1px solid #9bc0dd;
            -moz-border-radius:2px;
            -webkit-border-radius:2px;
            border-radius:2px;
        }
        .uploadB{
            border: 0;
            outline: none;
            margin-left: 15px;
            color: #FFF;
            vertical-align: middle;
            text-align: center;
            width: 72px;
            height: 30px;
            line-height: 30px;
            text-shadow: 1px 0px 2px #333333;
            background: url(../Assets/images/btn_bg1.png) no-repeat;
        }
    </style>
    <script type="text/javascript" rel="script">
        var flag = true;
        function checkAll() {

            var loves = document.getElementsByClassName("sel");
            if(flag){
                for (var i = 0; i < loves.length; i++) {//否则将所有复选框设置为选中
                    loves[i].checked = true;
                }
                flag = false;
            }else {
                for (var i = 0; i < loves.length; i++) {//否则将所有复选框设置为选中
                    loves[i].checked = false;
                }
                flag = true;
            }
        }

        function submitServlet(date) {
            let type = new String(date);
            let form = document.getElementById("delOrDownLoad");
            if(type=="downLoad")
                form.action="${pageContext.request.contextPath}/DownLoadListImg";
            if(type=="del")
                form.action="${pageContext.request.contextPath}/DeleteImgList";
        }
    </script>
</head>
<body>

<!--头部盒子-->

<div class="header clearfix">
    <div class="head_l">
        <span class="logo"><a href="">&nbsp;</a></span>
        <span><a href="">图片管理界面</a></span>
    </div>
    <div class="head_r">
        <span style="color: white;font-size: 15px">欢迎您，${empManager.username}${user.username}</span>
        <c:if test="${empManager != null}">
            <a href="${pageContext.request.contextPath}/manager/safe/showAllUser">用户管理</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/manager/Cancellation">注销</a>
    </div>

</div>

<!--主界面盒子-->

<div class="maina">

    <!--查询盒子-->

    <div class="refer">
        <form action="${pageContext.request.contextPath}/PageController" method="post">
            图片类型：
            <select class="shortselect" name="contentType">
                <c:choose>
                    <c:when test="${imgPage.contentType == 'concentrate'}">
                        <option value="primary">原始图</option>
                        <option value="concentrate" selected>聚类图</option>
                        <option value="segment">分割图</option>
                        <option value="cut">剪切图</option>
                        <option value="result">结果图</option>
                    </c:when>
                    <c:when test="${imgPage.contentType == 'segment'}">
                        <option value="primary">原始图</option>
                        <option value="concentrate">聚类图</option>
                        <option value="segment" selected>分割图</option>
                        <option value="cut">剪切图</option>
                        <option value="result">结果图</option>
                    </c:when>
                    <c:when test="${imgPage.contentType == 'cut'}">
                        <option value="primary">原始图</option>
                        <option value="concentrate">聚类图</option>
                        <option value="segment">分割图</option>
                        <option value="cut" selected>剪切图</option>
                        <option value="result">结果图</option>
                    </c:when>
                    <c:when test="${imgPage.contentType == 'result'}">
                        <option value="primary">原始图</option>
                        <option value="concentrate">聚类图</option>
                        <option value="segment">分割图</option>
                        <option value="cut">剪切图</option>
                        <option value="result" selected>结果图</option>
                    </c:when>
                    <c:otherwise>
                        <option value="primary" selected="selected">原始图</option>
                        <option value="concentrate">聚类图</option>
                        <option value="segment">分割图</option>
                        <option value="cut">剪切图</option>
                        <option value="result">结果图</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <span class="tel">图片名：<input name="filename" type="text" value="${imgPage.filename}"></span>
            <span> <input type="submit" value="查询"></span>
            <span><a class="uploadB" style="color: white" href="${pageContext.request.contextPath}/ImgManager/imgUpload.jsp">上传</a> </span>
        </form>

    </div>

    <!--表框盒子-->
    <form action="${pageContext.request.contextPath}/DeleteImgList" method="post" id="delOrDownLoad">
        <table border="0" cellpadding="0" cellspacing="0">
            <tr class="title">
                <th width="125" scope="col"><span onclick="checkAll()">全选</span></th>
                <th width="165" scope="col">图片名</th>
                <th width="92" scope="col">图片类型</th>
                <th width="200" scope="col">图片</th>
                <th width="200" scope="col">图片大小</th>
                <th width="113" scope="col">操作</th>
            </tr>
            <c:forEach items="${imgFiles}" var="imgFile">
                <tr>
                    <td><input class="sel" type="checkbox" name="imgId" value="${imgFile.id}"></td>
                    <td>${imgFile.filename}</td>
                    <td>${imgFile.contentType}</td>
                    <td><img src="${pageContext.request.contextPath}/ShowImg?id=${imgFile.id}"></td>
                    <c:choose>
                        <c:when test="${imgFile.length / 1024 > 1024}">
                            <td><fmt:formatNumber type="number" value="${imgFile.length / 1024 /1024}"
                                                  maxFractionDigits="2" minFractionDigits="2"/> MB
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:formatNumber type="number" value="${imgFile.length / 1024}" maxFractionDigits="2"
                                                  minFractionDigits="2"/> KB
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <a href="<c:url context='${pageContext.request.contextPath}' value='/ShowImgMessage?id=${imgFile.id}&pageIndex=${imgPage.pageIndex}'></c:url>">编辑</a>&nbsp|
                        <a href="<c:url context='${pageContext.request.contextPath}' value='/DeleteImg?id=${imgFile.id}'></c:url>">删除</a>&nbsp|
                        <a href="<c:url context='${pageContext.request.contextPath}' value='/DownLoadImg?id=${imgFile.id}'></c:url>">下载</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    <!--分页-->
    <div class="page">

        <span class="small" onclick="submitServlet('del')"><button type="submit">批量删除</button></span>
        <span class="small" onclick="submitServlet('downLoad')"><button type="submit">批量下载</button></span>
    </form>
        <a href="<c:url context='${pageContext.request.contextPath}' value='/PageController?pageIndex=1' ></c:url> ">首页</a>
        <c:choose>
            <c:when test="${imgPage.pageIndex > 1}">
                <a href="<c:url context='${pageContext.request.contextPath}' value='/PageController?pageIndex=${imgPage.pageIndex - 1}' />">上一页</a>
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
            <c:when test="${imgPage.pageIndex < imgPage.totalPages}">
                <a href="<c:url context='${pageContext.request.contextPath}' value='/PageController?pageIndex=${imgPage.pageIndex + 1}' />">下一页</a>
            </c:when>
            <c:otherwise>
                <a>下一页</a>
            </c:otherwise>
        </c:choose>
        <a href="<c:url context='${pageContext.request.contextPath}' value='/PageController?pageIndex=${imgPage.totalPages}'></c:url> ">尾页</a>
    </div>

</div>

</body>
</html>
