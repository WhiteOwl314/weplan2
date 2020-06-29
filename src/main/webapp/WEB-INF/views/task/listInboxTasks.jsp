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
	<title>Inbox</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>완료</b></td>
			<td><b>번호</b></td>
			<td><b>제목</b></td>
			<td><b>내용</b></td>
			<td><b>삭제</b></td>
		</tr>
		<c:forEach var="task" items="${inboxTasksList }">
			<tr align="center" 
			>
				<td
					onClick="location.href='${contextPath }/task/completeTask.do?id=${task.id }'"
					onMouseOver = " window.status = '${contextPath }/task/completeTask.do?id=${task.id }'" 
					onMouseOut = " window.status = '' "
					style="cursor:pointer;"
				>	
					<img  alt="checkbox" 
						class="checkbox"
						id="checkbox${task.id }"
						src="${contextPath }/resources/images/iconmonstr-checkbox-11.svg"
					>
				</td>
				<td>${task.id }</td>
				<td
					onClick="location.href='${contextPath }/task/viewTask.do?id=${task.id}'"
					onMouseOver = " window.status = '${contextPath }/task/viewTask.do?id=${task.id}'" 
					onMouseOut = " window.status = '' "
					style="cursor:pointer;"
				>
					${task.title }
				</td>
				<td>${task.content }</td>
				<td><a href="${contextPath }/task/removeTask.do?id=${task.id}">삭제하기</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			/* 체크표시 */
			$('.checkbox').click(function(event){
				var eventTarget = event.target.id;
				$('#'+eventTarget).attr("src","${contextPath }/resources/images/iconmonstr-checkbox-9.svg")
			});
			
			/* 중요도 초기값 */
			if('${task.importance}'=='1'){
				$('input:radio[name=importance]:input[value="1"]').attr("checked", true);
			} else if ('${task.importance}' == '2'){
				$('input:radio[name=importance]:input[value="2"]').attr("checked", true);
			} else if ('${task.importance}' == '3'){
				$('input:radio[name=importance]:input[value="3"]').attr("checked", true);
			}
			
			/* 기한 초기값 */
			if("${task.limitDate}" != null && "${task.limitDate}" != ""){
					var limitdate = "${task.limitDate }";
					var strArray= limitdate.split(' ');
					var changedDateArray = strArray[0].split('/'); 
					console.log(strArray);
					console.log(changedDateArray);
					
					var changedDate = changedDateArray[0]+ "-" 
						+ changedDateArray[1] + "-"
						+ changedDateArray[2];

					console.log(changedDate);
					
					$("#date-view").attr("value",changedDate);
					$("#time-view").attr("value",strArray[1]);

			}
			
			/* due 버튼 */
			$('#due-view').click(function(){
				$("#date-view").attr("type","date");
				$("#time-view").attr("type","time");
				
				if("${task.limitDate }" != null && "${task.limitDate }" != "" ){
					$("#date-view").attr("value",changedDate);
					$("#time-view").attr("value",strArray[1]);
				} else {
					/* 현재시간으로 */
					document.getElementById('date-view').valueAsDate = new Date();
					$("#time-view").attr("value","18:00");
				}

			});
			
			
			/* nullDate 버튼 */
			$('#nullDate-view').click(function(){
				$("#date-view").attr("type","hidden");
				$("#time-view").attr("type","hidden");
				$("#date-view").attr("value","0000-00-00");
				$("#time-view").attr("value","00:00");
			});

		});

		
	</script>
</body>
</html>