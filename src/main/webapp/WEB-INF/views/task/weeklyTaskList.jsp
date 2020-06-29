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
		  display: inline-block;
		  width: 50px;
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
	</style>
</head>
<body>

	<div class="bucket" ondragover="allowDrop();" ondrop="dropItem(event);">
		<c:forEach var="task" items="${weeklyTaskList}">
			${task.day_of_week }
			${task.id}
			<c:if test="${task.day_of_week eq '2'}">
				<span class="item" id="${task.id} " ondragstart="dragStart(event);" draggable="true" ondragend="dragEnd(event)">
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
		<div class="bucket" ondragover="allowDrop();" ondrop="dropItem(event);"></div>
	</div>
	
	<script type="text/javascript">
		allowDrop = function() {
		  event.preventDefault();
		};

		dropItem = function() {
		  var _targetEle = event.target;
		  var _id = event.dataTransfer.getData('text');
		  var _moveEle = document.getElementById(_id );
		  console.log(_moveEle);
		  _targetEle.append(_moveEle);
		};

		dragStart = function() {
		  var _thisEle = event.target;
		  var _thisId = _thisEle.id;
		  _thisEle.classList.add('is-dragging');
		  event.dataTransfer.setData('text/plain', _thisId);
		};

		dragEnd = function() {
		  var _thisEle = event.target;
		  _thisEle.classList.remove('is-dragging');
		};
	</script>
</body>
</html>