/**
 * 
 */

$(document).ready(function() {
	/* due 버튼 */
	$('#due').click(function(){
		$("#date").attr("type","date");
		/* 현재시간으로 */
		document.getElementById('date').valueAsDate = new Date();
		$("#due").attr("type","hidden");
		$("#nullDate").attr("type","button");
	});
	
	/* nullDate 버튼 */
	$('#nullDate').click(function(){
		$("#date").attr("type","hidden");
		$("#date").attr("value","0000-00-00");
		$("#due").attr("type","button");
		$("#nullDate").attr("type","hidden");
	});
	
	/* due 버튼 */
	$('#popUp-due').click(function(){
		$("#popUp-limitDate").attr("type","date");
		/* 현재시간으로 */
		document.getElementById('popUp-limitDate').valueAsDate = new Date();
		$("#popUp-due").attr("type","hidden");
		$("#popUp-nullDate").attr("type","button");
	});
	
	/* nullDate 버튼 */
	$('#popUp-nullDate').click(function(){
		$("#popUp-limitDate").attr("type","hidden");
		$("#popUp-limitDate").attr("value","0000-00-00");
		$("#popUp-due").attr("type","button");
		$("#popUp-nullDate").attr("type","hidden");
	});

	var contextPath = window.location.protocol + "//" + window.location.host + "/";
	
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
				console.log(result);
				var id = decodeURIComponent( result.id );
				var title = decodeURIComponent( result.title );
				var content = decodeURIComponent( result.content );
				var importance = decodeURIComponent( result.importance );
				var limitDate = decodeURIComponent(result.limitDate);
				var startDate = decodeURIComponent(result.startDate);

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
					console.log(target);
					$(target).attr("src",check_url);
					location.href = url;
				})
				//할일 완료

				if(limitDate === 'null'){
					$('#popUp-limitDate').attr("type","hidden");
					$('#popUp-limitDate').val("0000-00-00");
					$("#popUp-due").attr("type","button");
					$("#popUp-nullDate").attr("type","hidden");
				} else {
					$('#popUp-limitDate').attr("type","date")
					$('#popUp-limitDate').val(limitDate);
					$("#popUp-due").attr("type","hidden");
					$("#popUp-nullDate").attr("type","button");
				}
				

			},

		})
		
	}
	//프로젝트 정보 가져오기
	
	function getYearlyPlanList(project_id) {
		var url = contextPath + "weplan/goal/yearlyPlanList.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				id : project_id
			},
			success : function(result) {
				
				
				//초기화
				$('.project_detail .project_detail_body').html('');
				//초기화
				
				for(var i in result){
					var id = decodeURIComponent( result[i].id ); 
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
										+ 'yearly plan'
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
							+ ' </div>'
	
					);
					
					/* IMPORTANCE */
					if(importance == '1'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'red');
					} else if (importance == '2'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'yellow');
					} else if (importance == '3'){
						$(`#project_yearly_${id} .project_yearly_importance`).css("background-color", 'white');
					}
					/* IMPORTANCE */
					
				}
				
				
				$('#popUp-id').val(result.id);
				$('#popUp-title').val(title);
				$('#popUp-content').val(content);
				
				if(limitDate === 'null'){
					$('#popUp-limitDate').attr("type","hidden");
					$('#popUp-limitDate').val("0000-00-00");
					$("#popUp-due").attr("type","button");
					$("#popUp-nullDate").attr("type","hidden");
				} else {
					$('#popUp-limitDate').attr("type","date")
					$('#popUp-limitDate').val(limitDate);
					$("#popUp-due").attr("type","hidden");
					$("#popUp-nullDate").attr("type","button");
				}
				
			},

		})
		
	}
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
				
				/* IMPORTANCE */
				if(importance=='1'){
					$('.project_update .importance1').attr("checked", true);
				} else if (importance == '2'){
					$('.project_update .importance2').attr("checked", true);
				} else if (importance == '3'){
					$('.project_update .importance3').attr("checked", true);
				}
				/* IMPORTANCE */

				//title
					$('.project_update .project_title').attr('value',title);
				//title

				//startDate
				if(startDate === 'null'){
				} else{
				}
				//startDate

				//limitDate
				if(limitDate === 'null'){
				} else{
				}
				//limitDate

				console.log(content);
				//content
				if(content === 'null'){
					$('.project_update .project_content').text('');
				} else{
					$('.project_update .project_content').text(content);
				}
				//content

				//id
				$('.project_update .project_id').attr('value',id);
				//id
			},

		})
		
	}
	//팝업 프로젝트 정보 가져오기
	
	$('.project_List-text').click(function(event) {
		var project_id = event.target.id;
		getProject(project_id)
		getYearlyPlanList(project_id)
	})



	//popUp-update
	$('.project_detail_header .project_detail_title').click(function(event) {
		var id = event.target.id;
		var id_arr = id.split('_');
		var processed_id = id_arr[1];
		//popUp reset
		popupReset();

		//popUp_폼 on
		$('.layerpop_area .title').text('Project');
		$('.layerpop .layerpop_area .content .project_update').css('display','block')
		
		//프로젝트 정보 가져오기
		//팝업 요소에 프로젝트 정보 삽입
		popUp_getProject(processed_id);
		
		//팝업 띄우기
		goDetail();
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
		//popUp reset
		popupReset();

		//popUp_폼 on
		$('.layerpop_area .title').text('Project 추가');
		$('.layerpop .layerpop_area .content .project_add_form').css('display','block')
		
		//팝업 띄우기
		goDetail();
	});
	//popUp-add
	
})