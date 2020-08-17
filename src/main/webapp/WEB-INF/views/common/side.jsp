<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<div>


	<div id="side-menu-item_1">
		<a href="${contextPath}/task/listInboxTasks.do" 
					class="no-underline">
			<div 
				class="side-menu-item"
				id="side_menu-inbox"
			>
				<img 
					alt="inbox" 
					src="${contextPath }/resources/images/inbox-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Inbox
				</div>
			</div>
		</a>
	</div>
	
	<div id="side-menu-item_2">
		<a href="${contextPath}/absoluteValue/absoluteValueList.do"  class="no-underline">
			<div 
				class="side-menu-item"
				id="side_menu-absoluteValueList"
			>
				<img 
					alt="absoluteValue" 
					src="${contextPath }/resources/images/star-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Absolute Value
				</div>
			</div>
		</a>
	</div>
	
	<div id="side-menu-item_3">
		<a href="${contextPath}/goal/goalList.do"  class="no-underline">
			<div 
				class="side-menu-item"
				id="side_menu-goalList"
			>
				<img 
					alt="absoluteValue" 
					src="${contextPath }/resources/images/flag-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Project
				</div>
			</div>
		</a>
	</div>
	
	<div id="side-menu-item_4">
		<a 
			id="sideMenu_yearlyPlan_a"
			href="${contextPath}/monthlyPlan/yearlyView.do"  
			class="no-underline"
		>
			<div 
				class="side-menu-item"
				id="side_menu-yealyPlan"
			>
				<img 
					alt="yealyPlan" 
					src="${contextPath }/resources/images/event_note-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Year
				</div>
			</div>
		</a>
	</div>
	
	
	<div id="side-menu-item_5">
		<a 
			id="sideMenu_monthlyPlan_a"
			href="${contextPath}/weeklyPlan/monthlyView.do?month=2020-12"  
			class="no-underline"
		>
			<div 
				class="side-menu-item"
				id="side_menu-monthlyPlan"
			>
				<img 
					alt="monthlyPlan" 
					src="${contextPath }/resources/images/view_day-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Month
				</div>
			</div>
		</a>
	</div>
	
	
	
	
	
	<div id="side-menu-item_6">
		<a 
			href="${contextPath}/task/weeklyView.do"  
			class="no-underline"
			id="sideMenu_weeklyView_a"
		>
			<div 
				class="side-menu-item"
				id="side_menu-weeklyTaskList"
			>
				<img 
					alt="weeklyTask" 
					src="${contextPath }/resources/images/view_week-black-18dp.svg"
					class="side_icon"
				>
				<div class="side-menu-item-text">
					Week
				</div>
			</div>
		</a>
	</div>
	
	

</div>
<script src="${contextPath }/resources/javascript/baselayout/common/side.js"></script>







