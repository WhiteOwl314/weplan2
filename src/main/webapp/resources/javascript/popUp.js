/**
 * 
 */


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
		console.log( "document 사이즈:"+ $(document).width() + "*" + $(document).height()); 
		//브라우저에서 문서가 보여지는 영역의 크기
		console.log( "window 사이즈:"+ $(window).width() + "*" + $(window).height());        

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