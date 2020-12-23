<%--
Created by IntelliJ IDEA.
User: 27734
Date: 2020/12/16
Time: 20:57
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
	* {
		margin: 0px;
		padding: 0px;
		box-sizing: border-box;
	}

	body {
		background: url("img/register_bg.png") no-repeat center;
		padding-top: 25px;
	}

	.rg_layout {
		width: 900px;
		height: 500px;
		border: 8px solid #EEEEEE;
		background-color: white;
		/*让div水平居中*/
		margin: auto;
		display: grid;
		grid-template-columns: 40% 60%;
	}

	.rg_left {
		/*border: 1px solid red;*/
		float: left;
		margin: 15px;
	}

	.rg_left > p:first-child {
		color: #FFD026;
		font-size: 20px;
	}

	.rg_left > p:last-child {
		color: #A6A6A6;
		font-size: 20px;

	}


	.rg_center {
		float: left;
		/* border: 1px solid red;*/

	}

	.td_left {
		width: 100px;
		text-align: right;
		height: 45px;
	}

	.td_right {
		padding-left: 30px;
	}

	#photoName, #space, #type {
		width: 251px;
		height: 32px;
		border: 1px solid #A6A6A6;
		/*设置边框圆角*/
		border-radius: 5px;
		padding-left: 10px;
	}

	#btn_sub {
		width: 150px;
		height: 40px;
		background-color: #FFD026;
		border: 1px solid #FFD026;
	}

	img{
		padding-top: 50px;
		width: 100%;
	}
	.uploadPhoto{
		position: fixed;
		top:430px;
		left: 430px;
	}
	a.upload{
		padding-left: 5px;
		font-size: 16px;
		border: 1px solid rgb(255,208,38);
		width: 80px;
		height: 20px;
		text-decoration: none;
		color: black;
	}
	a.upload:hover{
		text-decoration: none;
	}

	input.change{
		position: absolute;
		overflow: hidden;
		right: 0;
		top: 0;
		opacity: 0;
	}

</style>
<body>

<div class="rg_layout">
	<div class="rg_left">
		<h3 style="color: #FFD026">图片详情</h3>
		<div class="img">
			<c:if test="${empty uploadPhoto}">
				<img src="img/${pictureDetail.image}" alt="">
			</c:if>
			<c:if test="${! empty uploadPhoto}">
				<img src="${u1}" alt="">
			</c:if>
		</div>
		<div class="uploadPhoto">
			<form action="PhotoUp" method="post" enctype="multipart/form-data">
				<a href="#" class="upload">选择文件
					<input type="file" name="myfile" class="change">
				</a>
				<input type="hidden"  name="edit">
				<a href="#"  class="upload">上传
					<input class="change" type="submit">
				</a>
			</form>
		</div>
	</div>

	<div class="rg_center">
		<div class="rg_form">
			<!--定义表单 form-->
			<c:if test="${empty uploadPhoto}">
				<form action="PictureEdit" method="post">
					<table style="padding-top: 40px; border-collapse:separate; border-spacing:2px 30px;">

						<tr>
							<td class="td_left"><label for="photoName">名字：</label></td>
							<td class="td_right">
								<input type="text" name="name" id="photoName" value=" ${pictureDetail.name}">
							</td>
						</tr>

						<tr>
							<td class="td_left"><label for="type">图片类型：</label></td>
							<td class="td_right">
								<input type="text" name="type" id="type" value="${pictureDetail.type}">
							</td>
						</tr>

						<tr>
							<td class="td_left"><label for="space">图片大小：</label></td>
							<td class="td_right">
								<input type="text" name="space" id="space" value="${pictureDetail.space}">
							</td>
						</tr>


						<tr>
							<td colspan="2" align="center">
								<input type="submit" id="btn_sub" value="保存">
							</td>
						</tr>
					</table>

				</form>
			</c:if>
			<c:if test="${! empty uploadPhoto}">
				<form action="PictureEdit" method="post">
					<input type="hidden" name="image" value="${uploadPhoto.image}">
					<input type="hidden" name="pid" value="${pictureDetail.id}">
					<table style="padding-top: 40px; border-collapse:separate; border-spacing:2px 30px;">

						<tr>
							<td class="td_left"><label for="photoName">名字：</label></td>
							<td class="td_right">
								<input type="text" name="name" id="photoName" value=" ${uploadPhoto.name}">
							</td>
						</tr>

						<tr>
							<td class="td_left"><label for="type">图片类型：</label></td>
							<td class="td_right">
								<input type="text" name="type" id="type" value="${uploadPhoto.type}">
							</td>
						</tr>

						<tr>
							<td class="td_left"><label for="space">图片大小：</label></td>
							<td class="td_right">
								<input type="text" name="space" id="space" value="${uploadPhoto.space}">
							</td>
						</tr>


						<tr>
							<td colspan="2" align="center">
								<input type="submit" id="btn_sub" value="保存">
							</td>
						</tr>
					</table>

				</form>
			</c:if>


		</div>

	</div>


</div>


</body>
</html>
