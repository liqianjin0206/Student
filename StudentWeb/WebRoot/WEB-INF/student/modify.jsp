<%@ page language="java" import="java.util.*,Entity.*,Dao.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
margin: 30px auto 0;

}
.addPage {
margin: 60px 200px 0;

}
.addPage .seve{
width:80px;
margin-left:30px
}
.addPage input{
width:150px;
height: 25px

}
.banji{
width: 150px;
height: 25px;
}
img{
width: 100ppx;
height: 100px;
}


</style>
<script type="text/javascript" src="jquery.js"></script>


  </head>
 
  <body>
  
   <form action="stu?type=modify" method="post"
			enctype="multipart/form-data">
   
    
   <div>
   <ul class="addPage">
        <input type="hidden"  name="id" value="${stu.ID}" />                                  

         <li>班级<select class="banji" name="bjName">
                          <option value="banji">请选择班级</option>
						<c:forEach items="${bjList}" var="bj">
						<option value="${bj.id}" <c:if test="${bj.id==stu.bj.id }">selected</c:if> >${bj.name }
						
						</option></c:forEach>
				</select></li>
         <li>姓名:<input type="text"  name="name" value="${stu.name}" /></li>
      
         <li>性别:<input type="text"  name="sex" value="${stu.sex}" /></li>
        
         <li>年龄:<input type="text"  name="age" value="${stu.age}" /></li>
            <c:if test="${empty stu.photo }">
            <c:set  target="${stu}" property="photo" value="touxiang.jpg"></c:set>
            </c:if>   
            
          <li><img name="photo" id="image" src="photos/${stu.photo}"  /></li> 
               
         <li>照片:<input class="photo" type="file" name="photo" /></li>
		 
		 <li><input class="seve" type="submit" value="确定" />
				</li>
   </ul>     
   </div>
   
   
   
   </form>
   
   
   
  </body>
</html>
