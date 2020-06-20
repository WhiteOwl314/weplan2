<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일</title>
</head>
<body>

    <form action="${contextPath }/task/updateTask.do" method="post">
    	<input type="hidden" name="taskNO" value="${task.id }">
    	<div>
    		<h1>중요도</h1>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
    	</div>
    	<div>
    		<h1>제목</h1>
			<input type="text" name="title" value="${task.title }"/>
    	</div>
    	<div>
    		<h1>내용</h1>
			<input type="text" name="content" value="${task.content }"/>
    	</div>
    	
    	<div>
    		<h1>기한</h1>
    		<c:if test="${task.limitDate == null }">
				<input id="date-view" type="hidden" name="date"/>
				<input id="time-view" type="hidden" name="time"/>
				<input id="due-view" type="button" name="due" value="due">
				<input id="nullDate-view" type="button" name="nullDate" value="nullDate">
    		</c:if>
    		<c:if test="${task.limitDate != null }">
				<input id="date-view" type="date" name="date" value= "" />
				<input id="time-view" type="time" name="time"/>
				<input id="due-view" type="button" name="due" value="due">
				<input id="nullDate-view" type="button" name="nullDate" value="nullDate">
    		</c:if>
    	</div>
	    <input type="submit" value="save"/>
    </form>

    <script>
    	$(document).ready(function(){
    		
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