/**
 * 
 */

function popUp_getTask(id){
	
		let data;
		//ajax 호출
		var url = contextPath + "weplan/task/popUpGetTask.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : id
			},
			async: false,
			success : function(result) {
				let id = decodeURIComponent( result.id );
				let title = decodeURIComponent( result.title );
				let content = decodeURIComponent( result.content );
				let importance = decodeURIComponent( result.importance );
				let preLimitDate = decodeURIComponent(result.limitDate).split(' ');
				let preStartDate = decodeURIComponent(result.startDate).split(' ');
				
				let limitDate = preLimitDate[0];
				let limitTime = preLimitDate[1];
				let startDate = preStartDate[0];
				let startTime = preStartDate[1];
				
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

				//startDate
				if(startDate === 'null'){
					$('.layerpop .layerpop_startDate_form').attr('value','');
				} else{
					$('.layerpop .layerpop_startDate_form').attr('value',startDate);
				}
				//startDate

				//limitDate
				if(limitDate === 'null'){
					$('.layerpop .layerpop_limitDate_form').attr('value','');
				} else{
					$('.layerpop .layerpop_limitDate_form').attr('value',limitDate);
				}
				//limitDate

				//startTime
				if(startTime === 'null'){
					$('.layerpop_startDate_container .layerpop_Time_form').attr('value','');
				} else{
					$('.layerpop_startDate_container .layerpop_Time_form').attr('value',startTime);
				}
				//startTime

				//limitTime
				if(limitTime === 'null'){
					$('.layerpop_limitDate_container .layerpop_Time_form').attr('value','');
				} else{
					$('.layerpop_limitDate_container .layerpop_Time_form').attr('value',limitTime);
				}
				//limitTime
			},
		});
		//ajax 호출
}

function getTaskListByMonthAndWeek(firstDay,lastDay){
	
		let data;
		//ajax 호출
		var url = contextPath + "weplan/task/getTaskListByMonthAndWeek.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				firstDay : firstDay,
				lastDay : lastDay
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		
		
		return data;
}

	function getWeeklyPlanListbyMonthAndWeek(month,week) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/weeklyplan/getWeeklyPlanListbyMonthAndWeek.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				month : month,
				week : week
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		
		
		return data;
	}
	
	//popUp yearlyPlan 가져오기
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
	//popUp yearlyPlan 가져오기

