<%--
  Created by IntelliJ IDEA.
  User: 72713
  Date: 2020/12/20/0020
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>图片信息修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/imgMessage.css">
    <style type="text/css">
        .shortselect{
            background:#fafdfe;
            height:28px;
            width:300px;
            line-height:28px;
            -moz-border-radius:2px;
            -webkit-border-radius:2px;
            border-radius:2px;
            border: 1px solid #A6A6A6;
            border-radius: 5px;
            padding-left: 10px;
        }
    </style>
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <h3 style="color: #FFD026">图片详情</h3>
        <div class="img">
            <img id="viewImg2" width="250px" height="300px" src="${pageContext.request.contextPath}/ShowImg?id=${imgFile.id}">
            <img id="viewImg">
        </div>

    </div>

    <div class="rg_center">
        <a id="comeBack" href="${pageContext.request.contextPath}/PageController?pageIndex=${imgPage.pageIndex}">返回</a>
        <div class="rg_form">
            <!--定义表单 form-->
            <form action="<c:url context='${pageContext.request.contextPath}' value='/UpdateImg?id=${imgFile.id}'></c:url>" method="post" enctype="multipart/form-data">

                    <table style="padding-top: 40px; border-collapse:separate; border-spacing:2px 30px;">

                        <tr>
                            <td class="td_left"><label for="photoName">名字：</label></td>
                            <td class="td_right">
                                <input type='text' name='filename' id="photoName" value="${imgFile.filename}">
                            </td>
                        </tr>

                        <tr>
                            <td class="td_left"><label for="type">图片类型：</label></td>
                            <td class="td_right">
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
                            </td>
                        </tr>

                        <tr>
                            <td class="td_left"><label for="length">图片大小：</label></td>
                            <td class="td_right">
                                <c:choose>
                                    <c:when test="${imgFile.length / 1024 > 1024}">
                                        <input id="length" type="text" name="length" value="<fmt:formatNumber type="number" value="${imgFile.length / 1024 /1024}" maxFractionDigits="2" minFractionDigits="2"/> MB"><br>
                                    </c:when>
                                    <c:otherwise>
                                        <input id="length" type="text" name="length" value="<fmt:formatNumber type="number" value="${imgFile.length / 1024}" maxFractionDigits="2" minFractionDigits="2"/> KB"><br>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                        <tr>
                            <td class="td_left"><label for="type">上传图片</label></td>
                            <td class="td_right">
                                <input type="file" id="upLoad" name="image" >
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" id="btn_sub" value="修改"><br>
                            </td>
                        </tr>
                    </table>

                </form>

        </div>

    </div>

</div>

</body>
<script src="http://i.gtimg.cn/qzone/biz/gdt/lib/jquery/jquery-2.1.4.js?max_age=31536000"></script>
<script type="text/javascript" rel="stylesheet">

    $(function(){
        $('#upLoad').on('change',function(){
            //设置原来的图片消失
            var viewImg2 = document.getElementById('viewImg2');
            viewImg2.src = '';
            viewImg2.style.display = 'none';
            //设置展示图片展示
            // var viewImg = document.getElementById('viewImg');
            //
            // viewImg.weight = '200px';
            // viewImg.height = '200px';
            var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
                fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                imgBase64 = '',      //存储图片的imgBase64
                fileObj = document.getElementById('upLoad').files[0]; //上传文件的对象,要这样写才行，用jquery写法获取不到对象

            //文件大小
            var size = 0;
            size = $(this)[0].files[0].size;
            size = size / 1024;//KB
            // 检查是否是图片
            if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
                alert('上传错误,文件格式必须为：png/jpg/jpeg');
                return;
            }
            if(size > 1024){
                size = size / 1024;//MB
                $("#length").attr("value",size.toFixed(2)+" MB");
            }else {
                $("#length").attr("value",size.toFixed(2)+" KB");
            }

            //获取文件名字
            // $("#photoName").attr("value", fileObj.name);

            // 调用函数，对图片进行压缩
            compress(fileObj,function(imgBase64){
                imgBase64 = imgBase64;    //存储转换的base64编码

                $('#viewImg').attr('src',imgBase64); //显示预览图片
                $('#viewImg').attr('width','250px');
                $('#viewImg').attr('height','300px');

            });
        });

        // 不对图片进行压缩，直接转成base64
        function directTurnIntoBase64(fileObj,callback){
            var r = new FileReader();
            // 转成base64
            r.onload = function(){
                //变成字符串
                imgBase64 = r.result;
                console.log(imgBase64);
                callback(imgBase64);
            }
            r.readAsDataURL(fileObj);    //转成Base64格式
        }

        // 对图片进行压缩
        function compress(fileObj, callback) {
            if ( typeof (FileReader) === 'undefined') {
                console.log("当前浏览器内核不支持base64图标压缩");
                //调用上传方式不压缩
                directTurnIntoBase64(fileObj,callback);
            } else {
                try {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var image = $('<img/>');
                        image.load(function(){
                            square = 700,   //定义画布的大小，也就是图片压缩之后的像素
                                canvas = document.createElement('canvas'),
                                context = canvas.getContext('2d'),
                                imageWidth = 0,    //压缩图片的大小
                                imageHeight = 0,
                                offsetX = 0,
                                offsetY = 0,
                                data = '';

                            canvas.width = square;
                            canvas.height = square;
                            context.clearRect(0, 0, square, square);

                            if (this.width > this.height) {
                                imageWidth = Math.round(square * this.width / this.height);
                                imageHeight = square;
                                offsetX = - Math.round((imageWidth - square) / 2);
                            } else {
                                imageHeight = Math.round(square * this.height / this.width);
                                imageWidth = square;
                                offsetY = - Math.round((imageHeight - square) / 2);
                            }
                            context.drawImage(this, offsetX, offsetY, imageWidth, imageHeight);
                            var data = canvas.toDataURL('image/jpeg');
                            //压缩完成执行回调
                            callback(data);
                        });
                        image.attr('src', e.target.result);
                    };
                    reader.readAsDataURL(fileObj);
                }catch(e){
                    console.log("压缩失败!");
                    //调用直接上传方式  不压缩
                    directTurnIntoBase64(fileObj,callback);
                }
            }
        }
    });

</script>
</html>
