/**
 * 
 * 
 */

function getMonthlyPlanListByMonth(month) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/monthlyPlan/getMonthlyPlanListByMonth.do";
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
		return data;
}

$(document).ready(function() {
	let month = location.search.split("=")[1]; 
	
	//monthlyPlan 리스트 생성
		//조회
		let monthlyPlanList = getMonthlyPlanListByMonth(month);
		//조회
		//배치
		for(var i in monthlyPlanList){
			let id = decodeURIComponent( monthlyPlanList[i].id ); 
			let title = decodeURIComponent( monthlyPlanList[i].title );
			let content = decodeURIComponent( monthlyPlanList[i].content );
			let importance = decodeURIComponent( monthlyPlanList[i].importance );
			let isCompleted = decodeURIComponent(monthlyPlanList[i].isCompleted);
			let month = decodeURIComponent(monthlyPlanList[i].month);
			let yearly_plan_id = decodeURIComponent(monthlyPlanList[i].yearly_plan_id);
			
			$(`#monthlyView_body_left .monthlyView_body_left_padding`).append(
					`<div
						id="monthlyView_monthlyPlan_${id}"
					>
						<div
							class="monthly_completed"
						>
							<img
								alt="checkbox"
								src="${contextPath }weplan/resources/images/iconmonstr-checkbox-11.svg"
							>
						</div>
						<div
							class="monthly_completed_on"
						>
							<img
								alt="checkbox"
								src="${contextPath }weplan/resources/images/iconmonstr-checkbox-9.svg"
							>
						</div>
						<div
							class="monthly_title_container"
						>
							<div
								class="monthly_title"
							>
								${title}
							</div>
						</div>
						<div
							class="monthly_importance"
						>
						</div>
					</div>`
			);

			//특성
				//complete
					//초기값
					if(isCompleted == 1){
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed`).css("display",'none');
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed_on`).css("display",'block');
						$(`#monthlyView_monthlyPlan_${id} .monthly_title`).css("text-decoration","line-through");
					}
					//초기값
					//monthlyPlan-complete
					$(`#monthlyView_monthlyPlan_${id} .monthly_completed`).click(function(event) {
						var url = contextPath + "weplan/monthlyPlan/completeMonthlyPlan.do?id=" + id;
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed`).css("display",'none');
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed_on`).css("display",'block');
						$(`#monthlyView_monthlyPlan_${id} .monthly_title`).css("text-decoration","line-through");
						location.href = url;
					});
					//monthlyPlan-complete
					//monthlyPlan-complete_on
					$(`#monthlyView_monthlyPlan_${id} .monthly_completed_on`).click(function(event) {
						var url = contextPath + "weplan/monthlyPlan/notCompleteMonthlyPlan.do?id=" + id;
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed`).css("display",'block');
						$(`#monthlyView_monthlyPlan_${id} .monthly_completed_on`).css("display",'none');
						$(`#monthlyView_monthlyPlan_${id} .monthly_title`).css("text-decoration","none");
						location.href = url;
					});
					//monthlyPlan-complete_on
				//complete
				
				/* IMPORTANCE */
				if(importance=='1'){
					$(`#monthlyView_monthlyPlan_${id} .monthly_importance`).css("background-color", 'red');
				} else if (importance == '2'){
					$(`#monthlyView_monthlyPlan_${id} .monthly_importance`).css("background-color", 'yellow');
				} else if (importance == '3'){
					$(`#monthlyView_monthlyPlan_${id} .monthly_importance`).css("background-color", 'white');
				}
				/* IMPORTANCE */
				
				
				//view
				$(`#monthlyView_monthlyPlan_${id} .monthly_title_container`).click(function() {
					popupReset();	
					let form_title = "Monthly Plan";
					let url = contextPath + "weplan/monthlyplan/updateMonthlyPlan.do";
					let monthlyPlan = getMonthlyPlan(id);
					$('.layerpop .startDate_container').css('display','none');
					$('.layerpop .limitDate_container').css('display','none');
					$('.layerpop .month_container').css('display','block');
					
					let title = decodeURIComponent( monthlyPlan.title );
					let content = decodeURIComponent( monthlyPlan.content );
					let importance = decodeURIComponent( monthlyPlan.importance );
					let month = decodeURIComponent(monthlyPlan.month);
					let isCompleted = decodeURIComponent(monthlyPlan.isCompleted);

					/* IMPORTANCE */
					if(importance=='1'){
						$('.layerpop .importance1').prop("checked", true);
					} else if (importance == '2'){
						$('.layerpop .importance2').prop("checked", true);
					} else if (importance == '3'){
						$('.layerpop .importance3').prop("checked", true);
					}
					/* IMPORTANCE */

					//title
						$('.layerpop .layerpop_title').attr('value',title);
					//title

					//month
					if(month === 'null'){
						$('.layerpop .layerpop_month_form').attr('value','');
					} else{
						$('.layerpop .layerpop_month_form').attr('value',month);
					}
					//month

					//content
					if(content === 'null'){
						$('.layerpop #layerpop_form_content').text('');
					} else{
						$('.layerpop #layerpop_form_content').text(content);
					}
					//content

					//id
					$('.layerpop .layerpop_id').attr('value',id);
					//id
									
					putPlaceholderAtMonth();
					popUpSetting(form_title, url);
				});
				//view

			//특성
		}
		//배치
	//monthlyPlan 리스트 생성
	
	//week 생성
	//week 생성
	
	//weeklyPlan 배치
	//weeklyPlan 배치
});