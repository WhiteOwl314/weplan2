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
			width:10px;
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
				</tr>
				<c:forEach
					var="yearlyPlan"
					items="${yearlyPlanList }"
				>
					<tr
						id="yearlyPlan_${yearlyPlan.id }" 
					>
						<input type="hidden" value="${yearlyPlan.startDate }" id="${yearlyPlan.id }_startDate"/>
						<input type="hidden" value="${yearlyPlan.limitDate }" id="${yearlyPlan.id }_limitDate"/>
						<script>
							/* 년도 구하기 */
							var url_search = $(location).attr('search');
							var year_array =  url_search.split('=');
							var year = parseInt(year_array[1]);
							var year_february_day = new Date(year,2,0).getDate()
							/* 년도 구하기 */

							/* 날짜 차이 구하기 */
						  	var sdd = '${yearlyPlan.startDate}'
							var edd = '${yearlyPlan.limitDate}'
							var ar1 = sdd.split('-');
							var ar2 = edd.split('-');
							var da1 = new Date(ar1[0], ar1[1], ar1[2]);
							var da2 = new Date(ar2[0], ar2[1], ar2[2]);
							var dif = da2 - da1;
							var cDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
							var cMonth = cDay * 30;// 월 만듬
							var cYear = cMonth * 12; // 년 만듬
							var calCDay =  parseInt(dif/cDay);
						  /*document.getElementById('years').value = parseInt(dif/cYear)
							document.getElementById('months').value = parseInt(dif/cMonth)
							document.getElementById('days').value = parseInt(dif/cDay) */
							/* 날짜 차이 구하기 */
							
							/* 1월 1일과의 차이 */
							var firstDay = new Date(year,1,1);
						  	var firstDif = da2 - firstDay;
						  	var firstCalCDay = parseInt(firstDif/cDay);
							/* 1월 1일과의 차이 */
							

							/* 12월 31일과의 차이 */
							var lastDay = new Date(year,12,31);
						  	var lastDif = lastDay - da1;
						  	var lastCalCDay = parseInt(lastDif/cDay);
							/* 12월 31일과의 차이 */
							
							/* 1월 1일과 start의 차이 */
							var firstDay = new Date(year,1,1);
						  	var dif3 = da1 - firstDay;
						  	var CalCDay3 = parseInt(dif3/cDay);
							/* 1월 1일과 start의 차이 */
							
							/* 12월 31일과 limit의 차이 */
							var lastDay = new Date(year,12,31);
						  	var dif4 = lastDay - da2;
						  	var CalCDay4 = parseInt(dif4/cDay);
							/* 12월 31일과의 last차이 */



							
							/* 넘어온 년도 구하기 */
							var startYear = parseInt(ar1[0]);
							var limitYear = parseInt(ar2[0]);
							/* 넘어온 년도 구하기 */

							/* yearlyPlan 만들기 */
							if(startYear < year && year < limitYear ){
								if(year_february_day == 29){
									var statement = "<td class='day' colspan='366' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"+ "${yearlyPlan.title}" +"</td>"
									$('#yearlyPlan_${yearlyPlan.id }').append(
											statement
									)
								} else if (year_february_day == 28){
									var statement = "<td class='day' colspan='365' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"+ "${yearlyPlan.title}" +"</td>"
									$('#yearlyPlan_${yearlyPlan.id }').append(
											statement
									)
								}
							} else if (startYear < year && year == limitYear){
								
								var statement = "<td class='day' colspan='"+ firstCalCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"+ "${yearlyPlan.title}" +"</td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement
								)
							} else if (startYear == year && year < limitYear){
								var statement1 = "<td class='day' colspan='"+ CalCDay3 +"'></td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement1
								)
								var statement2 = "<td class='day' colspan='"+ calCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"+ "${yearlyPlan.title}" +"</td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement2
								)
							} else if (startYear == year && year == limitYear){
								var statement1 = "<td class='day' colspan='"+ CalCDay3 +"'></td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement1
								)
								var statement2 = "<td class='day' colspan='"+ calCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"+ "${yearlyPlan.title}" +"</td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement2
								)
							}

							/* yearlyPlan 만들기 */
							
						</script>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
		$('#search_submit').click(function() {
			var year = $('#search_year').val();
			location.href='${contextPath}/yearlyPlan/yearlyPlanList.do?year=' + year;
		})
		
		window.onload = function() {
			var url_search = $(location).attr('search');
			var year_array =  url_search.split('=');
			var year = year_array[1];
			var year_february_day = new Date(year,2,0).getDate()
			var week = new Array("일","월","화","수","목","금","토");
			
			/* 달 생성 */
			if(year_february_day == 29){
			  var month_day = new Array(31,29,31,30,31,30,31,31,30,31,30,31);
			  	/* 12달 */
				for(i=0; i<month_day.length; i++){
					var month = i + 1;
					var dateMonth = year + "-" + month;
					var date = dateMonth + "-" + "01";
					$('.month-container').append(
							"<td class='month' id='"+ date +"'>"+ month +"</td>"
					)
					/* 날짜 */
					for(j=0; j<(month_day[i]-1); j++){
						var day = j+2;
						if(day < 10){
							day = "0" + day;
						}
						var dateDay = dateMonth + "-" + day;
						var statement = "<td class='month' id='"+dateDay+"'>"
										+"</td>";
						$('.month-container').append(
								statement
						)
					}
				}
				
			} else if (year_february_day == 28){
			  var month_day = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
				for(i=0; i<month_day.length; i++){
					var month = i + 1;
					var dateMonth = year + "-" + month;
					var date = dateMonth + "-" + "01";
					$('.month-container').append(
							"<td class='month' id='"+ date +"'>"+ month +"</td>"
					)
					/* 날짜 */
					for(j=0; j<(month_day[i]-1); j++){
						var day = j+2;
						if(day < 10){
							day = "0" + day;
						}
						var dateDay = dateMonth + "-" + day;
						var statement = "<td class='month' id='"+dateDay+"'>"
										+"</td>";
						$('.month-container').append(
								statement
						)
					}
					
				}
			}
		}
	</script>
</body>
</html>