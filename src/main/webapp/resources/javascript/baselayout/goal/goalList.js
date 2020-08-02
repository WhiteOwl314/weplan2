/**
 * 
 */

$(document).ready(function() {
	var contextPath = window.location.protocol + "//" + window.location.host + "/";
	
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

				$('.project_detail_title .project_detail_text').html(title);
				$('.project_detail_title .project_detail_class').html('project');
				$('.project_detail_header .project_dtail_completed').css('display','block')
				
				$('.project_detail_header .project_dtail_completed').click(function(event) {
					var url = contextPath + "weplan/goal/completeGoal.do?id=" + id;
					var target = event.target;
					var check_url = contextPath + "weplan/resources/images/iconmonstr-checkbox-9.svg" 
					console.log(target);
					$(target).attr("src",check_url);
					location.href = url;
				})

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
				
				/* 중요도 초기값 */
				if(importance=='1'){
					$('input:radio[name=importance]:input[value="1"]').attr("checked", true);
				} else if (importance == '2'){
					$('input:radio[name=importance]:input[value="2"]').attr("checked", true);
				} else if (importance == '3'){
					$('input:radio[name=importance]:input[value="3"]').attr("checked", true);
				}

			},

		})
		
	}
	
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
				console.log(result);
				var title = decodeURIComponent( result.title );
				var content = decodeURIComponent( result.content );
				var importance = decodeURIComponent( result.importance );
				var limitDate = decodeURIComponent(result.limitDate);
				var startDate = decodeURIComponent(result.startDate);
				console.log(title);
				
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
				
				/* 중요도 초기값 */
				if(importance=='1'){
					$('input:radio[name=importance]:input[value="1"]').attr("checked", true);
				} else if (importance == '2'){
					$('input:radio[name=importance]:input[value="2"]').attr("checked", true);
				} else if (importance == '3'){
					$('input:radio[name=importance]:input[value="3"]').attr("checked", true);
				}

			},

		})
		
	}
	
	$('.project_List-text').click(function(event) {
		var project_id = event.target.id;
		getProject(project_id)
		getYearlyPlanList(project_id)
	})
	
})