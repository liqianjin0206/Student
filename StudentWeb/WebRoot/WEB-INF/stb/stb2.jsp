<%@ page language="java" import="java.util.*,Dao.*,Entity.*,util.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>



<style>
#main{
	width:600px;
	height:300px;
	
}
#main ul {
   margin-left:20px;
	padding:10px 0;
	margin-top:20px;
	width:530px;
	height:120px;
   border: 1px solid #666;
}
#main #noSub{
margin-top: 40px;
}

#main  ul li {
	list-style: none;
	float: left;
	margin-right:10px;	
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

#main #ming ul li{
margin-left: 20px;
}
#main #ming0 ul li{
margin-left: 20px;
}

#main .selected{
background: #d9534f;
}
#main  button{
float:left;
height:30px;
width:60px;
margin-left: 20px;
list-style: none;
margin-top: 10px;	
}
#change{
overflow: hidden;}
#main #mes{
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
#sel  select{
height:40px;
margin-left: 15px;
font-size: 19px;
font-weight: 600;
}

</style>

<script type="text/javascript">
	$(document).ready(function() {
	
//jquery动态生成的元素，事件不能用的和解决办法	
		
		var lis=document.getElementsByTagName("li")
		$(lis).click(function() {
		var classNmae=$(this).attr("class")	;
		if($(this).attr("class")!="selected"){
			i++;
			$(this).addClass("selected");
			}else{
			i++;
			$(this).removeClass("selected");
			}					
		});
		
		$(" #main #add").click(function(){
			var subIds=new Array();
		$("#main #noSub li").each(function(index,element){
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
		$("#main #search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#main #noSub").html(noSubsStr);		
		}		
		
		});
		
		}
		else{
		showMes("请选中一条数据!");
		
		}
					
		});
		
		$("#main #delete").click(function(){
		var subIds=new Array();
		$("#main #search li").each(function(index,element){
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
		$("#main  #search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#main #noSub").html(noSubsStr);		
		}				
		});
		
		}
		else{
		showMes("请选中一条数据!");
		
		}
					
		});
		
		
		$("#sel #bj").change(function(){
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
		$("#main #search").html(subsStr);
		
		var noSubsStr="";
		for(var i=0;i<noSubs.length;i++){
		noSubsStr+="<li data-subid="+noSubs[i].id+">"+noSubs[i].name+"</li>"
		}
		$("#main #noSub").html(noSubsStr);		
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





	<div id="sel">
	<select id="bj">
	 <c:forEach items="${bjs}" var="banji">
	 <option data-bjid="${banji.id}" value="${banji.id}" <c:if test="${banji.id==bj.id}">selected</c:if>    >${banji.name}</option>
	 </c:forEach> 
	</select>
	</div>
	<div  id="main">
		<div id="ming0">
		<ul id="search">
		<c:forEach items="${bj.subs }" var="sub">
		<li data-subid="${sub.id }">${sub.name}</li>		
		</c:forEach>		
		</ul>
		</div>
		<div id="change">
		<button id="add" type="button" class="btn btn-danger" >↑</button>
		<button id="delete" type="button" class="btn btn-danger" >↓</button>
		</div>		
		<div id="mes"></div>
		<div id="ming">
		<ul id="noSub">
		<c:forEach items="${noSub }" var="sub">
		<li data-subid="${sub.id }">${sub.name}</li>		
		</c:forEach>		
		</ul>
		</div>
	</div>



