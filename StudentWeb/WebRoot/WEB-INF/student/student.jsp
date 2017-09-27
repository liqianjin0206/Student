<%@ page language="java" import="java.util.*,Dao.*,Entity.*,util.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'student.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">


<style>
#search ul {
	margin-left: 120px;
	height: 10px;
	margin-top: 20px;
}

#search li {
	list-style: none;
	float: left;
	margin-left: 20px;
	margin-bottom: 10px;
	font-weight: 600;
	height: 30px;
}

#search input {
	width: 80px;
}

.xingbie {
	height: 28px;
}

.table {
	width: 700px;
	margin: 50px auto 0 130px;
}

.pagination {
	margin-left: 400px;
}

.pagination li {
	cursor: pointer;
}

.pagination .choose {
	color: #FFF;
	background-color: blue;
}

.do ul {
	margin-left: 120px;
}

.do li {
	list-style: none;
	float: left;
	margin-left: 120px;
}

#content .table .selected {
	background: #8d818e;
}

.photo {
	height: 25px;
	width: 25px;
}

#bigPhoto {
	display: none; 
	position: absolute;


}

#bigPhoto img {
	width: 100px;
	height: 100px;




}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var id;
				var ye = ${pagination.ye};
				var maxYe = ${pagination.maxYe};
				$(".pagination li:first").click(
						function() {
							var name = $("#name").val();
							var sex = $("#sex").val();
							var age = $("#age").val();

							if (ye > 1) {

								location.href = "stu?type=search&ye="
										+ (ye - 1) + "&name=" + name + "&sex="
										+ sex + "&age=" + age;
							}
						});
				$(".pagination li:first").mouseover(function() {
					if (ye <= 1) {
						$(this).css("cursor", "not-allowed");
					}
				});

				$(".pagination li:last").click(
						function() {
							var name = $("#name").val();
							var sex = $("#sex").val();
							var age = $("#age").val();
							if (ye < maxYe) {
								location.href = "stu?type=search&ye="
										+ (ye + 1) + "&name=" + name + "&sex="
										+ sex + "&age=" + age;
							}
						});

				$(".pagination li:last").mouseenter(function() {
					if (ye >= maxYe) {
						$(this).css("cursor", "not-allowed");
					}
				});

				$("#shou").click(
						function() {
							var name = $("#name").val();
							var sex = $("#sex").val();
							var age = $("#age").val();

							location.href = "stu?type=search&ye=1" + "&name="
									+ name + "&sex=" + sex + "&age=" + age;
						})
				$("#wei").click(
						function() {
							var name = $("#name").val();
							var sex = $("#sex").val();
							var age = $("#age").val();
							location.href = "stu?type=search&ye=" + maxYe
									+ "&name=" + name + "&sex=" + sex + "&age="
									+ age;
						})

				$("[name=qwe]").click(
						function() {
							var name = $("#name").val();
							var sex = $("#sex").val();
							var age = $("#age").val();
							ye = $(this).html();
							location.href = "stu?type=search&ye=" + ye
									+ "&name=" + name + "&sex=" + sex + "&age="
									+ age;

						});
				$(".add").click(function() {
					location.href = "stu?type=showAdd";
				})

				$(".heng tr").click(function() {
					$(this).toggleClass("selected");
					if (id != null) {
						id = null;
					} else {
						id = $(this).children().html();
					}

					//oldColor=$(this).css("background-color");
					//$( "tr").css("background",oldColor);
					//if ($(this).attr("class") == "") {
					//	$(this).attr("class", "color");				
					//} else {
					//	$(this).attr("class", "");
					//}			
				});

				$(".modify").click(function() {
					if (id == null) {
						alert("未选择数据");
					} else {
						location.href = "stu?id=" + id + "&type=showModify";
					}

				})

				$(".delete").click(function() {

					var array = new Array();

					var i = 0;
					$(".heng tr").each(function(index, element) {
						if ($(this).attr("class") == "selected") {
							array[i] = $(this).children().html();
							;
							i++;
						}
					})

					if (array.length == 0) {
						alert("未选择数据");
					} else {
						var type = confirm("确认删除？");
						if (type) {
							location.href = "stu?type=delete&id=" + array;
						}
					}
					//alert($(".color").length);
					//list=$(".color").children().html();
					//for(var i=0;i<list.length;i++){
					// $("#haha").append($('<input type="hidden" value='+list[i]+' name="ids" />'));       
					//}
				})

				$(".searchBtn").click(function() {
					location.href = "stu?type=search";
				});

				$(".photo").hover(function(event) {
					var str = $(this).attr("src");

					$("#bigPhoto").show();
					$("#bigPhoto").css({
						left : event.pageX+45,
						top : event.pageY,
					})
					$("#bigPhoto img").attr("src", str);
				}, function(event) {
					

					$("#bigPhoto").hide();

				})

			});
