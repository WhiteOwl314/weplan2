<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>
 <link href="${contextPath }/resources/css/common/side.css" rel="stylesheet" type="text/css">

 <style>
   .no-underline{
      text-decoration:none;
   }
   .side_icon{
   	width:40px;
   	display: block; 
   	margin: 0px auto; 
   	padding-top: 3px;
   }
   .side-menu-item{
   	box-sizing: border-box;
   }
   .side-menu-item-text{
   	font-weight: bold;
   	color: #3B3B3B;
   }
	/* 마우스 클릭하고있을때 */
	.side-menu-item:active{
		background-color: #A75A21;
	}
	#side_menu-absoluteValueList .side-menu-item-text{
		font-size: 9px;
	}
 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
	<div>


		<div id="side-menu-item_1">
			<a href="${contextPath}/task/listInboxTasks.do" 
						class="no-underline">
				<div 
					class="side-menu-item"
					id="side_menu-inbox"
				>
					<img 
						alt="inbox" 
						src="${contextPath }/resources/images/inbox-black-18dp.svg"
						class="side_icon"
					>
					<div class="side-menu-item-text">
						Inbox
					</div>
				</div>
			</a>
		</div>
		
		<div id="side-menu-item_2">
			<a href="${contextPath}/absoluteValue/absoluteValueList.do"  class="no-underline">
				<div 
					class="side-menu-item"
					id="side_menu-absoluteValueList"
				>
					<img 
						alt="absoluteValue" 
						src="${contextPath }/resources/images/star-black-18dp.svg"
						class="side_icon"
					>
					<div class="side-menu-item-text">
						Absolute Value
					</div>
				</div>
			</a>
		</div>
		
		<div id="side-menu-item_3">
			<a href="${contextPath}/task/weeklyTaskList.do"  class="no-underline">
				<div 
					class="side-menu-item"
					id="side_menu-weeklyTaskList"
				>
					<img 
						alt="weeklyTask" 
						src="${contextPath }/resources/images/view_week-black-18dp.svg"
						class="side_icon"
					>
					<div class="side-menu-item-text">
						Weekly Task
					</div>
				</div>
			</a>
		</div>
		
		

	</div>
	<h1>
	</h1>
	
	<script type="text/javascript">
		window.onload = function(){
	        //전체주소
/* 	        console.log("url : "+$(location).attr('href'));
 */	 
	        //http:, localhost:port번호, index.html ?test=tttt 순으로 나누어져 있습니다.
/* 	        console.log("url : "+$(location).attr('protocol')+"//"+$(location).attr('host')+""+$(location).attr('pathname')+""+$(location).attr('search'));
 */	        
 
 			if($(location).attr('pathname') == "${contextPath}/task/listInboxTasks.do" ){
 				$('#side_menu-inbox').css('background-color','#EF802F');
 				$('#side_menu-inbox .side-menu-item-text').css('color','white');
 				$('.side-menu-item').removeClass('on');
 				$('#side_menu-inbox').addClass('on');
/*   				$('#side_menu-inbox').css('filter','brightness(70%)');
 */   		
 			} else if($(location).attr('pathname') == "${contextPath}/task/weeklyTaskList.do") {
 				$('#side_menu-weeklyTaskList').css('background-color','#EF802F');
 				$('#side_menu-weeklyTaskList .side-menu-item-text').css('color','white');
 				$('.side-menu-item').removeClass('on');
 				$('#side_menu-weeklyTaskList').addClass('on');
 			} else if($(location).attr('pathname') == "${contextPath}/absoluteValue/absoluteValueList.do") {
 				$('#side_menu-absoluteValueList').css('background-color','#EF802F');
 				$('#side_menu-absoluteValueList .side-menu-item-text').css('color','white');
 				$('.side-menu-item').removeClass('on');
 				$('#side_menu-absoluteValueList').addClass('on');
 			}
	    }
		
		/* 사이드메뉴에 마우스 올렸을때 */
		$('.side-menu-item').hover(function() {
				$(this).css('background-color','#EF802F');
				console.log(this);
				$(this).children('.side-menu-item-text').css('color','white');
			}, function() {
				if($(this).hasClass("on") == false ){
					$(this).css('background-color','#FFCC57');
					$(this).children('.side-menu-item-text').css('color','#3B3B3B');
				}
			}
		);
		
		
	</script>
</body>
</html>