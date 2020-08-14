/**
 * 
 * 
 */
function popUp_getWeeklyPlan(id) {
	var url = contextPath + "weplan/weeklyPlan/popUpWeeklyPlanView.do";
	$.ajax({
		url : url,
		dataType :"json",
		type : "POST",
		data : {
			id : id
		},
		success : function(result) {
			let id = decodeURIComponent( result.id );
			let title = decodeURIComponent( result.title );
			let content = decodeURIComponent( result.content );
			let importance = decodeURIComponent( result.importance );
			let month = decodeURIComponent(result.month);
			let yearly_plan_id = decodeURIComponent(result.yearly_plan_id);
			let week = decodeURIComponent(result.week);
			
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
				$('.layerpop .layerpop_month_form').attr('value', '');
			} else{
				$('.layerpop .layerpop_month_form').attr('value', month);
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

			//yearlyPlan_id
			$('.layerpop #layerpop_yearlyPlan_id').attr('value',yearly_plan_id);
			//yearlyPlan_id

			//week
			$('.layerpop #layerpop_week').attr('value',week);
			//week
		},

	})
	
}

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

function getWeeklyPlanListByMonth(month) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/weeklyplan/getWeeklyPlanListByOnlyMonth.do";
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
	let month = addZero(location.search.split("=")[1]); 
	console.log(month);
	
	//monthlyPlanAdd

		//hover 효과
		$(`#monthlyView_body_left_add_button`).hover(
				function() {
						$(`#monthlyView_body_left_add_button img`).css('background-color', '#e2e2e2');
				},function(){
						$(`#monthlyView_body_left_add_button img`).css('background-color', '#F7F7F7');
				}
		);
		//hover 효과
		
		//popUp
		$(`#monthlyView_body_left_add_button`).click(function() {
			
			popupReset();	
			let title = "Monthly Plan 추가";
			let url = contextPath + "weplan/monthlyplan/addMonthlyPlan.do";
			$('.layerpop .startDate_container').css('display','none');
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .month_container').css('display','block');
			$('.month_container .layerpop_month_form').attr('value',`${month}`);
			checkInitialImportance();
			putPlaceholderAtMonth();
			popUpSetting(title, url);
		});
		//popUp
		
	//monthlyPlanAdd
	
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
						class="monthlyView_monthlyPlan"
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
		//이 달에는 몇개의 주가 있을까?
		let weekList = getWeekByMonth(month);
		//이 달에는 몇개의 주가 있을까?
		
		//week-container
		for(var i in weekList){
			let thisWeekMap = weekList[i];
			let week = thisWeekMap.get("week");
			let weekFirstDay = thisWeekMap.get("weekFirstDay");
			let weekFirstDayGetDate = weekFirstDay.split("-")[2];
			let weekLastDay = thisWeekMap.get("weekLastDay");
			let weekLastDayGetDate = weekLastDay.split("-")[2];
			
			$(`#monthlyView_body_right .monthlyView_body_right_padding`).append(
					`<div
						id= "monthlyView_week_${month}_${week}"
						class="week_container"
					>
						<div
							class="header"
						>
							<div
								class="weeklyPlan_week"
							>
								${week} 주차
							</div>
							<div
								class="weeklyPlan_date"
							>
								${weekFirstDayGetDate} - ${weekLastDayGetDate}
							</div>
						</div>
						<div
							class="content"
						>
						</div>
						<div
							class="weekly_part_add"
						>
							<img
								alt="add_button" 
								src="${contextPath }/weplan/resources/images/add-black-18dp.svg"
							>
						</div>
					</div>`
			);
			
			//WeeklyPlan-add
			$(`#monthlyView_week_${month}_${week} .weekly_part_add`).hover(
					function() {
						$(`#monthlyView_week_${month}_${week} .weekly_part_add img`).css('background-color', '#e2e2e2');
						$(`#monthlyView_week_${month}_${week} .weekly_part_add img`).css('border-radius', '5px');
					},
					function() {
						$(`#monthlyView_week_${month}_${week} .weekly_part_add img`).css('background-color', '#F7F7F7');
					}
			)
			$(`#monthlyView_week_${month}_${week} .weekly_part_add`).click(function() {
				popupReset();	
				let title = "WeeklyPlan 추가";
				let url = contextPath + "weplan/weeklyplan/addWeeklyPlan.do";
				$('.layerpop .startDate_container').css('display','none');
				$('.layerpop .limitDate_container').css('display','none');
				$('.layerpop .month_container').css('display','block');
				$('.month_container .layerpop_month_form').attr('value',month);
				$('#layerpop_week').attr('value',week);
				checkInitialImportance();
				popUpSetting(title, url);
			})
			//WeeklyPlan-add

			//drop
				$(`#monthlyView_week_${month}_${week}`).on({

						'dragenter': function(event){
							$(`#monthlyView_week_${month}_${week}`).addClass('is-selecting');
						},
						'dragleave': function(event){
							$(`#monthlyView_week_${month}_${week}`).removeClass('is-selecting');
						},
						//브라우저 표중 동작 취소
						'dragover': function(event){
							event.preventDefault();
						},
						'drop': function(event){
							
							var dragId = event.originalEvent.dataTransfer.getData('text');
							let _weeklyPlanId = dragId.split('_')[5];

							var moveEle = $(`#${dragId}`).get(0);
							$(`#monthlyView_week_${month}_${week}`).removeClass('is-selecting');
							$(`#monthlyView_week_${month}_${week} .content`).append(moveEle);
							var member_id = $.trim($('#member_id').text());

							$.ajax({
								 url: `${contextPath}/weplan/weeklyPlan/moveWeek.do`,	
								 data: { 
									 id: _weeklyPlanId,
									 week: week,
									 member_id: member_id
								},
								 method: "POST", 
								 dataType: "json"
							})
						}
				});
			//drop

		}
		//week-container
	//week 생성
	
	//weeklyPlan 배치
		let weeklyPlanList = getWeeklyPlanListByMonth(month);

		for(let i in weeklyPlanList){
			let weeklyPlanId = decodeURIComponent( weeklyPlanList[i].id ); 
			let title = decodeURIComponent( weeklyPlanList[i].title );
			let content = decodeURIComponent( weeklyPlanList[i].content );
			let importance = decodeURIComponent( weeklyPlanList[i].importance );
			let isCompleted = decodeURIComponent(weeklyPlanList[i].isCompleted);
			let week = decodeURIComponent(weeklyPlanList[i].week);
			
			$(`#monthlyView_week_${month}_${week} .content`).append(
						`<div
							id="monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId}"
							class="weeklyPlan_container"
							draggable="true" 
						>
							<div
								class="completed"
							>
								<img
									alt="checkbox"
									src="${contextPath }weplan/resources/images/iconmonstr-checkbox-11.svg"
								>
							</div>
							<div
								class="completed_on"
							>
								<img
									alt="checkbox_on"
									src="${contextPath }weplan/resources/images/iconmonstr-checkbox-9.svg"
								>
							</div>
							<div
								class="weekly_title"
							>
								<div
									class="weekly_text"
								>
									${title}
								</div>
							</div>
							<div
								class="weekly_importance
							>
							</div>
						</div>`
			);
			//특성

				//weeklyPlan-initial
				if(isCompleted == 1){
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed`).css("display",'none');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'block');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","line-through");
				}
				//weeklyPlan-initial

				//weeklyPlan-complete
				$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed`).click(function(event) {
					var url = contextPath + "weplan/weeklyPlan/completeWeeklyPlan.do?id=" + weeklyPlanId;
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed`).css("display",'none');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'block');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","line-through");
					location.href = url;
				});
				//weeklyPlan-complete
				
				//weeklyPlan-complete_on
				$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed_on`).click(function(event) {
					var url = contextPath + "weplan/weeklyPlan/notCompleteWeeklyPlan.do?id=" + weeklyPlanId;
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed`).css("display",'block');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'none');
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","none");
					location.href = url;
				});
				//weeklyPlan-complete_on
						
				//weeklyPlan View
				$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId} .weekly_title`).click(function(event) {

					//popUp reset
					popupReset();
					
					$('.layerpop .limitDate_container').css('display','none');
					$('.layerpop .startDate_container').css('display','none');

					//popUp_폼 on
					let title = "Weekly Plan";
					let url = contextPath + "weplan/weeklyPlan/updateWeeklyPlan.do";
					
					//프로젝트 정보 가져오기
					//팝업 요소에 프로젝트 정보 삽입
					popUp_getWeeklyPlan(weeklyPlanId);
					
					//팝업 띄우기
					popUpSetting(title, url);
				});
				//weeklyPlan View

				//drop
					$(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId}`).on({
							//드래그 시작 시 요소 id 저장
							'dragstart': function(event){
								  var _thisId = `monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId}`;
//								  monthlyPlan_id = _thisId.split('_')[4];
								  $(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId}`).addClass('is-dragging');
								  event.originalEvent.dataTransfer.setData('text', _thisId);
							},
							//드래그 종료
							'dragend': function(event){
								  $(`#monthlyView_week_weeklyPlan_${month}_${week}_${weeklyPlanId}`).removeClass('is-dragging');
							}
					});
				//drop

			//특성
		}
		
	//weeklyPlan 배치
});