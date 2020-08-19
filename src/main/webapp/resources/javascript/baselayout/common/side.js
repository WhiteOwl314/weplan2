/**
 * 
 */

$('document').ready(function() {

		 if($(location).attr('pathname') == `/weplan/task/listInboxTasks.do` ){
			 $('#side_menu-inbox').css('background-color','#EF802F');
			 $('#side_menu-inbox .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-inbox').addClass('on');
		 } else if($(location).attr('pathname') == `/weplan/task/weeklyTaskList.do`) {
			 $('#side_menu-weeklyTaskList').css('background-color','#EF802F');
			 $('#side_menu-weeklyTaskList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-weeklyTaskList').addClass('on');
		 } else if(
				 $(location).attr('pathname') == `/weplan/absoluteValue/absoluteValueList.do`
				 || $(location).attr('pathname') == `/weplan/absoluteValue/absoluteValueView.do`
			 ) {
			 $('#side_menu-absoluteValueList').css('background-color','#EF802F');
			 $('#side_menu-absoluteValueList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-absoluteValueList').addClass('on');
		 } else if($(location).attr('pathname') == "/weplan/goal/goalList.do") {
			 $('#side_menu-goalList').css('background-color','#EF802F');
			 $('#side_menu-goalList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-goalList').addClass('on');
		 }else if($(location).attr('pathname') == "/weplan/monthlyPlan/yearlyView.do") {
			 $('#side_menu-yealyPlan').css('background-color','#EF802F');
			 $('#side_menu-yealyPlan .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-yealyPlan').addClass('on'); 
		}else if($(location).attr('pathname') == "/weplan/weeklyPlan/monthlyView.do") {
			 $('#side_menu-monthlyPlan').css('background-color','#EF802F');
			 $('#side_menu-monthlyPlan .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-monthlyPlan').addClass('on'); 
		}else if($(location).attr('pathname') == "/weplan/task/weeklyView.do") {
			 $('#side_menu-weeklyTaskList').css('background-color','#EF802F');
			 $('#side_menu-weeklyTaskList .side-menu-item-text').css('color','white');
			 $('.side-menu-item').removeClass('on');
			 $('#side_menu-weeklyTaskList').addClass('on'); 
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
		 let todayForWeeklyView = new Date();   
		 var yearForWe = todayForWeeklyView.getFullYear();
		 var fullMonthForWe = addZero(todayForWeeklyView.getMonth() + 1);
		 let dateForWe = todayForWeeklyView.getDate();
		 let fullDateForWe = yearForWe + "-" + fullMonthForWe + "-" + dateForWe;
		 let thisWeek =  getWeekByDate(fullDateForWe);

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

