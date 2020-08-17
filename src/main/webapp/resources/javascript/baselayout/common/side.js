/**
 * 
 */

$('document').ready(function() {
	getWeekByDate('2020-08-16');

		 if($(location).attr('pathname') == `${contextPath}weplan/task/listInboxTasks.do` ){
			 $('#side_menu-inbox').css('background-color','#EF802F');
			 $('#side_menu-inbox .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-inbox').addClass('on');
		 } else if($(location).attr('pathname') == `${contextPath}weplan/task/weeklyTaskList.do`) {
			 $('#side_menu-weeklyTaskList').css('background-color','#EF802F');
			 $('#side_menu-weeklyTaskList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-weeklyTaskList').addClass('on');
		 } else if(
				 $(location).attr('pathname') == `${contextPath}weplan/absoluteValue/absoluteValueList.do`
				 || $(location).attr('pathname') == `${contextPath}weplan/absoluteValue/absoluteValueView.do`
			 ) {
			 $('#side_menu-absoluteValueList').css('background-color','#EF802F');
			 $('#side_menu-absoluteValueList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-absoluteValueList').addClass('on');
		 } else if($(location).attr('pathname') == "${contextPath}/goal/goalList.do") {
			 $('#side_menu-goalList').css('background-color','#EF802F');
			 $('#side_menu-goalList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-goalList').addClass('on');
		 } 

		 /* 현재날짜로 세팅 */
		 var today = new Date();   
		 var year = today.getFullYear();
		 var href = `${contextPath}weplan/monthlyPlan/yearlyView.do?year=` + year;
		 $('#sideMenu_yearlyPlan_a').attr('href',href);
		 /* 현재날짜로 세팅 */

		 /* 현재날짜로 세팅 */
		 var today = new Date();   
		 var year = today.getFullYear();
		 var fullMonth = addZero(today.getMonth() + 1);
		 var href = `${contextPath}weplan/weeklyPlan/monthlyView.do?month=` + year + "-" + fullMonth;
		 $('#sideMenu_monthlyPlan_a').attr('href',href);
		 /* 현재날짜로 세팅 */

		 /* 현재날짜로 세팅 */
		 var today = new Date();   
		 var year = today.getFullYear();
		 var fullMonth = addZero(today.getMonth() + 1);
		 var date = today.getDate();
		 var fullDate = year + "-" + fullMonth + "-" + date;
		 var thisWeek =  getWeekByDate(fullDate);

		 var href = `${contextPath}weplan/task/weeklyView.do?month=${year}-${fullMonth}&week=${thisWeek}`;
		 $('#sideMenu_weeklyView_a').attr('href',href);
		 /* 현재날짜로 세팅 */
		 

	/* 사이드메뉴에 마우스 올렸을때 */
	$('.side-menu-item').hover(function() {
			$(this).css('background-color','#EF802F');
			$(this).children('.side-menu-item-text').css('color','white');
		}, function() {
			if($(this).hasClass("on") == false ){
				$(this).css('background-color','#FFCC57');
				$(this).children('.side-menu-item-text').css('color','#3B3B3B');
			}
		}
	);
})

