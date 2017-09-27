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

<title>My JSP 'student.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">


<style>
#search ul {
	margin-left: 160px;
	height: 10px;
	margin-top: 20px;
}

#search li {
	list-style: none;
	float: left;
	margin-left: 20px;
	margin-bottom: 10px;
	font-weight: 600;
	height: 30px;
}

#search input {
	width: 80px;
}

.xingbie {
	height: 28px;
}

.table {
	width: 600px;
	margin: 50px auto 0 230px;
}

.pagination {
	margin-left: 400px;
}

.pagination li {
	cursor: pointer;
}

.pagination .choose {
	color: #FFF;
	background-color: blue;
}

#content .table .selected {
	background: #8d818e;
}
.fScore{
width: 60px;
float: right;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var id;
				var ye = ${pagination.ye};
				var maxYe = ${pagination.maxYe};
				function clickYeMa(selectYe) {
					var stuName = $("#stuName").val();
					var bjId = $("#bjId").val();
					var subId = $("#subId").val();
					location.href = "sc?type=manage&ye=" + selectYe
							+ "&stuName=" + stuName + "&bjId=" + bjId + "&subId="
							+ subId;

				}

				$("[name=qwe]").click(
						function() {
								clickYeMa($(this).html());

						});

				$(".pagination li:first").click(
						function() {
							if (ye > 1) {

									clickYeMa(ye-1);
							}
						});
				$(".pagination li:first").mouseover(function() {
					if (ye <= 1) {
						$(this).css("cursor", "not-allowed");
					}
				});

				$(".pagination li:last").click(
						function() {

							if (ye < maxYe) {
								clickYeMa(ye+1);
							}
						});

				$(".pagination li:last").mouseenter(function() {
					if (ye >= maxYe) {
						$(this).css("cursor", "not-allowed");
					}
				});

				$("#shou").click(function() {

					clickYeMa(1);
				})
				$("#wei").click(function() {

					clickYeMa(maxYe);
				})

				$(".searchBtn").click(function() {
					location.href = "sc?type=search";
				});
				var score=0;
				$(".fScore").focus(function(){
				score=$(this).val();
				})
				 $(".fScore").blur(function(){ 	
				 if($(this).val()!=score){		 
				 var scId=$(this).parent().parent().attr("score");
                 var stuId=$(this).parent().siblings("[name=stu]").data("id");
                 var subId=$(this).parent().siblings("[name=sub]").data("id");
                 var txt=$(this);
                $.ajax({
                url:"sc?type=input",
                data:{
                    scId:scId,
                    stuId:stuId,
                    subId:subId,
                    score:$(this).val()},
                type:"post",
                dataType:"text",
                success:function(data){
            if(data!="false"){
            var json=JSON.parse(data);
            txt.parent().siblings("[name=grade]").html(json.grade);
            txt.parent().parent().attr("score",json.id);
            }else{
            alert("失败！");
            }
            
                }
                })}
                      
                })
                

			});
</script>


</head>
<body>

	<div id="main">
		<div id="search">
			<div>
				<form action="sc" method="post">
					<input type="hidden" name="type" value="search" />
					<ul>
						<li>姓名<input type="text" id="stuName" name="stuName"
							value="${condition.stu.name}" />
						</li>

						<li>班级<select id="bjId" name="bjId">
								<option value="0">请选择班级</option>
								<c:forEach items="${bjList}" var="bj">
									<option value="${bj.id}"
										<c:if test="${bj.id==condition.stu.bj.id }">selected</c:if>>${bj.name
										}</option>
								</c:forEach>
						</select>
						</li>

						<li>科目<select id="subId" name="subId">
								<option value="0">请选择学科</option>
								<c:forEach items="${subList}" var="sub">
									<option value="${sub.id}"
										<c:if test="${sub.id==condition.sub.id }">selected</c:if>>${sub.name
										}</option>
								</c:forEach>
						</select>
						</li>

						<li><input class="searchBtn" type="submit" value="查询" />
						</li>
					</ul>
				</form>
			</div>
		</div>


		<div id="content">
			<table class='table table-striped table-bordered'>
				<thead>
					<tr>

						<th>班级</th>
						<th>姓名</th>
						<th>科目</th>
						<th>等级</th>
						<th>成绩</th>
					</tr>
				</thead>
				<tbody class="heng">
					<c:forEach items="${scList}" var="sc">
						<tr  score="${sc.id}">
							<td>${sc.stu.bj.name}</td>
							<td data-id="${sc.stu.ID }" name="stu">${sc.stu.name}</td>
							<td data-id="${sc.sub.id }" name="sub">${sc.sub.name}</td>
							<td name="grade">${sc.grade}</td>
							<td>
							<input class="fScore"
							<c:choose>
							<c:when test="${sc.score!=null }">
							value="${sc.score}"
							</c:when> 
							<c:otherwise>							
							 placeholder="未录入"
							</c:otherwise>
							</c:choose>
							/>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
		<div>
			<ul class="pagination">

				<li><a>上一页</a>
				</li>
				<li id="shou"><a>首页</a></li>
				<%
					Pagination pagination = (Pagination) request
							.getAttribute("pagination");

					for (int i = pagination.getBegin(); i <= pagination.getEnd(); i++) {
				%>
				<li><a name="qwe"
					<%if (pagination.getYe() == i) {
					out.print("class='choose'");
				}%>><%=i%>
				</a></li>
				<%
					}
				%>

				<li id="wei"><a>尾页</a></li>
				<li><a>下一页</a></li>

			</ul>
		</div>


	</div>
</body>
</html>
