<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<style type="text/css">

.addPage li{
list-style: none;
margin: 50px auto 0;

}
.addPage {
margin: 150px 200px 0;

}
.addPage .seve{
width:80px;
margin-left:30px
}
.addPage input{
width:150px;
height: 25px

}



</style>
<script type="text/javascript" src="jquery.js"></script>


  </head>
 
  <body>
  
   <form action="bj" method="post">
   
    <input type="hidden" name="type" value="modify"/>
   <div>
   <ul class="addPage">
                                 
     <li>名称:<input type="text"  name="name" value="${bj.name}" /></li>
     
          
          <li><input class="seve" type="submit"  value="确定"/> </li>   
   
   </ul>     
   </div>
   
   
   
   </form>
   
   
   
  </body>
</html>
