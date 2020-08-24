/**
 * 
 */
function popUpTaskAddWithoutDay(){
	
		popupReset();	
		let title = "Task 추가";
		let url = contextPath + "weplan/task/addInboxTask.do";
		onTimeForm();
		checkInitialImportance();
		putPlaceholderAtDates();
		putPlaceholderAtTimes();

		displayPopUpFormGoalId()

		//goalList 가져오기
			getGoalAllList();
		//goalList 가져오기

		popUpSetting(title, url);
}
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
				let yearly_plan_id = decodeURIComponent(result.yearly_plan_id);
				
				let limitDate = preLimitDate[0];
				let limitTime = preLimitDate[1];
				let startDate = preStartDate[0];
				let startTime = preStartDate[1];

				console.log(yearly_plan_id);
				//goalId
				if(yearly_plan_id === 'null'){
				} else{
					$('#layerpop_goalId_select').val(yearly_plan_id);
				}
				//goalId
				
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
function popUpTaskView(taskId){
	
	//popUp reset
	popupReset();
	
	//popUp_폼 on
	let title = "Task";
	let url = contextPath + "weplan/task/updateTask.do";

	onTimeForm();
	putPlaceholderAtDates();
	putPlaceholderAtTimes();

	displayPopUpFormGoalId()

	//goalList 가져오기
		getGoalAllList();
	//goalList 가져오기
	
	//프로젝트 정보 가져오기
	//팝업 요소에 프로젝트 정보 삽입
	popUp_getTask(taskId);



	//팝업 띄우기
	popUpSetting(title, url);
}

function popUpTaskAdd(day){
	
		popupReset();	
		let title = "Task 추가";
		let url = contextPath + "weplan/task/addInboxTask.do";
		onTimeForm();
		checkInitialImportance();
		putPlaceholderAtDates();
		putPlaceholderAtTimes();

		displayPopUpFormGoalId()

		//goalList 가져오기
			getGoalAllList();
		//goalList 가져오기

		$('.layerpop_limitDate_container .layerpop_limitDate_form').attr('value',day);	
		popUpSetting(title, url);
}


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
				
				//goalId
				if(yearly_plan_id === 'null'){
				} else{
					$('#layerpop_goalId_select').val(yearly_plan_id);
				}
				//goalId

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

function popUpWeeklyGoalViewByWeeklyGoalId(weeklyGoalId){
	
			//popUp reset
			popupReset();
			
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .startDate_container').css('display','none');

			//popUp_폼 on
			let title = "Weekly Plan";
			let url = contextPath + "weplan/weeklyPlan/updateWeeklyPlan.do";
			

			displayPopUpFormGoalId()

			//goalList 가져오기
				getGoalAllList();
			//goalList 가져오기

			//프로젝트 정보 가져오기
			//팝업 요소에 프로젝트 정보 삽입
			popUp_getWeeklyPlan(weeklyGoalId);

			//팝업 띄우기
			popUpSetting(title, url);
}

function popUpWeeklyGoalView(goalId, weeklyGoalId){
	
			//popUp reset
			popupReset();
			
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .startDate_container').css('display','none');

			//popUp_폼 on
			let title = "Weekly Plan";
			let url = contextPath + "weplan/weeklyPlan/updateWeeklyPlan.do";
			
			//프로젝트 정보 가져오기
			//팝업 요소에 프로젝트 정보 삽입
			popUp_getWeeklyPlan(weeklyGoalId);

			displayPopUpFormGoalId()

			//goalList 가져오기
				getGoalAllList();
			//goalList 가져오기

			//goalId
			if(goalId === 'null'){
			} else{
				$('#layerpop_goalId_select').val(goalId);
			}
			//goalId
			
			//팝업 띄우기
			popUpSetting(title, url);
}

