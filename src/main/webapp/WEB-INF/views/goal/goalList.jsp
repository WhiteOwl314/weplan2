<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<div
	class="project"
>
	<div
		class="project_header"
	>
		<div
			class="project_title"
		>
			Project
		</div>
	</div>
	<div
		class="project_body"
	>
		<div
			class="project_left"
		>
			<div
				class="project_List"
			>
				<c:forEach items="${goalList }" var="project">
					<div
						id="${project.id }"
						class="project_List-text"
					>
						<img 
							alt="project_title" 
							src="${contextPath }/resources/images/fiber_manual_record-black-18dp.svg"
						>
						${project.title }
					</div>
				</c:forEach>
				<div
					class="project_add"
				>
					<div
						class="project_button"
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
			class="project_right"
		>
			<div
				class="project_detail"
			>
				<div
					class="project_detail_header"
				>
					<div
						class="project_detail_completed"
						
					>
						<img  alt="checkbox" 
							src="${contextPath }/resources/images/iconmonstr-checkbox-11.svg"
						>
					</div>
					<div
						class="project_detail_title"
					>
						<div
							class="project_detail_text"
						>
						</div>
						<div
							class="project_detail_class"
						>
						</div>
					</div>
					<div
						class="project_detail_startDate"
					>
					</div>
					<div
						class="project_detail_limitDate"
					>
					</div>
					<div
						class="project_detail_importance"
					>
					</div>
				</div>
				<div
					class="project_detail_body"
				>
				</div>
			</div>
		</div>
	</div>

</div>