</script>


</head>
<body>









	<div id="main">
		<div id="search">
			<div>
				<form action="stu" method="post">
					<input type="hidden" name="type" value="search" />
					<ul>
						<li>姓名<input type="text" id="name" name="name"
							value="${condition.name}" />
						</li>

						<li>性别 <select id="sex" class="xingbie" name="sex"><option>请选择性别</option>
								<option value="男"
									<c:if test="${condition.sex eq '男'}" >selected</c:if>>男</option>
								<option value="女"
									<c:if test="${condition.sex eq '女'}" >selected</c:if>>女</option>
						</select>
						</li>
						<li>年龄<input type="text" id="age" name="age"
							<c:if test="${condition.age!=-1}">value="${condition.age }" </c:if> />
						</li>

						<li>班级<select class="banji" name="bjName">
								<option value="0">请选择班级</option>
								<c:forEach items="${bjList}" var="bj">
									<option value="${bj.id}"
										<c:if test="${bj.id==condition.bj.id }">selected</c:if>>${bj.name
										}</option>
								</c:forEach>
						</select>
						</li>


						<li><input class="searchBtn" type="submit" value="查询" />
						</li>
					</ul>
				</form>
			</div>
		</div>


		<div id="content">
			<table class='table table-striped table-bordered'>
				<thead>
					<tr>
						<th>ID</th>
						<th>班级</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>照片</th>
					</tr>
				</thead>
				<tbody class="heng">
					<c:forEach items="${list}" var="stu">
						<tr>
							<td>${stu.ID}</td>
							<td>${stu.bj.name}</td>
							<td>${stu.name}</td>
							<td>${stu.sex}</td>
							<td>${stu.age}</td>
							<c:if test="${empty stu.photo }">
							<c:set target="${stu }" property="photo" value="touxiang.jpg"></c:set>
							</c:if>
							<td><img class="photo" src="photos/${stu.photo}" /></td>
							
						</tr>
						
					</c:forEach>

				</tbody>
			</table>
			
			
			
			
		</div>
		
		<div>
			<ul class="pagination">

				<li><a>上一页</a>
				</li>
				<li id="shou"><a>首页</a></li>
				<%
					Pagination pagination = (Pagination) request
							.getAttribute("pagination");

					for (int i = pagination.getBegin(); i <= pagination.getEnd(); i++) {
				%>
				<li><a name="qwe"
					<%if (pagination.getYe() == i) {
					out.print("class='choose'");
				}%>><%=i%>
				</a></li>
				<%
					}
				%>

				<li id="wei"><a>尾页</a></li>
				<li><a>下一页</a></li>

			</ul>
		</div>

		<div>
			<div class="do">
				<ul>
					<li class="add"><input type="button" value="增加" name="name" /></a>
					</li>
					<li class="modify"><input type="button" value="修改" />
					</li>
					<li class="delete"><input form="haha" type="button" value="删除" />
					</li>
					<li><div id="bigPhoto">
						<img src="">
					</div></li>
				</ul>

			</div>

		</div>

	</div>
</body>
</html>