function popUpWeeklyGoalAddByMonthAndWeek(month, week){
	
	console.log(week);
	console.log(month);
	popupReset();	
	let title = "Weekly Goal 추가";
	let url = contextPath + "weplan/weeklyplan/addWeeklyPlan.do";
	$('.layerpop .startDate_container').css('display','none');
	$('.layerpop .limitDate_container').css('display','none');
	$('.month_container .layerpop_month_form').attr('value',month);

	$('#layerpop_week').attr('value',week);

	displayPopUpFormGoalId()

	//goalList 가져오기
		getGoalAllList();
	//goalList 가져오기

	checkInitialImportance();
	popUpSetting(title, url);
}

function popUpWeeklyGoalAdd(goalId, month, week){
	
	popupReset();	
	let title = "Weekly Goal 추가";
	let url = contextPath + "weplan/weeklyplan/addWeeklyPlan.do";
	$('.layerpop .startDate_container').css('display','none');
	$('.layerpop .limitDate_container').css('display','none');
	$('.month_container .layerpop_month_form').attr('value',month);
	$('#layerpop_week').attr('value',week);
	displayPopUpFormGoalId()

	//goalList 가져오기
		getGoalAllList();
	//goalList 가져오기

	//goalId
	if(goalId === 'null'){
	} else{
		$('#layerpop_goalId_select').val(goalId);
	}
	//goalId
	checkInitialImportance();
	popUpSetting(title, url);
}

function popUpMonthlyGoalAddByMonth(month){
			popupReset();	
			let title = "Monthly Goal 추가";
			let url = contextPath + "weplan/monthlyplan/addMonthlyPlan.do";
			$('.layerpop .startDate_container').css('display','none');
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .month_container').css('display','block');
			$('.month_container .layerpop_month_form').attr('value',month);

			displayPopUpFormGoalId()

			//goalList 가져오기
				getGoalAllList();
			//goalList 가져오기

			checkInitialImportance();
			putPlaceholderAtMonth();
			popUpSetting(title, url);
}
function popUpMOnthlyGoalAdd(goalId){
	popupReset();	
	let title = "Monthly Goal 추가";
	let url = contextPath + "weplan/monthlyplan/addMonthlyPlan.do";
	$('.layerpop .startDate_container').css('display','none');
	$('.layerpop .limitDate_container').css('display','none');
	$('.layerpop .month_container').css('display','block');
	displayPopUpFormGoalId()

	//goalList 가져오기
		getGoalAllList();
	//goalList 가져오기

	//goalId
	if(goalId === 'null'){
	} else{
		$('#layerpop_goalId_select').val(goalId);
	}
	//goalId

	checkInitialImportance();
	putPlaceholderAtMonth();
	popUpSetting(title, url);
}

