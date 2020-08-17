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

<div
	id="weeklyView"
>
	<div
		id="weeklyView_header"
	>
		<div
			id="weeklyView_header_title"
		>
			${month }
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
					Monthly Plan
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
			</div>
		</div>
	</div>
</div>
<script src="${contextPath }/resources/javascript/baselayout/task/weeklyTaskList.js"></script>