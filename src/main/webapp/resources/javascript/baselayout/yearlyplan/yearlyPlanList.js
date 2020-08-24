
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

function getMonthlyPlanList(year) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/monthlyPlan/getMonthlyPlanListByYear.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				year : year
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
	
	let year = location.search.split("=")[1]; 

	$('#yearlyView_search_year').attr('placeholder',todayYear());
	
	//arrow_search
		//left
			$('#yearlyView_header_arrow_left').click(function(){
				let changedYear = year*1-1
				let url = contextPath + `weplan/monthlyPlan/yearlyView.do?year=${changedYear}`;
				location.href= url;
			})
		//left
		//right
			$('#yearlyView_header_arrow_right').click(function(){
				let changedYear = year*1+1
				let url = contextPath + `weplan/monthlyPlan/yearlyView.do?year=${changedYear}`;
				location.href= url;
			})
		//right
	//arrow_search
		
	//month 생성
		for(let i=0; i<12; i++){
			let month = "";
			if(i+1 < 10){
				month = "0" + (i+1);
			} else {
				month = String(i+1);
			}
			$(`#yearlyView_month_container`).append(
					`<div
						id="yearlyView_month_${year}-${month}"
						class="yearlyView_month bucket"
					>
						<div
							class="header"
						>
							<div
								class="header_text"
							>
								${month} 월
							</div>
						</div>
						<div
							class="body"
						>
							<div
								class="body_padding"
							>
							</div>
						</div>
						<div
							class="monthly_part_add"
						>
							<img
								alt="add_button" 
								src="${contextPath }/weplan/resources/images/add-black-18dp.svg"
							>
						</div>
					</div>`
			);
			
			
			//MonthlyPlan-add
				//hover 효과
				$(`#yearlyView_month_${year}-${month} .monthly_part_add`).hover(
						function() {
								$(`#yearlyView_month_${year}-${month} .monthly_part_add img`).css('background-color', '#e2e2e2');
						},function(){
								$(`#yearlyView_month_${year}-${month} .monthly_part_add img`).css('background-color', '#F7F7F7');
						}
				);
				//hover 효과
				
				$(`#yearlyView_month_${year}-${month} .monthly_part_add`).click(function() {
					let processedFullMonth = year + "-" + month;
					console.log(processedFullMonth);
					popUpMonthlyGoalAddByMonth(processedFullMonth);
				});
				
			//MonthlyPlan-add
				
//			var monthlyPlan_id = "";
//			var month = "";
			//drop
				$(`#yearlyView_month_${year}-${month}`).on({

						'dragenter': function(event){
							$(`#yearlyView_month_${year}-${month}`).addClass('is-selecting');
						},
						'dragleave': function(event){
							$(`#yearlyView_month_${year}-${month}`).removeClass('is-selecting');
						},
						//브라우저 표중 동작 취소
						'dragover': function(event){
							event.preventDefault();
						},
						'drop': function(event){
							
							var dragId = event.originalEvent.dataTransfer.getData('text');
							let _monthlyPlan_id = dragId.split('_')[4];
							let fullMonth = year + "-" + month;

							var moveEle = $(`#${dragId}`).get(0);
							$(`#yearlyView_month_${year}-${month}`).removeClass('is-selecting');
							$(`#yearlyView_month_${year}-${month} .body_padding`).append(moveEle);
							var member_id = $.trim($('#member_id').text());

							console.log(member_id);

							$.ajax({
								 url: `${contextPath}/weplan/monthlyPlan/moveMonth.do`,	
								 data: { 
									 id: _monthlyPlan_id,
									 month: fullMonth,
									 member_id: member_id
								},
								 method: "POST", 
								 dataType: "json"
							})
						}
				});
			//drop

		}
	//month 생성
	
	//monthlyPlan 생성
		//List 받아오기
		let monthlyPlanList = getMonthlyPlanList(year);
		//List 받아오기

		//위치시키기
		for(var i in monthlyPlanList){
			let id = decodeURIComponent( monthlyPlanList[i].id ); 
			let title = decodeURIComponent( monthlyPlanList[i].title );
			let content = decodeURIComponent( monthlyPlanList[i].content );
			let importance = decodeURIComponent( monthlyPlanList[i].importance );
			let isCompleted = decodeURIComponent(monthlyPlanList[i].isCompleted);
			let month = decodeURIComponent(monthlyPlanList[i].month);
			let yearly_plan_id = decodeURIComponent(monthlyPlanList[i].yearly_plan_id);
			
			$(`#yearlyView_month_${month} .body_padding`).append(
					`<div
						id="yearlyView_month_monthlyPlan_${month}_${id}"
						draggable="true" 
						class="yearlyView_month_monthlyPlan"
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
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed`).css("display",'none');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed_on`).css("display",'block');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_title`).css("text-decoration","line-through");
					}
					//초기값
					//monthlyPlan-complete
					$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed`).click(function(event) {
						var url = contextPath + "weplan/monthlyPlan/completeMonthlyPlan.do?id=" + id;
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed`).css("display",'none');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed_on`).css("display",'block');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_title`).css("text-decoration","line-through");
						location.href = url;
					});
					//monthlyPlan-complete
					//monthlyPlan-complete_on
					$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed_on`).click(function(event) {
						var url = contextPath + "weplan/monthlyPlan/notCompleteMonthlyPlan.do?id=" + id;
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed`).css("display",'block');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_completed_on`).css("display",'none');
						$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_title`).css("text-decoration","none");
						location.href = url;
					});
					//monthlyPlan-complete_on
				//complete
				
				/* IMPORTANCE */
				if(importance=='1'){
					$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_importance`).css("background-color", 'red');
				} else if (importance == '2'){
					$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_importance`).css("background-color", 'yellow');
				} else if (importance == '3'){
					$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_importance`).css("background-color", 'white');
				}
				/* IMPORTANCE */
				
				
				//view
				$(`#yearlyView_month_monthlyPlan_${month}_${id} .monthly_title_container`).click(function() {
					popUpMonthlyGoalView(id);
				});
				//view

				//drop
					$(`#yearlyView_month_monthlyPlan_${month}_${id}`).on({
							//드래그 시작 시 요소 id 저장
							'dragstart': function(event){
								  var _thisId = `yearlyView_month_monthlyPlan_${month}_${id}`;
//								  monthlyPlan_id = _thisId.split('_')[4];
								  $(`#yearlyView_month_monthlyPlan_${month}_${id}`).addClass('is-dragging');
								  event.originalEvent.dataTransfer.setData('text', _thisId);
							},
							//드래그 종료
							'dragend': function(event){
								  $(`#yearlyView_month_monthlyPlan_${month}_${id}`).removeClass('is-dragging');
							}
					});
				//drop

			//특성
		}
		//위치시키기
	//monthlyPlan 생성
	
}); 