$(document).ready(function() {
	let locationSearch = addZero(location.search);
	let searchMonth = locationSearch.split("&")[0].split("=")[1];
	let partYear = searchMonth.split('-')[0];
	let partMonth = searchMonth.split('-')[1];
	let searchWeek =  locationSearch.split("&")[1].split("=")[1];
	console.log(searchWeek);

	$('#weeklyView_search_month').attr('placeholder',todayMonth());
	$('#weeklyView_search_week').attr('placeholder','1');

	//arrow_search
		//left
			$('#weeklyView_header_arrow_left').click(function(){
				if(searchWeek == 1){
					if(partMonth == '01'){
						let changedYear = partYear*1-1;
						let changedMonth = 12;
						let changedFullMonth = changedYear + "-" + changedMonth;
						let weekInfo = getWeekByMonth(changedFullMonth);
						let lastWeek ;
						
						for(i in weekInfo){
							lastWeek = i*1+1;
						}

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=${lastWeek}`;
						location.href= url;
					} else {
						let changedYear = partYear;
						let changedMonth = addZero(partMonth*1-1);
						let changedFullMonth = changedYear + "-" + changedMonth;
						let weekInfo = getWeekByMonth(changedFullMonth);
						let lastWeek ;
						
						for(i in weekInfo){
							lastWeek = i*1+1;
						}

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=${lastWeek}`;
						location.href= url;
					}
				} else {
						let changedYear = partYear;
						let changedMonth = partMonth;
						let changedFullMonth = changedYear + "-" + changedMonth;
						let changedWeek = searchWeek*1-1

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=${changedWeek}`;
						location.href= url;
				}
			})
		//left
		//right
			$('#weeklyView_header_arrow_right').click(function(){
				console.log(searchMonth);
				let weekInfo = getWeekByMonth(searchMonth);
				console.log(weekInfo);
				let lastWeek ;
				for(i in weekInfo){
					lastWeek = i*1+1;
				}
				console.log(lastWeek);
				if(searchWeek == lastWeek){
					if(partMonth == '12'){
						console.log('성공');
						let changedYear = partYear*1+1;
						let changedMonth = '01';
						let changedFullMonth = changedYear + "-" + changedMonth;

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=1`;
						location.href= url;
					} else {
						let changedYear = partYear;
						let changedMonth = addZero(partMonth*1+1);
						let changedFullMonth = changedYear + "-" + changedMonth;

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=1`;
						location.href= url;
					}
				} else {
						let changedYear = partYear;
						let changedMonth = partMonth;
						let changedFullMonth = changedYear + "-" + changedMonth;
						let changedWeek = searchWeek*1+1

						let url = contextPath + `weplan/task/weeklyView.do?month=${changedFullMonth}&week=${changedWeek}`;
						location.href= url;
				}
			})
		//right
	//arrow_search
	
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
		let weeklyPlanList = getWeeklyPlanListbyMonthAndWeek(searchMonth, searchWeek);
		//weeklyPlanList 가져오기

		//weeklyPlanList 배치하기
			for(var i in weeklyPlanList){
				let id = decodeURIComponent( weeklyPlanList[i].id ); 
				let title = decodeURIComponent( weeklyPlanList[i].title );
				let content = decodeURIComponent( weeklyPlanList[i].content );
				let importance = decodeURIComponent( weeklyPlanList[i].importance );
				let isCompleted = decodeURIComponent(weeklyPlanList[i].isCompleted);
				let month = decodeURIComponent(weeklyPlanList[i].month);
				let yearly_plan_id = decodeURIComponent(weeklyPlanList[i].yearly_plan_id);
				let week =  decodeURIComponent(weeklyPlanList[i].week);

				$(`#weeklyView_body_left_body .weeklyView_body_left_padding`).append(
						`<div
							id="weeklyView_weeklyPlan_${id}"
							class="weeklyView_weeklyPlan"
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
									alt="checkbox"
									src="${contextPath }weplan/resources/images/iconmonstr-checkbox-9.svg"
								>
							</div>
							<div
								class="weekly_title_container"
							>
								<div
									class="weekly_title"
								>
									${title}
								</div>
							</div>
							<div
								class="weekly_importance"
							>
							</div>
						</div>`
				)


									if(isCompleted == 1){
										$(`#weeklyView_weeklyPlan_${id} .completed`).css("display",'none');
										$(`#weeklyView_weeklyPlan_${id} .completed_on`).css("display",'block');
										$(`#weeklyView_weeklyPlan_${id} .weekly_title`).css("text-decoration","line-through");
									}
									//weeklyPlan-complete
									$(`#weeklyView_weeklyPlan_${id} .completed`).click(function(event) {
										var url = contextPath + "weplan/weeklyPlan/completeWeeklyPlan.do?id=" + id
										$(`#weeklyView_weeklyPlan_${id} .completed`).css("display",'none');
										$(`#weeklyView_weeklyPlan_${id} .completed_on`).css("display",'block');
										$(`#weeklyView_weeklyPlan_${id} .weekly_title`).css("text-decoration","line-through");
										location.href = url;
									});
									//weeklyPlan-complete
									
									//weeklyPlan-complete_on
									$(`#weeklyView_weeklyPlan_${id} .completed_on`).click(function(event) {
										var url = contextPath + "weplan/weeklyPlan/notCompleteWeeklyPlan.do?id=" + id;
										$(`#weeklyView_weeklyPlan_${id} .completed`).css("display",'block');
										$(`#weeklyView_weeklyPlan_${id} .completed_on`).css("display",'none');
										$(`#weeklyView_weeklyPlan_${id} .weekly_title`).css("text-decoration","none");
										location.href = url;
									});
									//weeklyPlan-complete_on
											
									//weeklyPlan View
									$(`#weeklyView_weeklyPlan_${id} .weekly_title`).click(function(event) {

										//popUp reset
										popupReset();
										
										$('.layerpop .limitDate_container').css('display','none');
										$('.layerpop .startDate_container').css('display','none');

										//popUp_폼 on
										let title = "Weekly Plan";
										let url = contextPath + "weplan/weeklyPlan/updateWeeklyPlan.do";
										
										//프로젝트 정보 가져오기
										//팝업 요소에 프로젝트 정보 삽입
										popUp_getWeeklyPlan(id);
										
										//팝업 띄우기
										popUpSetting(title, url);
									});
									//weeklyPlan View
			}
		//weeklyPlanList 배치하기
	//weeklyPlanList
		
	//day 생성
		let processedYear = searchMonth.split("-")[0];
		let processedMonth = addZero(searchMonth.split("-")[1]*1);
		let processedFullMonth = processedYear + "-" +processedMonth;
		let weekList = getWeekByMonth(processedFullMonth);
		let processedWeek = searchWeek -1;
		let weekInfo = weekList[processedWeek];
		console.log(weekInfo);
		let weekFirstDay = weekInfo.get('weekFirstDay');
		let weekLastDay = weekInfo.get('weekLastDay');
		
		let processedFirstDay = weekFirstDay.split('-')[2]*1;
		
		let weekOfDateArray = ['월','화','수','목','금','토','일']
		
		for(let i=0 ; i<7; i++){
			let thisDay = (processedFirstDay + i);
			let FullthisDay = processedFullMonth + "-" + (processedFirstDay + i);
			
			$(`#weeklyView_body_right .weeklyView_body_right_padding #day_container_border`).append(
					`<div
						id= "weeklyView_day_${FullthisDay}"
						class="day_container"
					>
						<div
							class="header"
						>
							<div
								class="weeklyView_weekOfDate"
							>
								${weekOfDateArray[i]}
							</div>
							<div
								class="weeklyView_day"
							>
								${thisDay} 일
							</div>
						</div>
						<div
							class="content"
						>
						</div>
						<div
							class="day_part_add"
						>
							<img
								alt="add_button" 
								src="${contextPath }/weplan/resources/images/add-black-18dp.svg"
							>
						</div>
					</div>`
			)
			
			
			
			//weeklyPlanAdd

				//hover 효과
				$(`#weeklyView_day_${FullthisDay} .day_part_add`).hover(
						function() {
								$(`#weeklyView_day_${FullthisDay} .day_part_add img`).css('background-color', '#e2e2e2');
						},function(){
								$(`#weeklyView_day_${FullthisDay} .day_part_add img`).css('background-color', '#F7F7F7');
						}
				);
				//hover 효과
				
				//popUp
				$(`#weeklyView_day_${FullthisDay} .day_part_add`).click(function() {
					
					popupReset();	
					let title = "Task 추가";
					let url = contextPath + "weplan/task/addInboxTask.do";
					onTimeForm();
					checkInitialImportance();
					putPlaceholderAtDates();
					putPlaceholderAtTimes();
					
					$('.layerpop_limitDate_container .layerpop_limitDate_form').attr('value',FullthisDay);	
					popUpSetting(title, url);
				});
				//popUp

			//weeklyPlanAdd
			
		}
		
	//day 생성
	
	//taskList 
		//조회
			let taskList = getTaskListByMonthAndWeek(weekFirstDay, weekLastDay);
		//조회
		//배치
			for(let i in taskList){
				let taskId = decodeURIComponent( taskList[i].id ); 
				let title = decodeURIComponent( taskList[i].title );
				let content = decodeURIComponent( taskList[i].content );
				let importance = decodeURIComponent( taskList[i].importance );
				let isCompleted = decodeURIComponent(taskList[i].isCompleted);
				let startDate = decodeURIComponent(taskList[i].startDate);
				let limitDate = decodeURIComponent( taskList[i].limitDate ); 

				$(`#weeklyView_day_${limitDate} .content`).append(

						`<div
							id="weeklyView_day_task_${limitDate}_${taskId}"
							class="task_container"
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
								class="task_title"
							>
								<div
									class="task_text"
								>
									${title}
								</div>
							</div>
							<div
								class="task_importance
							>
							</div>
						</div>`
					
				)
				//특성

				//task-initial
				if(isCompleted == 1){
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed`).css("display",'none');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed_on`).css("display",'block');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .task_title`).css("text-decoration","line-through");
				}
				//task-initial

				//task-complete
				$(`#weeklyView_day_task_${limitDate}_${taskId} .completed`).click(function(event) {
					var url = contextPath + "weplan/task/completeTask.do?id=" + taskId;
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed`).css("display",'none');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed_on`).css("display",'block');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .task_title`).css("text-decoration","line-through");
					location.href = url;
				});
				//task-complete
				
				//task-complete_on
				$(`#weeklyView_day_task_${limitDate}_${taskId} .completed_on`).click(function(event) {
					var url = contextPath + "weplan/task/notCompleteTask.do?id=" + taskId;
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed`).css("display",'block');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .completed_on`).css("display",'none');
					$(`#weeklyView_day_task_${limitDate}_${taskId} .task_title`).css("text-decoration","none");
					location.href = url;
				});
				//task-complete_on
						
				//task View
				$(`#weeklyView_day_task_${limitDate}_${taskId} .task_title`).click(function(event) {

					//popUp reset
					popupReset();
					
					//popUp_폼 on
					let title = "Task";
					let url = contextPath + "weplan/task/updateTask.do";

					onTimeForm();
					putPlaceholderAtDates();
					putPlaceholderAtTimes();
					
					//프로젝트 정보 가져오기
					//팝업 요소에 프로젝트 정보 삽입
					popUp_getTask(taskId);
					
					//팝업 띄우기
					popUpSetting(title, url);
				});
				//task View

				//drop
					$(`#weeklyView_day_task_${limitDate}_${taskId}`).on({
							//드래그 시작 시 요소 id 저장
							'dragstart': function(event){
								  var _thisId = `weeklyView_day_task_${limitDate}_${taskId}`;
//								  monthlyPlan_id = _thisId.split('_')[4];
								  $(`#weeklyView_day_task_${limitDate}_${taskId}`).addClass('is-dragging');
								  event.originalEvent.dataTransfer.setData('text', _thisId);
							},
							//드래그 종료
							'dragend': function(event){
								  $(`#weeklyView_day_task_${limitDate}_${taskId}`).removeClass('is-dragging');
							}
					});
				//drop
			//특성
			}
		//배치
	//taskList 
	
})	
