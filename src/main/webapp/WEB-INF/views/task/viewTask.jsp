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
	<form name="frmTask" method="post" action="${contextPath }" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					완료여부
				   </td>
				   <td>
					<input type="text" value="${task.isCompleted }" name="isCompleted" />
				   </td>  
			  </tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					중요도
				   </td>
				   <td>
					<input type="text" value="${task.importance }" name="importance" />
				   </td>  
			  </tr>

			<tr>
				<td width=150 align="center" bgcolor=#FF9933>
					제목
				</td>
				<td>
					<input type="text" value="${task.title }" name="title" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					내용
				   </td>
				   <td>
					<textarea rows="20" cols="60"  name="content"  id="i_content" >${task.content }</textarea>
				   </td>  
			  </tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					기한
				   </td>
				   <td>
					<input type="text" value="${task.limitDate }" name="limitDate" />
				   </td>  
			  </tr>
		</table>
		
		
	</form>

    <form action="${contextPath }/task/updateInboxTask.do" method="post">
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
			<input id="date-view" type="hidden" name="date"/>
			<input id="time-view" type="hidden" name="time"/>
			<input id="due-view" type="button" name="due" value="due">
			<input id="nullDate-view" type="button" name="nullDate" value="nullDate">
    	</div>
	    <input type="submit" value="save"/>
    </form>

    <script>
    	$(document).ready(function(){
    		
    		/* due 버튼 */
    		$('#due-view').click(function(){
    			$("#date-view").attr("type","date");
    			$("#time-view").attr("type","time");
    			
    			if("${task.limitDate }" != null && "${task.limitDate }" != "" ){
    				
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