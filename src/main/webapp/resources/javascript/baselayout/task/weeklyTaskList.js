/**
 * 
 */
	function getWeeklyPlanListbyOnlyMonth(month) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/weeklyplan/getWeeklyPlanListbyOnlyMonth.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				month : month
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		console.log(data);
		
		
		
		return data;
	}
	

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
	
	//weeklyPlanList
		//weeklyPlanList 가져오기
		let weeklyPlanList = getWeeklyPlanListbyOnlyMonth(searchMonth);
		//weeklyPlanList 가져오기

		//weeklyPlanList 배치하기
		//weeklyPlanList 배치하기
	//weeklyPlanList
		
	
})	
