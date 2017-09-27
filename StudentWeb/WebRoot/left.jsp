<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#menu {
	margin: 20px 0;
}

#menu .yi {
	width: 180px;
	height: 40px;
	line-height:40px;
	font-size: 20px;
	font-weight: bold;
	color: #fff;
	text-align: center;
	margin-top: 10px;
	background-color: #666;
	border: 1px solid #666;
}

#menu .yi:HOVER {
	background-color: #286090;
	cursor: pointer;
}
#menu .yi span{
font-weight: bold;
font-size: 25px
}
#menu .er {
	margin-top: 0px
}

#menu .er li:hover,#menu .er a:hover {
	background-color: #286090;
	color: #fff;
	cursor: pointer;
}

#menu .er li {
	list-style: none;
	width: 140px;
	height: 20px;
	font-size: 16px;
	font-weight: bold;
	color: #666;
	text-align: center;
	margin-top: 5px;
	background-color: #e3e4e5;
	border: 1px solid #666;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	
		$(".yi").click(function() {
			if ("none" == $(this).next().css("display")) {
				$(this).next().slideDown(500);
				$(this).find("span").html("-");
			} else {
				$(this).next().slideUp(500);
				$(this).find("span").html("+");
			}

		})

	})
</script>


</head>

<body>
	<div id="menu">
		<div class="yi"> 学生管理<span>-</span></div>
		<ul class="er">
			<li><a href="stu" target="main"> 查看</a></li>
			<li><a href="stu?type=showAdd" target="main">新增</a></li>
		</ul>
		
		<div class="yi">班级管理<span>-</span></div>
		<ul class="er">
			<li><a href="bj" target="main"> 查看</a></li>
			<li><a href="bj?type=showAdd" target="main">新增</a></li>
			<li><a href="bj?type=showSub" target="main">管理科目</a></li>
		</ul>
		
		<div class="yi">科目管理<span>-</span></div>
		<ul class="er">
			<li><a href="sub" target="main"> 查看</a></li>
			<li><a href="sub?type=showAdd" target="main">新增</a></li>
		</ul>
		
		<div class="yi">成绩管理<span>-</span></div>
		<ul class="er">
			<li><a href="sc" target="main">查看</a></li>
			<li><a href="sc?type=manage" target="main">修改</a></li>
		</ul>
	</div>
</body>
</html>
