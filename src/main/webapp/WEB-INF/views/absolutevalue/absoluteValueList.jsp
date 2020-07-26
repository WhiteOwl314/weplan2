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
	<title>Insert title here</title>
	<style type="text/css">
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
		
		.absoluteValue-item-container{
			width: 700px;
			height: 100px;
			border: 1px solid;
			margin: auto;
			margin-top: 30px;
			border-radius: 30px;
		}
		
		.absoluteValue-item-container .item{
			color: black;
			padding-top: 30px;
			font-size: 30px;
		}
		
		.absoluteValue-item-container a{
			text-decoration: none;
		}
		
		
		
	</style>
	<!-- draggable -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
</head>
<body>
	
	<div>
		<form 
			action="${contextPath}/absoluteValue/addAbsoluteValue.do"
			method="post"
		>
			<label><input type="radio" name="importance" value="1">상</label>
			<label><input type="radio" name="importance" value="2">중</label>
			<label><input type="radio" name="importance" value="3">하</label>
			<input type="text" name="title" placeholder="title">
			<input type="text" name="content" placeholder="content"/>
			<input type="hidden" name="member_id" value="${ member.id }">
			<input type="submit" value="save"/>
		</form>
	</div>
	
	<c:forEach var="absoluteValue" items="${absoluteValueList }">
		<div 
			align="center"
			class="absoluteValue-item-container"
		>
			<a
				href="${contextPath }/absoluteValue/absoluteValueView.do?id=${absoluteValue.id}"
			>
				<div
					class="item"
					id="${absoluteValue.id }"
				>
					<script>

						//을를 구분
						function reulReturner(label) {

							 var strGA = 44032; //가
							 var strHI = 55203; //힣

							 var lastStrCode = label.charCodeAt(label.length-1);
							 var prop=true;
							 var msg;

							 if(lastStrCode < strGA || lastStrCode > strHI) {
							  return false; //한글이 아님
							 }

							 if (( lastStrCode - strGA ) % 28 == 0) prop = false;

							 if(prop) {
							  msg = label+'을';
							 }
							 else {
							  msg = label+'를';
							 }

							 return msg;
						}	

						document.write('나는 '+reulReturner('${absoluteValue.title }')+' 위해 살겠다');
					</script> 
				</div>
			</a>
			<span
				id="${absoluteValue.id }"
				onclick="javascript:goDetail(${absoluteValue.id })"
			>
				수정
			</span>
			<a href="${contextPath }/absoluteValue/deleteAbsoluteValue.do?id=${absoluteValue.id}">
				<span>
					삭제
				</span>
			</a>
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
						action="${contextPath }/absoluteValue/updateAbsoluteValue.do"
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

		function goDetail(absoluteValueID) {

			/*팝업 오픈전 별도의 작업이 있을경우 구현*/ 

			popupOpen(); //레이어 팝업창 오픈 
			wrapWindowByMask(); //화면 마스크 효과 
			
			$.ajax({
				url : "${contextPath}/absoluteValue/popUpAbsoluteValueView.do",
				dataType :"json",
				type : "POST",
				data : {
					id : absoluteValueID
				},
				success : function(result) {
					var title = decodeURIComponent( result.title );
					var content = decodeURIComponent( result.content );
					var importance = decodeURIComponent( result.importance );
					$('#popUp-id').val(result.id);
					$('#popUp-title').val(title);
					$('#popUp-content').val(content);
					
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
</body>
</html>