<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<!DOCTYPE html>
<html lang="ko-KR">
  <head>
    <meta charset="UTF-8">
    <!-- Title -->
    <title><tiles:insertAttribute name="title" /></title>
    <!-- Title -->
    
    <!-- JQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
    <!-- JQuery -->
	<!-- draggable -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
	<!-- draggable -->
    <!-- Style -->
    <link href="${contextPath }/resources/css/reset.css" rel="stylesheet" type="text/css">
	<link href="${contextPath }/resources/css/baselayout/style.css" rel="stylesheet" type="text/css">
    <!-- Style -->

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Cutive+Mono&display=swap" rel="stylesheet">
	<!-- Font -->
    
  </head>
    <body>
    <div id="container">
      <div id="header">
         <tiles:insertAttribute name="header"/>
      </div>
      <div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      </div>
      <div id="content">
          <tiles:insertAttribute name="body"/>
      </div>
      <div id="footer">
          <tiles:insertAttribute name="footer"/>
      </div>
    
	<!-- 팝업뜰때 배경 -->
	<div id="mask" class="mask"></div>
	<!-- 팝업뜰때 배경 -->

      <div 
      	class="inbox_button" 
      >
      	<img 
      		alt="Inbox_button_img" 
      		src="${contextPath }/resources/images/add-white-18dp.svg"
      		class="Inbox_button_img"
      	>
      </div>
      <div 
      	class="inbox_button_on" 
      >
      	<img 
      		alt="Inbox_button_img" 
      		src="${contextPath }/resources/images/clear-white-18dp.svg"
      		class="Inbox_button_img"
      	>
      </div>
		<jsp:include page="/WEB-INF/views/common/popUpForm.jsp"/>
      
    </div>

	<script src="${contextPath }/resources/javascript/baselayout/common.js"></script>
	<script src="${contextPath }/resources/javascript/popUp.js"></script>
	<script src="${contextPath }/resources/javascript/baselayout/absolutevalue/absoluteValueList.js"></script>
	<script src="${contextPath }/resources/javascript/inbox_button.js"></script>
	<script src="${contextPath }/resources/javascript/resizer.js"></script>
	<script src="${contextPath }/resources/javascript/baselayout/goal/goalList.js"></script>
	<script src="${contextPath }/resources/javascript/baselayout/yearlyplan/yearlyPlanList.js"></script>
    
  </body>
</html>