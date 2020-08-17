/**
 * 
 */

$(document).ready(function() {
	let locationSearch = addZero(location.search);
	let searchMonth = locationSearch.split("&")[0].split("=")[1];
	let searchWeek =  locationSearch.split("&")[1].split("=")[1];
	
	//weeklyPlanAdd

		//hover 효과
		$(`#weeklyView_body_left_add_button`).hover(
				function() {
						$(`#weeklyView_body_left_add_button img`).css('background-color', '#e2e2e2');
				},function(){
						$(`#weeklyView_body_left_add_button img`).css('background-color', '#F7F7F7');
				}
		);
		//hover 효과
		
		//popUp
		$(`#weeklyView_body_left_add_button`).click(function() {
			
				popupReset();	
				let title = "WeeklyPlan 추가";
				let url = contextPath + "weplan/weeklyplan/addWeeklyPlan.do";
				$('.layerpop .startDate_container').css('display','none');
				$('.layerpop .limitDate_container').css('display','none');
				$('.month_container .layerpop_month_form').attr('value',searchMonth);
				$('#layerpop_week').attr('value',searchWeek);
				checkInitialImportance();
				popUpSetting(title, url);
		});
		//popUp

	//weeklyPlanAdd
		
	
})	
