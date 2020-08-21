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
		<div
			id="yearlyView_header_search"
		>
			<div	
				id="yearlyView_header_search_container"
			>
				<form
					action="${contextPath}/monthlyPlan/yearlyView.do"
					method="get"
					id="yearlyView_search"
				>
					 <div>
						<input 
							type="text" 
							placeholder="year"
							name="year"
							id="yearlyView_search_year"
						/>
					 </div>
					 <div>
						 <button
						 	type="submit"
						 	form="yearlyView_search"
						 	id="yearlyView_search_year_button"
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
				id="yearlyView_header_arrow"
			>
				<div
					id="yearlyView_header_arrow_left"
				>
					<img 
						alt="left" 
						src="${contextPath}/resources/images/arrow_left-black-18dp.svg"
					>
				</div>
				<div
					id="yearlyView_header_arrow_right"
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
		id="yearlyView_body"
	>
		<div
			id="yearlyView_month_container"
		>
		</div>
	</div>
</div>
<script src="${contextPath }/resources/javascript/baselayout/yearlyplan/yearlyPlanList.js"></script>