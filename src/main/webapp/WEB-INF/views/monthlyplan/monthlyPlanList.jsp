<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<div
	id="monthlyView"
>
	<div
		id="monthlyView_header"
	>
		<div
			id="monthlyView_header_title"
		>
			${year}년 ${month }월
		</div>
	</div>
	<div
		id="monthlyView_body"
	>
		<div
			id="monthlyView_body_left"
		>
			<div
				class="monthlyView_body_left_header"
			>
				<div
					id="monthlyView_body_left_header_title"
				>
					Monthly Plan
				</div>
			</div>
			<div
				id="monthlyView_body_left_body"
			>
				<div
					class="monthlyView_body_left_padding"
				>
				</div>
				<div
					id="monthlyView_body_left_add"
				>
					<div
						id="monthlyView_body_left_add_button"
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
			id="monthlyView_body_right"
		>
			<div
				class="monthlyView_body_right_padding"
			>
			</div>
		</div>
	</div>
</div>

<script src="${contextPath }/resources/javascript/baselayout/monthlyplan/monthlyPlanList.js"></script>