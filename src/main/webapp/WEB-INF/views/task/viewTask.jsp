<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일</title>
</head>
<body>
	<form name="frmTask" method="post" action="${contextPath }" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					완료여부
				   </td>
				   <td>
					<input type="text" value="${task.isCompleted }" name="isCompleted" />
				   </td>  
			  </tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					중요도
				   </td>
				   <td>
					<input type="text" value="${task.importance }" name="importance" />
				   </td>  
			  </tr>

			<tr>
				<td width=150 align="center" bgcolor=#FF9933>
					제목
				</td>
				<td>
					<input type="text" value="${task.title }" name="title" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					내용
				   </td>
				   <td>
					<textarea rows="20" cols="60"  name="content"  id="i_content" >${task.content }</textarea>
				   </td>  
			  </tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					기한
				   </td>
				   <td>
					<input type="text" value="${task.limitDate }" name="limitDate" />
				   </td>  
			  </tr>
		</table>
	</form>

</body>
</html>