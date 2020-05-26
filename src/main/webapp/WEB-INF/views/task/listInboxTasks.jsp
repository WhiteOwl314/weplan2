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
	<title>Inbox</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>번호</b></td>
			<td><b>제목</b></td>
			<td><b>내용</b></td>
			<td><b>삭제</b></td>
		</tr>
		<c:forEach var="task" items="${inboxTasksList }">
			<tr align="center">
				<td>${task.id }</td>
				<td>${task.title }</td>
				<td>${task.content }</td>
				<td><a href="${contextPath }/task/removeTask.do?id=${task.id}">삭제하기</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>