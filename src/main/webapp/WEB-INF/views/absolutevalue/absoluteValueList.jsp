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
			action="${contextPath}/absoluteValue/addAbsoluteValue.do"
			method="post"
		>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
			<input type="text" name="title" placeholder="title">
			<input type="text" name="content" placeholder="content"/>
			<input type="hidden" name="member_id" value="${ member.id }">
			<input type="submit" value="save"/>
		</form>
	</div>
	
	<c:forEach var="absoluteValue" items="${absoluteValueList }">
		<div align="center">
			<a href="${contextPath}/absoluteValue/absoluteValueView.do?id=${absoluteValue.id}">
				<span>${absoluteValue.title }</span>
			</a>
		</div>
	</c:forEach>
</body>
</html>