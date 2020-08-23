/**
 * 
 */

	var contextPath = window.location.protocol + "//" + window.location.host + "/";

	
	
	
	function getMonthlyPlanList(yearlyPlan_id) {

		let data;
		//ajax 호출
		var url = contextPath + "weplan/yearlyPlan/monthlyPlanList.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : yearlyPlan_id
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		return data;
	}
	function getMonthList(yearlyPlan_id) {

		let data;
		//ajax 호출
		var url = contextPath + "weplan/yearlyPlan/getMonthList.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : yearlyPlan_id
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		return data;
	}
	function getMonthlyPlan(monthlyPlanId) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/monthlyPlan/getMonthlyPlan.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : monthlyPlanId
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		return data;
	}

	function getWeeklyPlanList(yearlyPlanId, month) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/weeklyplan/getweeklyPlanListByMonth.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : yearlyPlanId,
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
	
	
	
	//프로젝트 정보 가져오기
	function getProject(goal_id) {
		var url = contextPath + "weplan/goal/popUpGoalView.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : goal_id
			},
			success : function(result) {
				var id = decodeURIComponent( result.id );
				var title = decodeURIComponent( result.title );
				var content = decodeURIComponent( result.content );
				var importance = decodeURIComponent( result.importance );
				var limitDate = decodeURIComponent(result.limitDate);
				var startDate = decodeURIComponent(result.startDate);
				var absolute_value_id = decodeURIComponent(result.absolute_value_id);

				$('.project_detail_title .project_detail_text').html(title);
				$('.project_detail_title .project_detail_text').attr('id',`project_${id}`)
				$('.project_detail_title .project_detail_class').html('project');
				$('.project_detail_header .project_detail_completed').css('display','block')


				//startDate
				if(startDate === 'null'){
					$('.project_detail_header .project_detail_startDate').html('');
				} else{
					$('.project_detail_header .project_detail_startDate').html(startDate);
				}
				//startDate

				//limitDate
				if(limitDate === 'null'){
					$('.project_detail_header .project_detail_limitDate').html('');
				} else{
					$('.project_detail_header .project_detail_limitDate').html(limitDate);
				}
				//limitDate

				/* IMPORTANCE */
				if(importance=='1'){
					$('.project_detail_header .project_detail_importance').css("background-color", 'red');
				} else if (importance == '2'){
					$('.project_detail_header .project_detail_importance').css("background-color", 'yellow');
				} else if (importance == '3'){
					$('.project_detail_header .project_detail_importance').css("background-color", 'white');
				}
				/* IMPORTANCE */
				
				
				//할일 완료
				$('.project_detail_header .project_detail_completed').attr('id',`project_detail_${id}`);
				$('.project_detail_header .project_detail_completed').off('click');

				$(`.project_detail_header #project_detail_${id}`).click(function(event) {
					var url = contextPath + "weplan/goal/completeGoal.do?id=" + id;
					var target = event.target;
					var check_url = contextPath + "weplan/resources/images/iconmonstr-checkbox-9.svg" 
					$(target).attr("src",check_url);
					location.href = url;
				})
				//할일 완료

				//add yearlyPlan
				$('.project_detail .project_detail_add').hover(
						function() {
							$('.project_detail_add img').css('background-color','#e2e2e2');
						},
						function() {
							$('.project_detail_add img').css('background-color','white');
						}
				);
				$('.project_detail .project_detail_add').click(function(event) {
					popupReset();	
					let title = "Goal 추가";
					let url = contextPath + "weplan/yearlyPlan/addYearlyPlan.do";
					displayPopUpFormProjectId();
					getProjectAllList();

					//goalId
					if(goal_id === 'null'){
					} else{
						console.log(goal_id);
						$('#layerpop_projectId_select').val(goal_id);
					}
					//goalId
					
					checkInitialImportance();
					putPlaceholderAtDates();
					popUpSetting(title, url);
				});
				//add yearlyPlan
			},

		})
		
	}
	//프로젝트 정보 가져오기
	
	//yearlyPlan 가져오기
	function getYearlyPlanList(project_id) {
		var url = contextPath + "weplan/goal/yearlyPlanList.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "GET",
			data : {
				id : project_id
			},
			success : function(result) {
				
				//초기화
				$('.project_detail .project_detail_body').html('');
				//초기화
				
				for(var i in result){
					let id = decodeURIComponent( result[i].id ); 
					var title = decodeURIComponent( result[i].title );
					var content = decodeURIComponent( result[i].content );
					var importance = decodeURIComponent( result[i].importance );
					var limitDate = decodeURIComponent(result[i].limitDate);
					var startDate = decodeURIComponent(result[i].startDate);

					//startDate
					if(startDate === 'null'){
						startDate = '';
						$('.project_detail_header .project_detail_startDate').html('');
					} 
					//startDate

					//limitDate
					if(limitDate === 'null'){
						limitDate = '';
					} 
					//limitDate


					$('.project_detail .project_detail_body').append(
						'<div'+
							' class="project_yearly_container"' +
								` id=project_yearly_container_${id}` +
						' >' +
							'<div'
								+ ' class="project_yearly"'
								+ ` id=project_yearly_${id}`
							+ ' >'
								+ ' <div'
									+ ' class="project_yearly_viewer"'
								+ '>'
									+ ' <img alt="viewer"' 
										+ ` src="${contextPath }weplan/resources/images/chevron_right-black-18dp.svg"`
									+ '>' 
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_viewer_on"'
								+ '>'
									+ ' <img alt="viewer"' 
										+ ` src="${contextPath }weplan/resources/images/expand_more-black-18dp.svg"`
									+ '>' 
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_completed"'
								+ '>'
									+ ' <img alt="checkbox"' 
										+ ` src="${contextPath }weplan/resources/images/iconmonstr-checkbox-11.svg"`
									+ '>'
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_title"'
								+ ' >'
									+ ' <div'
										+ ' class="project_yearly_text"'
									+ ' >'
										+ title 
									+ ' </div>'
									+ ' <div'
										+ ' class="project_yearly_class"'
									+ ' >'
										+ 'goal'
									+ ' </div>'
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_startDate"'
								+ ' >'
									+ startDate
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_limitDate"'
								+ ' >'
									+ limitDate
								+ ' </div>'
								+ ' <div'
									+ ' class="project_yearly_importance"'
								+ ' >'
								+ ' </div>'
							+ ' </div>' +
							' <div' +
								' class="project_monthly"' +
								` id=project_monthly_${id}` +
							' >' +
							' </div>' +
							`<div
								class="monthly_ent_add"
							>
								
								<img
									alt="add_button" 
									src="${contextPath }/weplan/resources/images/add-black-18dp.svg"
								>
							</div>` +
						' </div>'
	
					);
					//popUp-add
					
						//hover 효과
						$(`#project_yearly_container_${id} .monthly_ent_add`).hover(function() {
							$(`#project_yearly_container_${id} .monthly_ent_add img`).css('background-color', '#e2e2e2');
							$(`#project_yearly_container_${id} .monthly_ent_add img`).css('border-radius', '5px');
						},function(){
							$(`#project_yearly_container_${id} .monthly_ent_add img`).css('background-color', '#F7F7F7');
						});
						//hover 효과
					
					$(`#project_yearly_container_${id} .monthly_ent_add`).click(function(event) {
						popUpMOnthlyGoalAdd(id);
					});
					//popUp-add
					
					/* IMPORTANCE */
					if(importance == '1'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'red');
					} else if (importance == '2'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'yellow');
					} else if (importance == '3'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'white');
					}
					/* IMPORTANCE */

					//yearlyPlan-detail_view
					$(`#project_yearly_${id} .project_yearly_title`).click(function(event) {


						//popUp_폼 on
						let title = "Goal";
						let url = contextPath + "weplan/yearlyPlan/updateYearlyPlan.do";

						//popUp reset
						popupReset();

						displayPopUpFormProjectId();

						getProjectAllList();
						
						//프로젝트 정보 가져오기
						//팝업 요소에 프로젝트 정보 삽입
						popUp_getYearlyPlan(id);

						
						//팝업 띄우기
						popUpSetting(title, url);
					});
					//yearlyPlan-detail_view
					
					//yearlyPlan-complete
					$(`#project_yearly_${id} .project_yearly_completed`).click(function(event) {
						var url = contextPath + "weplan/yearlyPlan/completeYearlyPlan.do?id=" + id;
						var target = event.target;
						var check_url = contextPath + "weplan/resources/images/iconmonstr-checkbox-9.svg" 
						$(target).attr("src",check_url);
						location.href = url;
					});
					//yearlyPlan-complete

					//monthlyPlanList
					$(`#project_yearly_${id} .project_yearly_viewer`).click(function(event) {
						
						let url = contextPath + "weplan/yearlyPlan/monthlyPlanList.do?id=" + id;
						//ajax 호출하고 map List 으로 리턴 
						let monthlyPlanList = getMonthlyPlanList(id);
						console.log(monthlyPlanList);
						//ajax 호출하고 map List 으로 리턴 
						
						$(this).css('display','none');
						$(`#project_yearly_${id} .project_yearly_viewer_on`).css('display','block');
						$(`#project_yearly_container_${id} .monthly_ent_add`).css('display','block');
						$(`#project_yearly_container_${id}`).css('border-bottom','1px solid #DCDCDC');
						
						//month리스트 생성
						let monthList = getMonthList(id);
						
						for(let i in monthList){
							let month = decodeURIComponent(monthList[i]);
							console.log(month);
							
								$(`#project_monthly_${id}`).append(
										'<div' +
											` id="project_monthly_${id}_${month}"` +
											` class="project_yearlyPlan_month"` +
										' >' +
											
											' <div' +
												' class="project_yearlyPlan_month_content"' +
											'>' +
												' <div' +
													' class="project_monthly_viewer"' +
												'>' +
													' <img alt="viewer"'+ 
														` src="${contextPath }weplan/resources/images/chevron_right-black-18dp.svg"` +
													'>' + 
												' </div>' +
												' <div' +
													' class="project_monthly_viewer_on"' +
												'>' +
													' <img alt="viewer"' + 
														` src="${contextPath }weplan/resources/images/expand_more-black-18dp.svg"`+
													'>' + 
												' </div>' +
												' <div' +
													' class="monthly_month"' +
												' >' +
													month +
												' </div>' +
											' </div>' +
											' <div' +
												' class="monthly_month_content"' +
											' >' +
											' </div>' +
											`<div
												class="monthly_part_add"
											>
												<img
													alt="add_button" 
													src="${contextPath }/weplan/resources/images/add-black-18dp.svg"
												>
											</div>`+
										' </div>'
											
										
								);
								
								
							//weeklyPlan view
							$(`#project_monthly_${id}_${month} .project_monthly_viewer`).click(function() {
								$(this).css('display','none');
								$(`#project_monthly_${id}_${month} .project_monthly_viewer_on`).css('display','block')
								let weeklyPlanList = getWeeklyPlanList(id, month);
								
								$(`#project_monthly_${id}_${month} .monthly_part_add`).css('border-bottom','1px solid #DCDCDC')
								
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
									
									
									if(!($(`#project_monthly_${id}_${month} .monthly_part_week`).length)){
										$(`#project_monthly_${id}_${month}`).append(
												`<div
													class="monthly_part_week"
												>
												</div>`
										);
									}
									if(!$(`#project_monthly_${id}_${month} #project_monthly_${id}_${month}_${week}`).lengh){
										$(`#project_monthly_${id}_${month} .monthly_part_week`).append(
												`<div
													id= "project_monthly_${id}_${month}_${week}"
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
														<div
															class="weeklyPlan_img"
														>
															<img
																src="${contextPath }weplan/resources/images/input-black-18dp.svg"
															>
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
									}
									
									//WeeklyPlan-add
									$(`#project_monthly_${id}_${month}_${week} .weekly_part_add`).hover(
											function() {
												$(`#project_monthly_${id}_${month}_${week} .weekly_part_add img`).css('background-color', '#e2e2e2');
												$(`#project_monthly_${id}_${month}_${week} .weekly_part_add img`).css('border-radius', '5px');
											},
											function() {
												$(`#project_monthly_${id}_${month}_${week} .weekly_part_add img`).css('background-color', '#F7F7F7');
											}
									)
									$(`#project_monthly_${id}_${month}_${week} .weekly_part_add`).click(function() {
										popUpWeeklyGoalAdd(id, month, week);
									})
									//WeeklyPlan-add
									
								}
								//week-container
								
								//weeklyPlan
								for(let i in weeklyPlanList){
									let weeklyPlanId = decodeURIComponent( weeklyPlanList[i].id ); 
									let title = decodeURIComponent( weeklyPlanList[i].title );
									let content = decodeURIComponent( weeklyPlanList[i].content );
									let importance = decodeURIComponent( weeklyPlanList[i].importance );
									let month = decodeURIComponent(weeklyPlanList[i].month);
									let isCompleted = decodeURIComponent(weeklyPlanList[i].isCompleted);
									let week = decodeURIComponent(weeklyPlanList[i].week);
									
									$(`#project_monthly_${id}_${month}_${week} .content`).append(
											`<div
												id="project_monthly_${id}_${month}_${week}_${weeklyPlanId}"
												class="weeklyPlan_container"
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
													<div
														class="weekly_class"
													>
														weekly goal
													</div>
												</div>
												<div
													class="weekly_importance
												>
												</div>
											</div>`
									);

									if(isCompleted == 1){
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed`).css("display",'none');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'block');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","line-through");
									}
									//weeklyPlan-complete
									$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed`).click(function(event) {
										var url = contextPath + "weplan/weeklyPlan/completeWeeklyPlan.do?id=" + weeklyPlanId;
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed`).css("display",'none');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'block');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","line-through");
										location.href = url;
									});
									//weeklyPlan-complete
									
									//weeklyPlan-complete_on
									$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed_on`).click(function(event) {
										var url = contextPath + "weplan/weeklyPlan/notCompleteWeeklyPlan.do?id=" + weeklyPlanId;
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed`).css("display",'block');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .completed_on`).css("display",'none');
										$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .weekly_text`).css("text-decoration","none");
										location.href = url;
									});
									//weeklyPlan-complete_on
											
									//weeklyPlan View
									$(`#project_monthly_${id}_${month}_${week}_${weeklyPlanId} .weekly_title`).click(function(event) {
										popUpWeeklyGoalView(id,weeklyPlanId)
									});
									//weeklyPlan View

								}
								//weeklyPlan
								
							});
							$(`#project_monthly_${id}_${month} .project_monthly_viewer_on`).click(function() {
								$(this).css('display','none');
								$(`#project_monthly_${id}_${month} .project_monthly_viewer`).css('display','block')
								$(`#project_monthly_${id}_${month} .monthly_part_week`).text('');
								$(`#project_monthly_${id}_${month} .monthly_part_add`).css('border-bottom','none')
							});
							//weeklyPlan view
						}
						//month리스트 생성
						
						for(let i in monthlyPlanList){
							
							let monthlyPlanId = decodeURIComponent( monthlyPlanList[i].id ); 
							var title = decodeURIComponent( monthlyPlanList[i].title );
							var content = decodeURIComponent( monthlyPlanList[i].content );
							var importance = decodeURIComponent( monthlyPlanList[i].importance );
							let month = decodeURIComponent(monthlyPlanList[i].month);
							let isCompleted = decodeURIComponent(monthlyPlanList[i].isCompleted);
							
							//content
							$(`#project_monthly_${id}_${month} .monthly_month_content`).append(
										'<div' +
											` id="project_${id}_${month}_${monthlyPlanId}"` +
											` class="project_yearlyPlan_month_monthlyPlan"` +
										' >' +
											' <div' +
												' class="project_monthly_completed"' +
											'>' +
												' <img alt="checkbox"' + 
													` src="${contextPath }weplan/resources/images/iconmonstr-checkbox-11.svg"`+
												'>' +
											' </div>' +
											' <div' +
												' class="project_monthly_completed_on"' +
											'>' +
												' <img alt="checkbox"' + 
													` src="${contextPath }weplan/resources/images/iconmonstr-checkbox-9.svg"`+
												'>' +
											' </div>' +

											' <div' +
												' class="monthly_title_container"' +
											' >' +
												' <div' +
													' class="monthly_title"' +
												' >' +
													title +
												' </div>' +
												' <div' +
													' class="monthly_class"' +
												' >' +
													"monthly goal" +
												' </div>' +
											' </div>' +
											' <div' +
												' class="project_monthly_importance"' +
											' >' +
											' </div>'+
										' </div>'
							);
							//content
							
							
							
							
							if(isCompleted == 1){
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed`).css("display",'none');
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed_on`).css("display",'block');
								$(`#project_${id}_${month}_${monthlyPlanId} .monthly_title`).css("text-decoration","line-through");
							}
							
							/* IMPORTANCE */
							if(importance=='1'){
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_importance`).css("background-color", 'red');
							} else if (importance == '2'){
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_importance`).css("background-color", 'yellow');
							} else if (importance == '3'){
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_importance`).css("background-color", 'white');
							}
							/* IMPORTANCE */


							//hover 효과
							$(`#project_monthly_${id}_${month} .monthly_part_add`).hover(
									function() {
											$(`#project_monthly_${id}_${month} .monthly_part_add img`).css('background-color', '#e2e2e2');
									},function(){
											$(`#project_monthly_${id}_${month} .monthly_part_add img`).css('background-color', '#F7F7F7');
									}
							);
							//hover 효과
							
							$(`#project_monthly_${id}_${month} .monthly_part_add`).click(function() {
								popUpMonthlyGoalAdd(id, month);
							});
							
							//monthlyPlan-complete
							$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed`).click(function(event) {
								var url = contextPath + "weplan/monthlyPlan/completeMonthlyPlan.do?id=" + monthlyPlanId;
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed`).css("display",'none');
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed_on`).css("display",'block');
								$(`#project_${id}_${month}_${monthlyPlanId} .monthly_title`).css("text-decoration","line-through");
								location.href = url;
							});
							//monthlyPlan-complete
							
							//monthlyPlan-complete_on
							$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed_on`).click(function(event) {
								var url = contextPath + "weplan/monthlyPlan/notCompleteMonthlyPlan.do?id=" + monthlyPlanId;
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed`).css("display",'block');
								$(`#project_${id}_${month}_${monthlyPlanId} .project_monthly_completed_on`).css("display",'none');
								$(`#project_${id}_${month}_${monthlyPlanId} .monthly_title`).css("text-decoration","none");
								location.href = url;
							});
							//monthlyPlan-complete_on
							
							$(`#project_${id}_${month}_${monthlyPlanId} .monthly_title_container`).click(function() {
								popUpMonthlyGoalView(monthlyPlanId);
							});

						}

					});
					$(`#project_yearly_${id} .project_yearly_viewer_on`).click(function() {
						$(this).css('display','none');
						$(`#project_yearly_${id} .project_yearly_viewer`).css('display','block');
						$(`#project_monthly_${id}`).html('');
						$(`#project_yearly_container_${id} .monthly_ent_add`).css('display','none');
						$(`#project_yearly_container_${id}`).css('border-bottom','0px solid #DCDCDC');
					});
					//monthlyPlanList
					

				}
				

			},
			error: function (){
				location.href=`${contextPath}weplan/main`;
			}

		})
		
	}
	//yearlyPlan 가져오기

	//팝업 프로젝트 정보 가져오기
	function popUp_getProject(goal_id) {
		var url = contextPath + "weplan/goal/popUpGoalView.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : goal_id
			},
			success : function(result) {
				var id = decodeURIComponent( result.id );
				var title = decodeURIComponent( result.title );
				var content = decodeURIComponent( result.content );
				var importance = decodeURIComponent( result.importance );
				var limitDate = decodeURIComponent(result.limitDate);
				var startDate = decodeURIComponent(result.startDate);
				var absolute_value_id = decodeURIComponent(result.absolute_value_id);

				//absoluteValueId
				if(absolute_value_id === 'null'){
				} else{
					console.log(absolute_value_id);
					$('#layerpop_absoluteValueId_select').val(absolute_value_id);
				}
				//absoluteValueId
				
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
			},

		})
		
	}
	//팝업 프로젝트 정보 가져오기
	
	//popUp yearlyPlan 가져오기
	function popUp_getYearlyPlan(id) {
		var url = contextPath + "weplan/yearlyPlan/popUpYearlyPlanView.do";
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
				let limitDate = decodeURIComponent(result.limitDate);
				let startDate = decodeURIComponent(result.startDate);
				var goal_id = decodeURIComponent(result.goal_id);

				//absoluteValueId
				if(goal_id === 'null'){
				} else{
					console.log(goal_id);
					$('#layerpop_projectId_select').val(goal_id);
				}
				//absoluteValueId
				
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

				//startDate
				if(startDate === 'null'){
					$('.layerpop .layerpop_startDate_form').attr('value', '');
				} else{
					$('.layerpop .layerpop_startDate_form').attr('value', startDate);
				}
				//startDate

				//limitDate
				if(limitDate === 'null'){
					$('.layerpop .layerpop_limitDate_form').attr('value', '');
				} else{
					$('.layerpop .layerpop_limitDate_form').attr('value', limitDate);
				}
				//limitDate

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
			},

		})
		
	}
	//popUp yearlyPlan 가져오기
	
	
	//detail-view
	$('.project_List-text').click(function(event) {
		var project_id = event.target.id;
		$('.project_body .project_right').css('display','block');
		getProject(project_id)
		getYearlyPlanList(project_id)
	})
	//detail-view



	//popUp-update
	$('.project_detail_header .project_detail_title').click(function(event) {
		let id = event.target.id;
		let id_arr = id.split('_');
		let processed_id = id_arr[1];

		popupReset();	

		displayPopUpFormAbsoluteValueId();
		getAbsoluteValueList();

		let title = "Project";
		let url = contextPath + "weplan/goal/updateGoal.do";

		//프로젝트 정보 가져오기
		//팝업 요소에 프로젝트 정보 삽입
		popUp_getProject(processed_id);

		popUpSetting(title, url);
	});
	//popUp-update
	
	//popUp-add
	
		//hover 효과
		$('.project_add .project_button').hover(function() {
			$('.project_List .project_add img').css('background-color', '#e2e2e2');
			$('.project_List .project_add img').css('border-radius', '5px');
		},function(){
			$('.project_List .project_add img').css('background-color', '#F7F7F7');
		});
		//hover 효과
	
	$('.project_add .project_button').click(function(event) {

		popupReset();	
		displayPopUpFormAbsoluteValueId();
		getAbsoluteValueList();
		let title = "Project 추가";
		let url = contextPath + "weplan/goal/addGoal.do";
		checkInitialImportance();
		putPlaceholderAtDates();
		popUpSetting(title, url);
	});
	//popUp-add
	
	