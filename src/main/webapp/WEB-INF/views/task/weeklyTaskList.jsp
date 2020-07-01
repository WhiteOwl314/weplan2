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

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEEKLY TASK LIST</title>
	<style type="text/css">
		.item {
		  background: #999;
		  height: 50px;
		  color: #fff;
		  padding: 12px 20px;
		  box-sizing: border-box;
		  margin: 0 0 10px;
		}
		.item.is-dragging {
		  background-color: red;
		}

		.bucket {
		  display: block;
		  width: 200px;
		  height: 90px;
		  margin: 0 0 18px;
		  border: 1px solid;
		  text-align: center;
		  padding: 15px;
		}
		.bucket.is-selecting{
			background-color: blue;
		}
	</style>
</head>
<body>

	<div 
		id="${dayList['monday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
	>
		<c:out value="${dayList['monday']}" />
		<c:out value="${dayList['tuesday']}" />
		<c:out value="${dayList['wednesday']}" />
		<c:out value="${dayList['thursday']}" />
		<c:out value="${dayList['friday']}" />
		<c:out value="${dayList['saturday']}" />
		<c:out value="${dayList['sunday']}" />

		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '2'}">
				<div 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</div>
			</c:if>
		</c:forEach>
	</div>


	<div 
		id="${dayList['tuesday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '3'}">
				<span 
					class="item" 
					id="${task.id} " 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['wednesday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '4'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['thursday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '5'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['friday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '6'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['saturday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '7'}">
				<span 
					class="item" id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['sunday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '1'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	<div class="test wrap">
		<div class="drag-items">
			<span class="item" id="1" ondragstart="dragStart(event);" draggable="true" ondragend="dragEnd(event)">1</span>
			<span class="item" id="2" ondragstart="dragStart(event);" draggable="true" ondragend="dragEnd(event)">2</span>
			<span class="item" id="3" ondragstart="dragStart(event);" draggable="true" ondragend="dragEnd(event)">3</span>
		</div>
		<div 
			class="bucket" 
			ondragover="allowDrop();" 
			ondrop="dropItem(event);"
			ondragenter="dragEnter();"
			ondragleave="dragLeave();"
		>
		</div>
	</div>
	
	<script type="text/javascript">
	
		var task_id = "";
		var date = "";
	
		dragEnter = function() {
		  var _targetEle = event.target;
		  _targetEle.classList.add('is-selecting');
			
		}
		
		dragLeave = function() {
		  var _targetEle = event.target;
		  _targetEle.classList.remove('is-selecting');
		}
	
		allowDrop = function() {
		  event.preventDefault();
		};

		dropItem = function() {
		  var _targetEle = event.target;
		  var _id = event.dataTransfer.getData('text');
		  var _moveEle = document.getElementById(_id );
		  console.log(_moveEle);
		  _targetEle.append(_moveEle);
		  date = _targetEle.id;
		  window.location.href="#";
		  console.log(task_id);
		  console.log(date);
		};

		dragStart = function() {
		  var _thisEle = event.target;
		  var _thisId = _thisEle.id;
		  _thisEle.classList.add('is-dragging');
		  event.dataTransfer.setData('text/plain', _thisId);
		  task_id = _thisId;
		};

		dragEnd = function() {
		  var _thisEle = event.target;
		  _thisEle.classList.remove('is-dragging');
		};
	</script>
</body>
</html>