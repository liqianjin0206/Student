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
#search,#noSub {
	margin-left: 120px;
	padding:10px 0;
	width:680px;
	height:160px;
	margin-top:20px;
	border: 1px solid #666;
}
#noSub{
margin-top: 80px;
}

ul li {

	list-style: none;
	float: left;
	margin-left: 30px;
	margin-bottom: 10px;
	font-weight: 600;
	width:100px;
	height:40px;
	line-height:40px;
	text-align:center;
	background: #6585a7;
	color:#fff;
	font-size: 16px;
	cursor: pointer;
}
.selected{
background: #d9534f;
}
#change {
float:left;
margin-left: 120px;
	list-style: none;
	margin-top: 10px;
	
}
#mes{
float: left;
margin-left:20px;
width:150px;
height: 36px;
line-height: 36px;
letter-spacing: 3px;
font-size:16px;
font-weight:500;
color:red;
}
#bj{
height:40px;
margin-top:10px;
margin-left: 120px;
font-size: 19px;
font-weight: 600;
}

</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	
//jquery动态生成的元素，事件不能用的解决办法	

		$(document).on("click","li",function() {
		var classNmae=$(this).attr("class")	;
			
			$(this).toggleClass("selected");					
		});
		
		$("#add").click(function(){
			var subIds=new Array();
		$("#noSub li").each(function(index,element){
		if($(this).attr("class")=="selected"){
		subIds.push($(this).data("subid"));
		
		}		
		});	
		if(subIds.length>0){
		$.ajax({
		url:"bj",
		data:"type=addSub&bjId="+$("#bj").val()+"&subIds="+subIds,
		type:"get",
		dataType:"text",
		success:function(data){	

		var strs=data.split("-|-");
		var subs=JSON.parse(strs[0]);
		var noSubs=JSON.parse(strs[1]);
	    
		var subsStr="";
		for(var i=0;i<subs.length;i++){
		subsStr+="<li data-subid="+subs[i].id+">"+subs[i].name+"</li>"
		}
		$("#search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#noSub").html(noSubsStr);		
		}		
		
		});
		
		}
		else{
		showMes("请选中一条数据!");
		
		}
					
		});
		
		$("#delete").click(function(){
		var subIds=new Array();
		$("#search li").each(function(index,element){
		if($(this).attr("class")=="selected"){
		subIds.push($(this).data("subid"));
		
		}		
		});	
		if(subIds.length>0){
		$.ajax({
		url:"bj",
		data:"type=deleteSub&bjId="+$("#bj").val()+"&subIds="+subIds,
		type:"get",
		dataType:"text",
		success:function(data){	

		var strs=data.split("-|-");
		var subs=JSON.parse(strs[0]);
		var noSubs=JSON.parse(strs[1]);
	    
		var subsStr="";
		for(var i=0;i<subs.length;i++){
		subsStr+="<li data-subid="+subs[i].id+">"+subs[i].name+"</li>"
		}
		$("#search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#noSub").html(noSubsStr);		
		}				
		});
		
		}
		else{
		showMes("请选中一条数据!");
		
		}
					
		});
		
		
		$("#bj").change(function(){
		$.ajax({
		url:"bj",
		data:"type=searchSub&bjId="+$("#bj").val(),
		type:"get",
		dataType:"text",
		success:function(data){	

		var strs=data.split("-|-");
		var subs=JSON.parse(strs[0]);
		var noSubs=JSON.parse(strs[1]);
	    
		var subsStr="";
		for(var i=0;i<subs.length;i++){
		subsStr+="<li data-subid="+subs[i].id+">"+subs[i].name+"</li>"
		}
		$("#search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#noSub").html(noSubsStr);		
		}				
		});
		
		
					
		});
function showMes(mes){
$("#mes").html(mes);
setTimeout(function(){
$("#mes").html("")
},1000);

}

	});
</script>


</head>
<body>


	<div >
	<select id="bj">
	 <c:forEach items="${bjs}" var="banji">
	 <option data-bjid="${banji.id}" value="${banji.id}" <c:if test="${banji.id==bj.id}">selected</c:if>    >${banji.name}</option>
	 </c:forEach>
	</select>
		<ul id="search">
		<c:forEach items="${bj.subs }" var="sub">
		<li data-subid="${sub.id }">${sub.name}</li>
		
		</c:forEach>		
		</ul>
		
		<div id="change">
		<button id="add" type="button" class="btn btn-danger" />↑</button>
		<button id="delete" type="button" class="btn btn-danger"/>↓</button>
		</div>
		<div id="mes"></div>
		<ul id="noSub">
		<c:forEach items="${noSub }" var="sub">
		<li data-subid="${sub.id }">${sub.name}</li>		
		</c:forEach>		
		</ul>
	</div>


</body>
</html>
