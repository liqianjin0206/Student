<%@ page language="java" import="java.util.*,Entity.*,Dao.*"
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

<title>My JSP 'addStudnet.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



<style type="text/css">
.addPage li {
	list-style: none;
	margin: 30px auto 10px;
}

.addPage {
	margin: 60px 200px 0;
}

.addPage .seve {
	width: 80px;
	margin-left: 30px
}

.addPage input {
	width: 150px;
	height: 25px
}

.banji {
	width: 150px;
	height: 25px;
}

.addPage .photo {
	width: 180px;
}

#uploadForm{
margin-top: 0px;
}

#photos img {
	width: 60px;
	height: 60px;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#upload").click(function() {
				
				var length=$("#photos img").length;
				if(length<5){
					$.ajax({
						url : "stu?type=upload",
						type : "post",
						cache : false,
						data : new FormData($("#uploadForm")[0]),
						processData : false,
						contentType : false,
						dataType : "text",
						success : function(data) {
						data=data.substring(1,data.length-1);
						data=data.replace(/ /g,"");
						var imgs=data.split(",");
						for(var i=0;i<imgs.length;i++){
							if(i<5-length){
							str = "<img src='photos/"+imgs[i]+"'/>";
						
							$("#photos").append(str);}else{
								alert("最多5张");
								break;
							}}
						}
					});}else{
						alert("最多5张")
					}
				})

				$("#add").click(
						function() {
							var name = $("[name=name]").val();
							var sex = $("[name=sex]").val();
							var age = $("[name=age]").val();
							var bjId = $("[name=bjId]").val();
							var photo=$("#photos img").attr("src");
							photo=photo.substring(photo.lastIndexOf("/")+1)
							var url = "stu?type=add2&name=" + name + "&sex="
									+ sex + "&age=" + age + "&bjId=" + bjId+"&photo="+photo;
                            location.href=url;
                            
						});
						$(document).on("click","#photos img",function(){
						
						$(this).remove();
						})
						
						

			})
</script>


</head>

<body>
	<!--  	<form action="stu?type=add"  method="post"
			enctype="multipart/form-data">
		-->
	<div>
		<ul class="addPage">
			<li>班级<select class="banji" name="bjId">
					<option value="$">请选择班级</option>
					<%
						List<BanJi> bjList = (List<BanJi>) request.getAttribute("bjList");
						for (int i = 0; i < bjList.size(); i++) {
					%>
					<option value="<%=bjList.get(i).getId()%>">
						<%=bjList.get(i).getName()%></option>

					<%
						}
					%>
			</select></li>

			<li>姓名:<input type="text" name="name" /placeholder="请输入姓名">
			</li>

			<li>性别:<input type="text" name="sex" /></li>

			<li>年龄:<input type="text" name="age" /></li>

			<div id="photos"></div>
			
			<form id="uploadForm" action="stu?type=add" method="post"
				enctype="multipart/form-data">

				<li>照片:<input class="photo" type="file" multiple="multiple" name="photo" /></li> <input
					id="upload" type=button value="上传">
			</form>


			<li><input id="add" class="seve" type="button" value="确定" /></li>



		</ul>
	</div>
	<!--  
	</form>
-->

</body>
</html>
