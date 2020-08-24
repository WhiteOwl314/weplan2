<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<div
	id="inboxTaskList_container"
>
	<div
		id="inboxTaskList_header"
	>
		<div
			id="inboxTaskList_header_title"
		>
			Inbox
		</div>
	</div>
	<div
		id="inboxTaskList_body"
	>
		<div
			id="inboxTaskList_padding"
		>
			<c:forEach
			 	items="${inboxTasksList }"
			 	var="task"
			>
				<div
					id="inboxTask_${task.id }"
					class="inboxTaskList_inboxTask"
				>
					<div
						class="inboxTask_completed"
						onclick="inboxTask_complete(${task.id})"
					>
						<img
							alt="checkbox"
							src="${contextPath }/resources/images/iconmonstr-checkbox-11.svg"
						>
					</div>
					<div
						class="inboxTask_completed_on"
						onclick="inboxTask_notComplete(${task.id})"
					>
						<img
							alt="checkbox"
							src="${contextPath }/resources/images/iconmonstr-checkbox-9.svg"
						>
					</div>
					<div
						class="inboxTask_title_container"
						onclick="javascript:popUpTaskView(${task.id})"
					>
						<div
							class="inboxTask_title"
						>
							${task.title}
						</div>
					</div>
					<c:choose>
						<c:when test="${task.importance eq 1}">
							<div
								class="inboxTask_importance"
								style="background-color:red;"
							>
							</div>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${task.importance eq 2}">
							<div
								class="monthly_importance"
								style="background-color:yellow;"
							>
							</div>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${task.importance eq 3}">
							<div
								class="monthly_importance"
								style="background-color:white;"
							>
							</div>
						</c:when>
					</c:choose>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

	
<script src="${contextPath }/resources/javascript/baselayout/task/listInboxTasks.js"></script>