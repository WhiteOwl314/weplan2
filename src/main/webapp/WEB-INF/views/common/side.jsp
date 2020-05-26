<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>
 <link href="${contextPath }/resources/css/common/side.css" rel="stylesheet" type="text/css">

 <style>
   .no-underline{
      text-decoration:none;
   }
 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
	<div>
		<div id="side-menu-item_1">
			<a href="${contextPath}/task/listInboxTasks.do" 
						class="no-underline">
				<div class="side-menu-item">Inbox</div>
			</a>
		</div>
		<div id="side-menu-item_2">
			<a href="${contextPath}/board/listArticles.do"  class="no-underline">
				<div class="side-menu-item">
					Projects	
				</div>
			</a>
		</div>
		<div id="side-menu-item_3">
			<a href="#"  class="no-underline">
				<div class="side-menu-item">
					Tags
				</div>
			</a>
		</div>
		<div id="side-menu-item_3">
			<a href="#"  class="no-underline">
				<div class="side-menu-item">
					Forecast
				</div>
			</a>
		</div>
		<div id="side-menu-item_3">
			<a href="#"  class="no-underline">
				<div class="side-menu-item">
					Flagged
				</div>
			</a>
		</div>
		<div id="side-menu-item_3">
			<a href="#"  class="no-underline">
				<div class="side-menu-item">
					Review
				</div>
			</a>
		</div>

	</div>
	<h1>
	</h1>
	
</body>
</html>