<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form 
			action="${contextPath}/goal/addGoal.do"
			method="post"
		>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
			<input type="text" name="title" placeholder="title">
			<input type="text" name="content" placeholder="content"/>
			<input id="date" type="hidden" name="date" value="0000-00-00"/>
			<input id="due" type="button" name="due" value="due">
			<input id="nullDate" type="hidden" name="nullDate" value="x">
			<input type="hidden" name="member_id" value="${ member.id }">
			<input type="submit" value="save"/>
		</form>
	</div>
	
	
	<c:forEach 
		var="goal"
		items="${goalList }"
	>
		${goal.title }
	</c:forEach>
	<script type="text/javascript">
		/* due 버튼 */
		$('#due').click(function(){
			$("#date").attr("type","date");
			/* 현재시간으로 */
			document.getElementById('date').valueAsDate = new Date();
			$("#due").attr("type","hidden");
			$("#nullDate").attr("type","button");
		});
		
		/* nullDate 버튼 */
		$('#nullDate').click(function(){
			$("#date").attr("type","hidden");
			$("#date").attr("value","0000-00-00");
			$("#due").attr("type","button");
			$("#nullDate").attr("type","hidden");
		});
	</script>
</body>
</html>