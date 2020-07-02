<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>WEEKLY TASK LIST</title>
	<style type="text/css">
		.item {
		  background: #999;
		  height: 50px;
		  color: #fff;
		  padding: 12px 20px;
		  box-sizing: border-box;
		  margin: 0 0 10px;
		}
		.item.is-dragging {
		  background-color: red;
		}

		.bucket {
		  display: block;
		  width: 200px;
		  height: 90px;
		  margin: 0 0 18px;
		  border: 1px solid;
		  text-align: center;
		  padding: 15px;
		}
		.bucket.is-selecting{
			background-color: blue;
		}
		
		/*-- POPUP common style S ======================================================================================================================== --*/
		#mask {
			position: absolute;
			left: 0;
			top: 0;
			z-index: 999;
			background-color: #000000;
			display: none; }

		.layerpop {
			display: none;
			z-index: 1000;
			border: 2px solid #ccc;
			background: #fff;
			cursor: move; }

		.layerpop_area .title {
			padding: 10px 10px 10px 10px;
			border: 0px solid #aaaaaa;
			background: #f1f1f1;
			color: #3eb0ce;
			font-size: 1.3em;
			font-weight: bold;
			line-height: 24px; }

		.layerpop_area .layerpop_close {
			width: 25px;
			height: 25px;
			display: block;
			position: absolute;
			top: 10px;
			right: 10px;
			background: transparent url('btn_exit_off.png') no-repeat; }

		.layerpop_area .layerpop_close:hover {
			background: transparent url('btn_exit_on.png') no-repeat;
			cursor: pointer; }

		.layerpop_area .content {
			width: 96%;    
			margin: 2%;
			color: #828282; }
		/*-- POPUP common style E --*/
		
	</style>

	<!-- draggable -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
	
</head>
<body>

	<div 
		id="${dayList['monday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '2'}">
				<div 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</div>
			</c:if>
		</c:forEach>
	</div>


	<div 
		id="${dayList['tuesday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '3'}">
				<span 
					class="item" 
					id="${task.id} " 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
					onclick="javascript:goDetail(${task.id})"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['wednesday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '4'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['thursday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '5'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['friday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '6'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['saturday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '7'}">
				<span 
					class="item" id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	
	<div 
		id="${dayList['sunday']}" 
		class="bucket" 
		ondragover="allowDrop();" 
		ondrop="dropItem(event);"
		ondragenter="dragEnter();"
		ondragleave="dragLeave();"
		
	>
		<c:forEach var="task" items="${weeklyTaskList}">
			<c:if test="${task.day_of_week eq '1'}">
				<span 
					class="item" 
					id="${task.id}" 
					ondragstart="dragStart(event);" 
					draggable="true" 
					ondragend="dragEnd(event)"
				>
					${task.title }
				</span>
			</c:if>
		</c:forEach>
	</div>
	
	<div>
		<button onClick="javascript:goDetail('테스트');">팝업</button>
		<div style="height:1000px;"> </div>

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
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				레이어 팝업 내용<br/>
				
				<div>
					id : <span id="popUp_taskId"></span>
				</div>
				
		
			</div>
			</article>
    </div>
    <!--Popup End -->

		
	</div>
	
	<script type="text/javascript">
	
		/* 드래그 앤 드 */
		var task_id = "";
		var date = "";
	
		dragEnter = function() {
		  var _targetEle = event.target;
		  _targetEle.classList.add('is-selecting');
			
		}
		
		dragLeave = function() {
		  var _targetEle = event.target;
		  _targetEle.classList.remove('is-selecting');
		}
	
		allowDrop = function() {
		  event.preventDefault();
		};

		dropItem = function() {
		  var _targetEle = event.target;
		  var _id = event.dataTransfer.getData('text');
		  var _moveEle = document.getElementById(_id );
		  _targetEle.classList.remove('is-selecting');
		  console.log(_moveEle);
		  _targetEle.append(_moveEle);
		  date = _targetEle.id;
		  window.location.href="${contextPath}/task/moveDate.do?id="+task_id+"&date="+date;
		  console.log(task_id);
		  console.log(date);
		};

		dragStart = function() {
		  var _thisEle = event.target;
		  var _thisId = _thisEle.id;
		  _thisEle.classList.add('is-dragging');
		  event.dataTransfer.setData('text/plain', _thisId);
		  task_id = _thisId;
		};

		dragEnd = function() {
		  var _thisEle = event.target;
		  _thisEle.classList.remove('is-dragging');
		};
		
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

	    function goDetail(task_id) {

			$.ajax({
				url : "${contextPath}/task/popUpTaskView.do",
				dataType :"json",
				type : "POST",
				data : {
					id : task_id
				},
				success : function(result) {
					console.log(result.id);
					$('#popUp_taskId').text(result.id);
				},

			})

	        /*팝업 오픈전 별도의 작업이 있을경우 구현*/ 

	        popupOpen(); //레이어 팝업창 오픈 
	        wrapWindowByMask(); //화면 마스크 효과 
	        
	    };
	    
	</script>
</body>
</html>