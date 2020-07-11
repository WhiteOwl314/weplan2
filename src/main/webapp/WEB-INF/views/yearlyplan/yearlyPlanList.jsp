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
	<title>Insert title here</title>
	<style type="text/css">
		.auto{
			overflow: auto;
		}
		.month-container {
		}
		table tr td{
			width:300px;
		}
	</style>
</head>
<body>

	<c:forEach
		var="yearlyPlan"
		items="${yearlyPlanList }"
	>
		${yearlyPlan.title }
	</c:forEach>
	
	<div>
		<div>
			<input 
				id="search_year"
				type="text"
			>
			<input 
				type="button" value="검색"
				id="search_submit"
			>
		</div>
		
		<div>
			<form 
				action="${contextPath}/yearlyPlan/addYearlyPlan.do"
				method="post"
			>
				<label><input type="radio" name="importance" value="1">상</label>
				<label><input type="radio" name="importance" value="2">중</label>
				<label><input type="radio" name="importance" value="3">하</label>
				<input type="text" name="title" placeholder="title">
				<input type="text" name="content" placeholder="content"/>
				<input id="startDate" type="date" name="startDate" />
				<input id="limitDate" type="date" name="limitDate" />
				<input type="hidden" name="member_id" value="${ member.id }">
				<input type="submit" value="save"/>
			</form>
		</div>
			
		
		<div
			class="auto"
		>
			<table 
				border="1"
				align="center"
				width="1200px"
			>
				<tr
					class= "month-container"
				>
					<td class="month">
						1
					</td>
					<td class="month">
						2
					</td>
					<td class="month">
						3
					</td>	
					<td class="month">
						4
					</td>
					<td class="month">
						5
					</td>
					<td class="month">
						6
					</td>
					<td class="month">
						7
					</td>
					<td class="month">
						8
					</td>
					<td class="month">
						9
					</td>
					<td class="month">
						10
					</td>
					<td class="month">
						11
					</td>
					<td class="month">
						12
					</td>
				</tr>
				<c:forEach
					var="yearlyPlan"
					items="${yearlyPlanList }"
				>
					<tr>
						<td>
							dk
						</td>
						<td >
						</td>
						<td 
							colspan="3"
							align="center"
							style="background-color:yellow;"
						>
							${yearlyPlan.title }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		$('#search_submit').click(function() {
			var year = $('#search_year').val();
			console.log(year);
			location.href='${contextPath}/yearlyPlan/yearlyPlanList.do?year=' + year;
		})
	</script>
</body>
</html>