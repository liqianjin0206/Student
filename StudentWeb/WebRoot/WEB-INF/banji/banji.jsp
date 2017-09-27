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

<title>My JSP 'banji.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
	
<script type="text/javascript" src="jquery.js"></script>
<script
	src="bootstrap/js/bootstrap.min.js"></script>

<style>
#search ul {
	margin-left: 200px;
	height: 10px;
	margin-top: 20px;
}

#search li {
	list-style: none;
	float: left;
	margin-left: 30px;
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
	width: 600px;
	margin: 50px auto 0 230px;
}

.pagination {
	margin-left: 500px;
}

.pagination li {
	cursor: pointer;
}

.pagination .choose {
	color: #FFF;
	background-color: blue;
}

.do ul {
	margin-left: 130px;
}

.do li {
	list-style: none;
	float: left;
	margin-left: 50px;
}

#content .table .selected {
	background: #8d818e;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				var id;
				var ye = ${pagination.ye};
				var maxYe = ${pagination.maxYe};
				$(".pagination li:first").click(
						function() {
							var name = $("#name").val();
							if (ye > 1) {
								location.href = "bj?type=search&ye=" + (ye - 1)
										+ "&name=" + name;
							};
						});
				$(".pagination li:first").mouseover(function() {
					if (ye <= 1) {
						$(this).css("cursor", "not-allowed");
					}
				});
				$(".pagination li:last").click(
						function() {
							var name = $("#name").val();
							if (ye < maxYe) {
								location.href = "bj?type=search&ye=" + (ye + 1)
										+ "&name=" + name;
							};
						});
				$(".pagination li:last").mouseenter(function() {
					if (ye >= maxYe) {
						$(this).css("cursor", "not-allowed");
					}
				});
				$("[name=qwe]").click(
						function() {
							var name = $("#name").val();
							alert(name);
							ye = $(this).html();
							location.href = "bj?type=search&ye=" + ye
									+ "&name=" + name;

						});
				$(".add").click(function() {
					location.href = "bj?type=showAdd";
				})

				$(".heng tr").click(function() {
					$(this).toggleClass("selected");
					if (id != null) {
						id = null;
					} else {
						id = $(this).children().html();
					}

				});

				$(".modify").click(function() {
					if (id == null) {
						alert("未选择数据");
					} else {
						location.href = "bj?id=" + id + "&type=showModify";
					}

				})

				$(".show").click(function() {
					if (id == null) {
						alert("未选择数据");
					} else {
						location.href = "bj?id=" + id + "&type=showSub";
					}

				})

				$(".delete").click(function() {
					var array = new Array();
					var i = 0;
					$(".heng tr").each(function(index, element) {
						if ($(this).attr("class") == "selected") {
							array[i] = $(this).children().html();
							i++;
						}
					})

					if (array.length == 0) {
						alert("未选择数据");
					} else {
						var type = confirm("确认删除？");
						if (type) {
							location.href = "bj?type=delete&id=" + array;
						};
					};

				})

				$(".searchBtn").click(function() {
					location.href = "bj?type=search";
				});




				$("#tan").click(function(){
				var array = new Array();
				 i=0;
				$(".heng tr").each(function(index, element) {
						if ($(this).attr("class") == "selected") {
							array[i] = $(this).children().html();
							i++;
						}
					})

					if (array.length !=1 ) {
						alert("未选择数据");
					}else{
					$("#modalM").load(
					"bj?type=showSub2&id="+array[0]);
					$("#myModal").modal();				
					};
					
							
				
				});
								

			});
</script>


</head>
<body>



	<div id="search">
		<div>
			<form action="bj" method="post">
				<input type="hidden" name="type" value="search" />
				<ul>
					<li>名称<input type="text" id="name" name="name"
						value="${condition.name}" /></li>
					<li><input class="searchBtn" type="submit" value="查询" /></li>
				</ul>
			</form>
		</div>
	</div>


	<div id="content">
		<table class='table table-striped table-bordered'>
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>人数</th>

				</tr>
			</thead>
			<tbody class="heng">
				<c:forEach items="${list}" var="bj">
					<tr>
						<td>${bj.id}</td>
						<td>${bj.name}</td>
						<td id="renshu"><a href="stu?type=search&bjName=${bj.id}">${bj.stuNums}</a>
						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<div>
		<ul class="pagination">
			<li><a>上一页</a> <%
 	Pagination pagination = (Pagination) request
 			.getAttribute("pagination");

 	for (int i = pagination.getBegin(); i <= pagination.getEnd(); i++) {
 %>
			
			<li><a name="qwe"
				<%if (pagination.getYe() == i) {
					out.print("class='choose'");
				}%>><%=i%>
			</a>
			</li>
			<%
				}
			%>

			<li><a>下一页</a>
			</li>
		</ul>
	</div>

	<div>
		<div class="do">
			<ul>
				<li class="show"><input type="button" value="管理科目" /></li>
				<li class="add"><input type="button" value="增加" name="name" /></li>
				<li class="modify"><input type="button" value="修改" /></li>
				<li class="delete"><input type="button" value="删除" /></li>
				<li><button id="tan" class="btn btn-primary">弹弹弹弹</button></li>
			</ul>
		</div>

	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">学生科目管理</h4>
				</div>
				<div class="modal-body" id="modalM"></div>
				<div class="modal-footer">
					</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>




</body>
</html>