function popUpMonthlyGoalAdd(goalId, month){
			popupReset();	
			let title = "Monthly Goal 추가";
			let url = contextPath + "weplan/monthlyplan/addMonthlyPlan.do";
			$('.layerpop .startDate_container').css('display','none');
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .month_container').css('display','block');
			$('.month_container .layerpop_month_form').attr('value',month);

			displayPopUpFormGoalId()

			//goalList 가져오기
				getGoalAllList();
			//goalList 가져오기

			//goalId
			if(goalId === 'null'){
			} else{
				$('#layerpop_goalId_select').val(goalId);
			}
			//goalId

			checkInitialImportance();
			putPlaceholderAtMonth();
			popUpSetting(title, url);
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

function popUpMonthlyGoalView(id){
	
			popupReset();	
			let form_title = "Monthly Goal";
			let url = contextPath + "weplan/monthlyplan/updateMonthlyPlan.do";
			let monthlyPlan = getMonthlyPlan(id);
			$('.layerpop .startDate_container').css('display','none');
			$('.layerpop .limitDate_container').css('display','none');
			$('.layerpop .month_container').css('display','block');
			
			
			let yearlyPlan_id = id;
			let title = decodeURIComponent( monthlyPlan.title );
			let content = decodeURIComponent( monthlyPlan.content );
			let importance = decodeURIComponent( monthlyPlan.importance );
			let month = decodeURIComponent(monthlyPlan.month);
			let isCompleted = decodeURIComponent(monthlyPlan.isCompleted);

			displayPopUpFormGoalId()

			//goalList 가져오기
				getGoalAllList();
			//goalList 가져오기

			$('#layerpop_goalId_select').val('0');

			//goalId
			if(id === 'null'){
			} else{
				$('#layerpop_goalId_select').val(yearlyPlan_id);
			}
			//goalId

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
}

function displayPopUpFormAbsoluteValueId(){
	$('#layerpop_absoluteValueId').css('display','block');
}
function displayPopUpFormProjectId(){
	$('#layerpop_projectId').css('display','block');
}
function displayPopUpFormGoalId(){
	$('#layerpop_goalId').css('display','block');
}

function getGoalAllList(){

	let data;
	//ajax 호출
	var url = contextPath + "weplan/yearlyPlan/getYearlyPlanAllList.do";
	$.ajax({
		url : url,
		dataType :"json",
		type : "POST",
		async: false,
		success : function(result) {
			data = result;
			
			for(let i in data){
				let yearlyPlanVO = data[i];
				let yearlyPlanId = decodeURIComponent( yearlyPlanVO.id );
				let title = decodeURIComponent( yearlyPlanVO.title );
				let content = decodeURIComponent( yearlyPlanVO.content );
				let importance = decodeURIComponent( yearlyPlanVO.importance );
				let goal_id = decodeURIComponent( yearlyPlanVO.goal_id );

				$(`#layerpop_goalId_select`).append(
					`<option value="${yearlyPlanId}">${title}</option>`
				)
			}
				
		},
	});
	//ajax 호출
}

function getProjectAllList(){

	let data;
	//ajax 호출
	var url = contextPath + "weplan/goal/getGoalAllList.do";
	$.ajax({
		url : url,
		dataType :"json",
		type : "POST",
		async: false,
		success : function(result) {
			data = result;
			
			for(let i in data){
				let goalVO = data[i];
				let goalId = decodeURIComponent( goalVO.id );
				let title = decodeURIComponent( goalVO.title );
				let content = decodeURIComponent( goalVO.content );
				let importance = decodeURIComponent( goalVO.importance );

				$(`#layerpop_projectId_select`).append(
					`<option value="${goalId}">${title}</option>`
				)
			}
				
		},
	});
	//ajax 호출
}

function getAbsoluteValueList(){

	let data;
	//ajax 호출
	var url = contextPath + "weplan/absoluteValue/getAbsoluteValueList.do";
	$.ajax({
		url : url,
		dataType :"json",
		type : "POST",
		async: false,
		success : function(result) {
			data = result;
			
			for(let i in data){
				let absoluteValueVO = data[i];
				let absoluteValueId = decodeURIComponent( absoluteValueVO.id );
				let title = decodeURIComponent( absoluteValueVO.title );
				let content = decodeURIComponent( absoluteValueVO.content );
				let importance = decodeURIComponent( absoluteValueVO.importance );

				$(`#layerpop_absoluteValueId_select`).append(
					`<option value="${absoluteValueId}">${title}</option>`
				)
			}
				
		},
	});
	//ajax 호출
}

	/* 레이어팝업 */
	var contextPath = window.location.protocol + "//" + window.location.host + "/";
	
	function todayYear(){
		let today = new Date();
		let year = today.getFullYear(); // 년도
		return year;
	}

	//오늘날짜 반환
	function today() {
		let today = new Date();
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		
		if(month < 10){
			month = String(0) + String(month);
		}
		if(date < 10){
			date = String(0) + String(date);
		}
		
		let proccesedToday = year + '-' + month + '-' + date;
		
		return proccesedToday;
	}
	//오늘날짜 반환
	
	function todayTime() {
		let today = new Date();
		let hours = today.getHours(); // 시
		let minutes = today.getMinutes();  // 분
		
		if(hours < 10){
			hours = String(0) + String(hours);
		}
		if(minutes < 10){
			minutes = String(0) + String(minutes);
		}
		
		let proccesedTime = hours + ':' + minutes;
		
		return proccesedTime;
	}
	
	function todayMonth() {
		let today = new Date();
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		
		if(month < 10){
			month = String(0) + String(month);
		}

		let proccesedToday = year + '-' + month;
		
		return proccesedToday;
	}

	function wrapWindowByMask() {
		//화면의 높이와 너비를 구한다.
		var maskHeight = $(document).height(); 
		var maskWidth = $(window).width();

		//문서영역의 크기 
/*/*		console.log( "document 사이즈:"+ $(document).width() + "*" + $(document).height()); 
*/		//브라우저에서 문서가 보여지는 영역의 크기
/*		console.log( "window 사이즈:"+ $(window).width() + "*" + $(window).height());        */

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#mask').css({
			'width' : maskWidth,
			'height' : maskHeight
		});

		//애니메이션 효과
		//$('#mask').fadeIn(1000);      
		$('#mask').fadeTo("fast", 0.4);
	}

	function popupOpen() {
		$('.layerpop').css("position", "absolute");
		//영역 가운에데 레이어를 뛰우기 위해 위치 계산 
		$('.layerpop').css("top",(($(window).height() - $('.layerpop').outerHeight()) / 2) + $(window).scrollTop());
		$('.layerpop').css("left",(($(window).width() - $('.layerpop').outerWidth()) / 2) + $(window).scrollLeft());
		$('.layerpop').draggable();
		$('#layerbox').show();
	}

	function popupClose() {
		$('#layerbox').hide();
		$('.mask').hide();
	}

	function popupReset(){
		$('.layerpop .importance_container').css('display','block');
		$('.layerpop .title_container').css('display','block');
		$('.layerpop .layerpop_title').attr('value','');
		$('.layerpop .startDate_container').css('display','block');
		$('.layerpop .layerpop_startDate_form').attr('value','');
		$('.layerpop .limitDate_container').css('display','block');
		$('.layerpop .layerpop_limitDate_form').attr('value','');
		$('.layerpop .content_container').css('display','block');
		$('.layerpop #layerpop_form_content').text('');
		$('.layerpop .month_container').css('display','none');
		$('.layerpop .layerpop_month_form').attr('value','');
		$('#layerpop_yearlyPlan_id').attr('value','');
		$('#layerpop_week').attr('value','');
		$('#layerpop_absoluteValueId').css('display','none');
		$('#layerpop_absoluteValueId_select').val('0');
		$('#layerpop_projectId').css('display','none');
		$('#layerpop_projectId_select').val('0');
		$('#layerpop_goalId').css('display','none');
		$('#layerpop_goalId_select').val('0');
	}
	function changeTitle(title) {
		$('.layerpop .title').text(title);
	}

	function changeFormUrl(url) {
		$('.layerpop .layerpop_form').attr('action',url)
		
	}
	function checkInitialImportance() {
		$('.layerpop .importance1').prop("checked", true);
	}
	function putPlaceholderAtDates() {
		$('.layerpop .layerpop_startDate_form').attr('placeholder',today());
		$('.layerpop .layerpop_limitDate_form').attr('placeholder',today());
	}
	function putPlaceholderAtTimes() {
		$('.layerpop .layerpop_Time_form').attr('placeholder',todayTime());
	}
	function onTimeForm() {
		$('.layerpop .layerpop_Time_form').css('display','block');
	}
	function offTimeForm() {
		$('.layerpop .layerpop_Time_form').css('display','none');
	}
	function putPlaceholderAtMonth() {
		$('.layerpop .layerpop_month_form').attr('placeholder',todayMonth());
	}

	function popUpSetting(title, url) {
		
		changeTitle(title);
		changeFormUrl(url);
		//중요도를 바꾸는데 시간이 걸려서 0.1초 늦게 띄움
		setTimeout(function() {
			popupOpen(); //레이어 팝업창 오픈 
		}, 100);
		
		wrapWindowByMask(); //화면 마스크 효과 
	};

	/* 레이어팝업 */