<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<div
	id="yearlyView"
>
	<div
		id="yearlyView_header"
	>
		<div
			id="yearlyView_header_title"
		>
			${year }년
		</div>
	</div>
	<div
		id="yearlyView_body"
	>
		<div
			id="yearlyView_month_container"
		>
		</div>
	</div>
</div>
<script src="${contextPath }/resources/javascript/baselayout/yearlyplan/yearlyPlanList.js"></script>