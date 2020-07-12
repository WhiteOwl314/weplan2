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
		
		/*-- POPUP common style S ======================================================================================================================== --*/
		#mask {
			position: absolute;
			left: 0;
			top: 0;
			z-index: 999;
			background-color: #000000;
			display: none; }

		.layerpop {
			display: none;
			z-index: 1000;
			border: 2px solid #ccc;
			background: #fff;
			cursor: move; }

		.layerpop_area .title {
			padding: 10px 10px 10px 10px;
			border: 0px solid #aaaaaa;
			background: #f1f1f1;
			color: #3eb0ce;
			font-size: 1.3em;
			font-weight: bold;
			line-height: 24px; }

		.layerpop_area .layerpop_close {
			width: 25px;
			height: 25px;
			display: block;
			position: absolute;
			top: 10px;
			right: 10px;
			background: transparent url('btn_exit_off.png') no-repeat; }

		.layerpop_area .layerpop_close:hover {
			background: transparent url('btn_exit_on.png') no-repeat;
			cursor: pointer; }

		.layerpop_area .content {
			width: 96%;    
			margin: 2%;
			color: #828282; }
		/*-- POPUP common style E --*/
		
	</style>
	
	<!-- draggable -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
	
</head>
<body>
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
									var statement = "<td class='day' colspan='366' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"
													+ "${yearlyPlan.title}" 
													+ "<span id='update_"+${yearlyPlan.id}+"' onclick='javascript:goDetail("+${yearlyPlan.id}+")'>수정</span>"
													+ "<span><a href=''>삭제</a></span>"
													+"</td>"
									$('#yearlyPlan_${yearlyPlan.id }').append(
											statement
									)
								} else if (year_february_day == 28){
									var statement = "<td class='day' colspan='365' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"
													+ "${yearlyPlan.title}" 
													+ "<span id='update_"+${yearlyPlan.id}+"' onclick='javascript:goDetail("+${yearlyPlan.id}+")'>수정</span>"
													+ "<span><a href=''>삭제</a></span>"
													+"</td>"
									$('#yearlyPlan_${yearlyPlan.id }').append(
											statement
									)
								}
							} else if (startYear < year && year == limitYear){
								
								var statement = "<td class='day' colspan='"+ firstCalCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"
												+ "${yearlyPlan.title}" 
												+ "<span id='update_"+${yearlyPlan.id}+"' onclick='javascript:goDetail("+${yearlyPlan.id}+")'>수정</span>"
												+ "<span><a href=''>삭제</a></span>"
												+"</td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement
								)
							} else if (startYear == year && year < limitYear){
								var statement1 = "<td class='day' colspan='"+ CalCDay3 +"'></td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement1
								)
								var statement2 = "<td class='day' colspan='"+ calCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"
												+ "${yearlyPlan.title}" 
												+ "<span id='update_"+${yearlyPlan.id}+"' onclick='javascript:goDetail("+${yearlyPlan.id}+")'>수정</span>"
												+ "<span><a href=''>삭제</a></span>"
												+"</td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement2
								)
							} else if (startYear == year && year == limitYear){
								var statement1 = "<td class='day' colspan='"+ CalCDay3 +"'></td>"
								$('#yearlyPlan_${yearlyPlan.id }').append(
										statement1
								)
								var statement2 = "<td class='day' colspan='"+ calCDay +"' id='"+ "yearlyPlan_" + ${yearlyPlan.id} +"' align='center' style='background-color:yellow;'>"
												+ "${yearlyPlan.title}" 
												+ "<span id='update_"+${yearlyPlan.id}+"' onclick='javascript:goDetail("+${yearlyPlan.id}+")'>수정</span>"
												+ "<span><a href=''>삭제</a></span>"
												+"</td>"
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
		
    <!--Popup Start -->
		<div>
			<!-- 팝업뜰때 배경 -->
			<div id="mask"></div>

			<div id="layerbox" class="layerpop"
				style="width: 700px; height: 350px;">
				<article class="layerpop_area">
					<div class="title">레이어팝업 타이틀</div>
					<a href="javascript:popupClose();" class="layerpop_close"
						id="layerbox_close"></a> <br>
					<div class="content">
						<form 
							action="${contextPath }/yearlyPlan/updateYearlyPlan.do"
							method="post"	
						>
							<p>
								<label>IMPORTANCE</label> 
								<label><input type="radio" name="importance" value="1">상</label>
								<label><input type="radio" name="importance" value="2">중</label>
								<label><input type="radio" name="importance" value="3">하</label>
							</p>
							<p>
								<label>TITLE</label> 
								<input type="text" id="popUp-title" name="title"> 
							</p>
							<p>
								<label>CONTENT</label> 
								<textarea id="popUp-content" name="content" cols="50" rows="10"></textarea> 
							</p>
							<p>
								<label>STARTDATE</label> 
								<input type="date" id="popUp-startDate" name="startDate"> 
								<input type="hidden" id="popUp-start_due" value="due">
								<input type="button" id="popUp-start_nullDate" value="x">
							</p>
							
							<p>
								<label>LIMITDATE</label> 
								<input type="date" id="popUp-limitDate" name="limitDate"> 
								<input type="hidden" id="popUp-due" value="due">
								<input type="button" id="popUp-nullDate" value="x">
							</p>
							
							<input type="hidden" name="member_id" value="${ member.id }">
							<input type="hidden" name="id" id="popUp-id">

							<button type="submit">수정</button>
						</form>
					</div>
				</article>
			</div>

			
		</div>
    <!--Popup End -->
		
		
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
		
		/* 레이어팝업 */
		
		function wrapWindowByMask() {
			//화면의 높이와 너비를 구한다.
			var maskHeight = $(document).height(); 
			var maskWidth = $(window).width();

			//문서영역의 크기 
			console.log( "document 사이즈:"+ $(document).width() + "*" + $(document).height()); 
			//브라우저에서 문서가 보여지는 영역의 크기
			console.log( "window 사이즈:"+ $(window).width() + "*" + $(window).height());        

			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask').css({
				'width' : maskWidth,
				'height' : maskHeight
			});

			//애니메이션 효과
			//$('#mask').fadeIn(1000);      
			$('#mask').fadeTo("slow", 0.5);
		}

		function popupOpen() {
			$('.layerpop').css("position", "absolute");
			//영역 가운에데 레이어를 뛰우기 위해 위치 계산 
			$('.layerpop').css("top",(($(window).height() - $('.layerpop').outerHeight()) / 2) + $(window).scrollTop());
			$('.layerpop').css("left",(($(window).width() - $('.layerpop').outerWidth()) / 2) + $(window).scrollLeft());
			$('.layerpop').draggable();
			$('#layerbox').show();
		}

		function popupClose() {
			$('#layerbox').hide();
			$('#mask').hide();
		}

		function goDetail(yearlyPlan_id) {

			/*팝업 오픈전 별도의 작업이 있을경우 구현*/ 

			popupOpen(); //레이어 팝업창 오픈 
			wrapWindowByMask(); //화면 마스크 효과 
			
			$.ajax({
				url : "${contextPath}/yearlyPlan/popUpYearlyPlanView.do",
				dataType :"json",
				type : "POST",
				data : {
					id : yearlyPlan_id
				},
				success : function(result) {
					var title = decodeURIComponent( result.title );
					var content = decodeURIComponent( result.content );
					var importance = decodeURIComponent( result.importance );
					var startDate = decodeURIComponent(result.startDate);
					var limitDate = decodeURIComponent(result.limitDate);
					$('#popUp-id').val(result.id);
					$('#popUp-title').val(title);
					$('#popUp-content').val(content);
					
					if(limitDate === 'null'){
						$('#popUp-limitDate').attr("type","hidden");
						$('#popUp-limitDate').val("0000-00-00");
						$("#popUp-due").attr("type","button");
						$("#popUp-nullDate").attr("type","hidden");
					} else {
						$('#popUp-limitDate').attr("type","date")
						$('#popUp-limitDate').val(limitDate);
						$("#popUp-due").attr("type","hidden");
						$("#popUp-nullDate").attr("type","button");
					}
					
					if(startDate === 'null'){
						$('#popUp-startDate').attr("type","hidden");
						$('#popUp-startDate').val("0000-00-00");
						$("#popUp-start_due").attr("type","button");
						$("#popUp-start_nullDate").attr("type","hidden");
					} else {
						$('#popUp-startDate').attr("type","date")
						$('#popUp-startDate').val(startDate);
						$("#popUp-start_due").attr("type","hidden");
						$("#popUp-start_nullDate").attr("type","button");
					}

					
		    		/* 중요도 초기값 */
		    		if(importance=='1'){
		    			$('input:radio[name=importance]:input[value="1"]').attr("checked", true);
		    		} else if (importance == '2'){
		    			$('input:radio[name=importance]:input[value="2"]').attr("checked", true);
		    		} else if (importance == '3'){
		    			$('input:radio[name=importance]:input[value="3"]').attr("checked", true);
		    		}

				},

			})
		};
		/* 레이어팝업 */


	</script>
</body>
</html>