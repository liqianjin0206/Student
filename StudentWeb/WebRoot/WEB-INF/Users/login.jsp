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

<title>注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
body {
	background: #d8bbbb;
}

#login {
	margin: 50px 0 0 500px;
	padding: 10px 35px 0 0;
	float: left;
}

#login li {
	list-style: none;
	margin-top: 20px;
	color:#666;
}

#login li input {
	border-radius: 3px;
	height: 30px;
	background: #bf92a1;
}
#denglu {
	margin: 20px 0 0 500px;
	clear: both;
}

#denglu li {
	list-style: none;
	float: left;
	margin-left: 20px;
	color:#fff;
}
#denglu input{
background: #5fd0b1;

}
#denglu #btn {
	clear: both;
	margin: 50px 0 0 40px;
}

#denglu #btn input {
	width: 80px;
	height: 30px;
	border-radius: 5;
}

#denglu #zhuce input{
clear: both;
width: 80px;
height: 30px;
margin: 50px 0 0 30px;
}


#tu {
	height: 30px;
	width: 80px;
	border: 1px solid #666;
	float: left;
	margin: 180px 0 0 0;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {


if(self!=top){
top.location=self.location;
}

		$("#image").click(function() {
			
			$(this).attr("src","user?type=randomImage&"+Math.random());

		})
		
		$("#zhuce").click(function(){
		
		location.href="user?type=showRegister";
		})
		
		
	})
</script>
</head>

<body>

	<form action="user" method="post">
		<div id="login">
			<ul>
				<li><input type="hidden" name="type" value="doLogin" />
				</li>

				<li>账号：<input type="text" name="name" value="${name }" />
				</li>
				<li>密码：<input type="password" name="pwd">
				</li>
				
				<li>验证：<input type="text" name="random"
					class="form-control" placeholder="请输入验证码">
				</li>

			</ul>
		</div>

		<div id="tu" class="col-xs-4">
			<img id="image" src="user?type=randomImage">
		</div>

<div id="denglu">
			

			<li id="btn"><input type="submit" value="登录" />
			</li>

	<li id="zhuce"><input type="button" value="注册" />
			</li>
		</div>



	</form>
	
	${mes}
</body>
</html>
