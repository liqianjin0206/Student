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

<title>登录</title>

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
	background: #cfe48f;
}

#login {
	margin: 50px 0 0 500px;
	padding: 10px 35px 0 0;
	float: left;
}

#login li {
	list-style: none;
	margin-top: 20px;
}
#login #pwd{
margin-left: -32px;
}
#login #code{
margin-left: -5px;

}
#login li input {
	border-radius: 3px;
	height: 30px;
	
	background: #dce4c2;
}
#denglu {
	margin: 20px 0 0 500px;
	clear: both;
}

#denglu li {
	list-style: none;
	float: left;
	margin-left: 20px;
}

#denglu #btn {
	clear: both;
	margin: 30px 0 0 120px;
}

#denglu #btn input {
	width: 70px;
	height: 30px;
	border-radius: 5;
}

#tu {
	height: 30px;
	width: 80px;
	border: 1px solid #666;
	float: left;
	margin: 170px 0 0 30px;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("#image").click(function() {
			
			$(this).attr("src","user?type=randomImage&"+Math.random());

		})
		
		

	})
</script>
</head>

<body>

	<form action="user" method="post">
		<div id="login">
			<ul>
				<li><input type="hidden" name="type" value="doRegister" />
				</li>

				<li>账号：<input type="text" name="name" value="${name }" />
				</li>
				<li>密码：<input type="password" name="pwd">
				</li>
				<li id="pwd">确认密码：<input type="password" >
				</li>
				<li id="code">验证： <input type="text" name="random"
					class="form-control" placeholder="请输入验证码">
				</li>

			</ul>
		</div>

		<div id="tu" class="col-xs-4">
			<img id="image" src="user?type=randomImage">
		</div>

<div id="denglu">
		

			<li id="btn"><input type="submit" value="注册" />
			</li>


		</div>
	</form>
	
	${mes}
</body>
</html>
