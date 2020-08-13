
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
	
	let year = $.trim($('#yearlyView_header_title').text());
	
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
						class="yearlyView_month"
					>
						<div
							class="header"
						>
							${month} 월
						</div>
						<div
							class="body"
						>
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
					
					popupReset();	
					let title = "Monthly Plan 추가";
					let url = contextPath + "weplan/monthlyplan/addMonthlyPlan.do";
					$('.layerpop .startDate_container').css('display','none');
					$('.layerpop .limitDate_container').css('display','none');
					$('.layerpop .month_container').css('display','block');
					$('.month_container .layerpop_month_form').attr('value',`${year}-${month}`);
					checkInitialImportance();
					putPlaceholderAtMonth();
					popUpSetting(title, url);
				});
				
			//MonthlyPlan-add

		}
	//month 생성
	
	//monthlyPlan 생성
		//List 받아오기
		let monthlyPlanList = getMonthlyPlanList(2020);
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
			
			$(`#yearlyView_month_${month} .body`).append(
					`<div
						id="yearlyView_month_monthlyPlan_${month}_${id}"
						ondragstart="dragStart(event);" 
						draggable="true" 
						ondragend="dragEnd(event)"
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
							<div
								class="monthly_class"
							>
								monthly plan
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
		//위치시키기
	//monthlyPlan 생성
	
}); 

