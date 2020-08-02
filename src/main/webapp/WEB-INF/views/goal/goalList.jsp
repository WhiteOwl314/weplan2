<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<div
	class="project"
>
	<div
		class="project_header"
	>
		<div
			class="project_title"
		>
			Project
		</div>
	</div>
	<div
		class="project_body"
	>
		<div
			class="project_left"
		>
			<div
				class="project_List"
			>
				<c:forEach items="${goalList }" var="project">
					<div
						id="${project.id }"
						class="project_List-text"
					>
						<img 
							alt="project_title" 
							src="${contextPath }/resources/images/fiber_manual_record-black-18dp.svg"
						>
						${project.title }
					</div>
				</c:forEach>
				<div
					class="project_add"
				>
					<div
						class="project_button"
					>
						<img
							alt="add_button" 
							src="${contextPath }/resources/images/add-black-18dp.svg"
						>
					</div>
				</div>
			</div>
		</div>
		<div
			class="project_right"
		>
			<div
				class="project_detail"
			>
				<div
					class="project_detail_header"
				>
					<div
						class="project_dtail_completed"
					>
						<img  alt="checkbox" 
							class="checkbox"
							id="checkbox${task.id }"
							src="${contextPath }/resources/images/iconmonstr-checkbox-11.svg"
						>
					</div>
					<div
						class="project_detail_title"
					>
						<div
							class="project_detail_text"
						>
						</div>
						<div
							class="project_detail_class"
						>
						</div>
					</div>
				</div>
				<div
					class="project_detail_body"
				>
				</div>
			</div>
		</div>
	</div>

</div>
	<div>
		<form 
			action="${contextPath}/goal/addGoal.do"
			method="post"
		>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
			<input type="text" name="title" placeholder="title">
			<input type="text" name="content" placeholder="content"/>
			<input id="date" type="hidden" name="date" value="0000-00-00"/>
			<input id="due" type="button" name="due" value="due">
			<input id="nullDate" type="hidden" name="nullDate" value="x">
			<input type="hidden" name="member_id" value="${ member.id }">
			<input type="submit" value="save"/>
		</form>
	</div>
	
	
	<c:forEach 
		var="goal"
		items="${goalList }"
	>
		<div>
			<span>
				${goal.title }
			</span>
			<span
				class="item"
				id="${goal.id }"
				onclick="javascript:goDetail(${goal.id })"
				
			>
				수정하기
			</span>
			<span>
				<a
					href="${contextPath }/goal/deleteGoal.do?id=${goal.id}"
				>
					삭제하기
				</a>
			</span>
		</div>
	</c:forEach>
	
	<div>
		<!-- 팝업뜰때 배경 -->
		<div id="mask"></div>

		<!--Popup Start -->
		<div id="layerbox" class="layerpop"
			style="width: 700px; height: 350px;">
			<article class="layerpop_area">
				<div class="title">레이어팝업 타이틀</div>
				<a href="javascript:popupClose();" class="layerpop_close"
					id="layerbox_close"></a> <br>
				<div class="content">
					<form 
						action="${contextPath }/goal/updateGoal.do"
						method="post"	
					>
						<p>
							<label>IMPORTANCE</label> 
							<label><input type="radio" name="importance" value="1">상</label>
							<label><input type="radio" name="importance" value="2">중</label>
							<label><input type="radio" name="importance" value="3">하</label>
						</p>
						<p>
							<label>TITLE</label> 
							<input type="text" id="popUp-title" name="title"> 
						</p>
						<p>
							<label>CONTENT</label> 
							<textarea id="popUp-content" name="content" cols="50" rows="10"></textarea> 
						</p>
						<p>
							<label>LIMITDATE</label> 
							<input type="date" id="popUp-limitDate" name="limitDate"> 
							<input type="hidden" id="popUp-due" value="due">
							<input type="button" id="popUp-nullDate" value="x">
						</p>
						
						<input type="hidden" name="member_id" value="${ member.id }">
						<input type="hidden" name="id" id="popUp-id">

						<button type="submit">수정</button>
					</form>
				</div>
			</article>
		</div>
    <!--Popup End -->

		
	</div>

	
	
	<script type="text/javascript">
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

		
		
		/* 레이어팝업 */
		
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
			$('#mask').fadeTo("slow", 0.5);
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
			$('#mask').hide();
		}

		function goDetail(goal_id) {

			/*팝업 오픈전 별도의 작업이 있을경우 구현*/ 

			popupOpen(); //레이어 팝업창 오픈 
			wrapWindowByMask(); //화면 마스크 효과 
			
			$.ajax({
				url : "${contextPath}/goal/popUpGoalView.do",
				dataType :"json",
				type : "POST",
				data : {
					id : goal_id
				},
				success : function(result) {
					var title = decodeURIComponent( result.title );
					var content = decodeURIComponent( result.content );
					var importance = decodeURIComponent( result.importance );
					var limitDate = decodeURIComponent(result.limitDate);
					console.log(limitDate);
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
		};


		
	</script>