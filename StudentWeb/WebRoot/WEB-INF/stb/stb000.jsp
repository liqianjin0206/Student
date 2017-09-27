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

<title>My JSP 'subject.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">


<style>
#search ul {
	margin-left: 200px;
	height: 10px;
	margin-top:20px;
}

#search li {

	list-style: none;
	float: left;
	margin-left: 30px;
	margin-bottom: 10px;
	font-weight: 600;
	height:30px;
}

#search input {
	width: 80px;
}
.xingbie{
height:28px;
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
	margin-left: 220px;
}

.do li {
	list-style: none;
	float: left;
	margin-left: 100px;
}

#content .table .selected {
	background: red;
}

</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var id;
		var ye = ${pagination.ye};
		var maxYe = ${pagination.maxYe};

		$(".pagination li:first").click(function() {
		    var name=$("#name").val();
			if (ye > 1) {
			location.href = "stb?type=search&ye="+ (ye - 1)+"&name="+name;
			}
		});
		$(".pagination li:first").mouseover(function() {
			if (ye <= 1) {
				$(this).css("cursor", "not-allowed");
			}
		});
		$(".pagination li:last").click(function() {
					var name=$("#name").val();
			if (ye < maxYe) {
				location.href = "stb?type=search&ye=" + (ye + 1)+"&name="+name;
			}
		});
		$(".pagination li:last").mouseenter(function() {
			if (ye >= maxYe) {
				$(this).css("cursor", "not-allowed");
			}
		});
		$("[name=qwe]").click(function() {
			var name=$("#name").val();
			alert(name);
			ye = $(this).html();
			location.href = "stb?type=search&ye=" + ye+"&name="+name;

		});
		$(".add").click(function() {
			location.href = "stb?type=showAdd";
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
				location.href = "stb?id=" + id + "&type=showModify";
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
					location.href = "stb?type=delete&id=" + array;
				}
			}
			//alert($(".color").length);
			//list=$(".color").children().html();
			//for(var i=0;i<list.length;i++){
			// $("#haha").append($('<input type="hidden" value='+list[i]+' name="ids" />'));       
			//}
		})
		
     	$(".searchBtn").click(function() {		
		location.href = "stb?type=search";
		});

	});
</script>


</head>
<body>


	<div id="search">
		<div>
			<form action="sub" method="post">
			<input type="hidden" name="type" value="search" />
				<ul>
					<li>名称<input type="text"  id="name" name="name"  value="${condition.name}"/></li>							
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
		
					
				</tr>
			</thead>
			<tbody class="heng">
				<c:forEach items="${list}" var="sub">
					<tr>
						<td>${sub.id}</td>
						<td>${sub.name}</td>
						
						
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
				<li class="add"><input type="button" value="增加" name="name" /></a>
				</li>
				<li class="modify"><input type="button" value="修改" /></li>
				<li class="delete"><input form="haha" type="button" value="删除" />
				</li>
			</ul>
		</div>

	</div>

</body>
</html>
