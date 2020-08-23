<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<%@ page errorPage="error/viewErrorMessage.jsp" %>

<div
	id="weeklyView"
>
	<div
		id="weeklyView_header"
	>
		<div
			id="weeklyView_header_title"
		>
			${year }년 ${month }월 ${week }주
		</div>
		<div
			id="weeklyView_header_search"
		>
			<div	
				id="weeklyView_header_search_container"
			>
				<form
					action="${contextPath}/task/weeklyView.do"
					method="get"
					id="weeklyView_search"
				>
					 <div>
						<input 
							type="text" 
							placeholder="month"
							name="month"
							id="weeklyView_search_month"
						/>
					 </div>
					 <div>
						<input 
							type="text" 
							placeholder="week"
							name="week"
							id="weeklyView_search_week"
						/>
					 </div>
					 <div>
						 <button
							 type="submit"
							 form="weeklyView_search"
							 id="weeklyView_search_week_button"
						 >
							<img 
								alt="검색"
								src="${contextPath}/resources/images/search-black-18dp.svg"
								class="icon"
							/>
						 </button>
					 </div>
				</form>
			</div>
			<div
				id="weeklyView_header_arrow"
			>
				<div
					id="weeklyView_header_arrow_left"
				>
					<img 
						alt="left" 
							src="${contextPath}/resources/images/arrow_left-black-18dp.svg"
						>
				</div>
				<div
					id="weeklyView_header_arrow_right"
				>
					<img 
						alt="right" 
						src="${contextPath}/resources/images/arrow_right-black-18dp.svg"
					>
				</div>	
			</div>
		</div>
	</div>
	<div
		id="weeklyView_body"
	>
		<div
			id="weeklyView_body_left"
		>
			<div
				class="weeklyView_body_left_header"
			>
				<div
					id="weeklyView_body_left_header_title"
				>
					Weekly Goal
				</div>
			</div>
			<div
				id="weeklyView_body_left_body"
			>
				<div
					class="weeklyView_body_left_padding"
				>
				</div>
				<div
					id="weeklyView_body_left_add"
				>
					<div
						id="weeklyView_body_left_add_button"
					>
						<img
							alt="add_button" 
							src="${contextPath }/resources/images/add-black-18dp.svg"
						>
					</div>
				</div>
			</div>
		</div>
		<div
			id="weeklyView_body_right"
		>
			<div
				class="weeklyView_body_right_padding"
			>
				<div
					id="day_container_border"
				>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${contextPath }/resources/javascript/baselayout/task/weeklyTaskList.js"></script>