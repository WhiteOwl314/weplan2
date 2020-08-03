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
	<div>
		<form 
			action="${contextPath}/goal/addGoal.do"
			method="post"
		>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
			<input type="text" name="title" placeholder="title">
			<input type="text" name="content" placeholder="content"/>
			<input id="date" type="hidden" name="date" value="0000-00-00"/>
			<input id="due" type="button" name="due" value="due">
			<input id="nullDate" type="hidden" name="nullDate" value="x">
			<input type="hidden" name="member_id" value="${ member.id }">
			<input type="submit" value="save"/>
		</form>
	</div>
	
	
	<c:forEach 
		var="goal"
		items="${goalList }"
	>
		<div>
			<span>
				${goal.title }
			</span>
			<span
				class="item"
				id="${goal.id }"
				onclick="javascript:goDetail(${goal.id })"
				
			>
				수정하기
			</span>
			<span>
				<a
					href="${contextPath }/goal/deleteGoal.do?id=${goal.id}"
				>
					삭제하기
				</a>
			</span>
		</div>
	</c:forEach>
	