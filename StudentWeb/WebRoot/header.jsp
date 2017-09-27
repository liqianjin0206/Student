<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
#head{

font-size: 30px;
line-height:100px;
text-align: center;
}
#head li{
list-style: none;

}
#count{
color:black;
font-size: 14px;
margin-top:-80px;
float: right;
}

</style>



  </head>
  
<script src="jquery.js" type="text/javascript"></script>

<script type="text/javascript">
	var socket = new WebSocket("ws://192.168.0.170:8080/StudentWeb/socket");
	socket.onopen = function() {
		//浏览器socket开始进入
		console.log("open");

	};
	socket.onmessage = function(evt) {
	    console.log(evt.data);
		$("#count span").html(evt.data)
	};
	socket.onclose = function(evt) {

	};
	socket.onerror = function(evt) {

	};
</script>
  <body style="background-color: #4998a1">
   
<div id="head">
<li>
涂有财土匪头目管理系统
   </li>
   <li id="count">
		当前在线人数:<span></span>
	</li>
</div >

  </body>
</html>
