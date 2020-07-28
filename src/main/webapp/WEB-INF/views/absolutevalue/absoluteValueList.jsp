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
		.layerpop .layerpop_area{
			padding: 20px 35px;
		}

		.layerpop_area .title {
			border: 0px solid #aaaaaa;
			color: #3B3B3B;
			font-size: 1.3em;
			font-weight: bold;
			line-height: 24px; 
		}

		.layerpop_area .content {
			width: 96%;    
			color: #828282;
			margin-top: 35px; 
		}
		/*-- POPUP common style E --*/
		
		.absoluteValue-item-container{
			width: 700px;
			height: 50px;
			border-radius: 10px;
			background-color: #FFCC57;
		}
		
		.absoluteValue-item-container .item{
			color: black;
			margin-top: 5px;
			font-size: 20px;
			margin-left: 5px;
			margin-right: 5px;
			border-radius: 10px;
			background-color: white;
			padding: 10px;
		}
		
		.absoluteValue-item-container a{
			text-decoration: none;
			position: relative;
		}
		
		.item-delete{
			display: none;
		}
		.absoluteValue.absoluteValue_header{
			display: flex;
			justify-content: space-between;
		}
		.absoluteValue_header.absoluteValue_title{
			margin-top: 30px;
			margin-left: 40px;
			font-size: 30px;
		}
		.absoluteValue_header .absoluteValue_menu{
			display: flex;
			margin-top: 15px;
		}
		.absoluteValue_text{
			padding-top: 7px;
			padding-left: 25px;
			color: white;
			font-size: 13px;
			box-sizing: border-box;
		}
		.absoluteValue_menu.absoluteValue_add{
			width: 70px;
			height: 25px;
			border-radius: 3px;
			background-color: #3B3B3B;
			cursor: pointer;
			
		}
		.absoluteValue_menu.absoluteValue_add:hover{
			background-color: #4f4f4f;
		}
		.absoluteValue_menu.absoluteValue_add:active{
			background-color: #7c7c7c;
		}
		
		.absoluteValue_menu.absoluteValue_update{
			width: 70px;
			height: 25px;
			border-radius: 3px;
			background-color: #3B3B3B;
			cursor: pointer;
			margin-left: 10px;
		}
		.absoluteValue_menu.absoluteValue_update:hover{
			background-color: #4f4f4f;
		}
		.absoluteValue_menu.absoluteValue_update:active{
			background-color: #7c7c7c;
		}
		
		.absoluteValue_menu.absoluteValue_update_on{
			width: 70px;
			height: 25px;
			border-radius: 3px;
			background-color: #EF802F;
			cursor: pointer;
			margin-left: 10px;
			display: none;
		}
		.absoluteValue_menu.absoluteValue_update_on:hover{
			background-color: #f29757;
		}
		.absoluteValue_menu.absoluteValue_update_on:active{
			background-color: #f2a168;
		}
		
		.absoluteValue_form.absoluteValue_add{
			display: none;
		}
		.absoluteValue_content.absoluteValue_container{
			display: flex;
			margin-top: 30px;
			justify-content: center;
		}
		.item-delete{
			padding-top: 7px;
			margin-left: 10px;
		}
		.item-delete .icon{
			width: 35px;
		}
		
		.importance.importance1{
			background-color: red;
		}
		.absoluteValue_add .form_title{
			color: #66738A;
			font-size: 15px;
		}
		.container{
			margin-bottom: 30px;
		}
		.item_container{
			background-color: #f7f7f7;
			border: 1px solid #e2e2e2;
			border-radius: 3px;
		}
		.absoluteValue_form textarea{
			width: 100%;
			height: 100px;
			resize: none;
			background-color: #f7f7f7;
			border: 1px solid #e2e2e2;
			border-radius: 3px;
		}  
		.absoluteValue_add_title{
			width: 100%;
			resize: none;
			background-color: #f7f7f7;
			border: 1px solid #e2e2e2;
			border-radius: 3px;
		}
		.absoluteValue_form input[type='submit']{
			width: 70px;
			height: 25px;
			border-radius: 3px;
			background-color: #3B3B3B;
			cursor: pointer;
			color: white;
			appearance: none;
			box-sizing: border-box;
			padding-top: 3px;
			border: none;
			text-decoration: none;
		}
		.absoluteValue_form input[type='submit']:hover {
			background-color: #4f4f4f;
		}
		.absoluteValue_form input[type='submit']:active {
			background-color: #7c7c7c;
		}
		.absoluteValue_add_submit{
			float: left;
		}
		.absoluteValue_form .cancle{
			width: 70px;
			height: 25px;
			border-radius: 3px;
			background-color: #3B3B3B;
			cursor: pointer;
			color: white;
			appearance: none;
			box-sizing: border-box;
			border: none;
			text-decoration: none;
			margin-left: 80px;
			
		}	
		.absoluteValue_form .cancle:hover {
			background-color: #4f4f4f;
		}
		.absoluteValue_form .cancle:active {
			background-color: #7c7c7c;
		}
		.absoluteValue_cancle .absoluteValue_text{
		}
		
		
		
	</style>
	<!-- draggable -->
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
</head>
<body>
	
	<div
		class="absoluteValue absoluteValue_header"
	>
		
		<div
			class="absoluteValue_header absoluteValue_title"
		>
			Absolute Value
		</div>
		<div
			class="absoluteValue_header absoluteValue_menu"
		>
			<div 
				class="absoluteValue_menu absoluteValue_add"
			>
				<div
					  class="absoluteValue_add absoluteValue_text"
				>
					추가
				</div>
			</div>
			<div 
				class="absoluteValue_menu absoluteValue_update"
			>
				<div
					  class="absoluteValue_update absoluteValue_text"
				>
					수정
				</div>
			</div>
			<div 
				class="absoluteValue_menu absoluteValue_update_on"
			>
				<div
					  class="absoluteValue_update absoluteValue_text"
				>
					수정
				</div>
			</div>
			
		</div>

	</div>

	<c:forEach var="absoluteValue" items="${absoluteValueList }">
		<div 
			align="center"
			class="absoluteValue_content absoluteValue_container"
		>
			<div
				onMouseOver = " window.status = '${contextPath }/absoluteValue/absoluteValueView.do?id=${absoluteValue.id}'" 
				onMouseOut = " window.status = '' "
				style="cursor:pointer;"
				class="absoluteValue-item-container"
				id="${absoluteValue.id }"
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
			</div>
			<div
				class="item-delete"
			>
				<a 
					href="${contextPath }/absoluteValue/deleteAbsoluteValue.do?id=${absoluteValue.id}"
				>
					<img 
						alt="delete" 
						src="${contextPath }/resources/images/highlight_off-black-18dp.svg"
						class="icon"
					>
				</a>
			</div>
		</div>
	</c:forEach>
	
	<div>
		<!-- 팝업뜰때 배경 -->
		<div id="mask"></div>

		<!--Popup Start -->
		<div id="layerbox" class="layerpop"
			style="width: 700px; height: 450px;">
			<article class="layerpop_area">
				<div class="title">수정</div>
				<div class="content">
					<form 
						action="${contextPath }/absoluteValue/updateAbsoluteValue.do"
						method="post"	
						class="absoluteValue_form absoluteValue_update"
					>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								중요도
							</div>
							<div
								class="item_container"
							>
								<label><input type="radio" name="importance" value="1" class="importance importance1">상</label>
								<label><input type="radio" name="importance" value="2" class="importance importance2">중</label>
								<label><input type="radio" name="importance" value="3" class="importance importance3">하</label>
							</div>
						</div>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								제목
							</div>
							<input id="popUp-title" class="absoluteValue_add_title" type="text" name="title" >
						</div>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								내용
							</div>
							<div>
								<textarea id="popUp-content" name="content"></textarea>
							</div>
						</div>
						<div
							class="container absoluteValue_add_submit"
						>
							<input type="submit" value="저장"/>
						</div>
						<div
							class="absoluteValue_form cancle layerpop_close"
							onclick="javascript:popupClose();"
						>
							<div
								class="absoluteValue_cancle absoluteValue_text"
							>
								취소
							</div>
						</div>
						<input type="hidden" name="member_id" value="${ member.id }">
						<input type="hidden" name="id" id="popUp-id">
					</form>
					
					<form 
						action="${contextPath}/absoluteValue/addAbsoluteValue.do"
						method="post"
						class="absoluteValue_form absoluteValue_add"
					>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								중요도
							</div>
							<div
								class="item_container"
							>
								<label><input type="radio" id="importance1" name="importance" value="1" class="importance importance1" checked="checked">상</label>
								<label><input type="radio" id="importance2" name="importance" value="2" class="importance importance2">중</label>
								<label><input type="radio" id="importance3" name="importance" value="3" class="importance importance3">하</label>
							</div>
						</div>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								제목
							</div>
							<input class="absoluteValue_add_title" type="text" name="title" >
						</div>
						<div
							class="container"
						>
							<div
								class="form_title"
							>
								내용
							</div>
							<div>
								<textarea name="content"></textarea>
							</div>
						</div>
						<div
							class="container absoluteValue_add_submit"
						>
							<input type="submit" value="저장"/>
						</div>
						<div
							class="absoluteValue_form cancle layerpop_close"
							onclick="javascript:popupClose();"
						>
							<div
								class="absoluteValue_cancle absoluteValue_text"
							>
								취소
							</div>
						</div>
						<input type="hidden" name="member_id" value="${ member.id }">
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
		    			$('.absoluteValue_update .importance1').prop("checked", true);
		    		} else if (importance == '2'){
		    			$('.absoluteValue_update .importance2').prop("checked", true);
		    		} else if (importance == '3'){
		    			$('.absoluteValue_update .importance3').prop("checked", true);
		    		}
				},

			})
			
			//중요도를 바꾸는데 시간이 걸려서 0.1초 늦게 띄움
			setTimeout(function() {
				popupOpen(); //레이어 팝업창 오픈 
			}, 100);
			
			wrapWindowByMask(); //화면 마스크 효과 
		};
		
		// 처음 클릭
		$('.absoluteValue-item-container').click(function() {
			var id = $(this).attr("id");
			console.log(id);
			location.href="${contextPath }/absoluteValue/absoluteValueView.do?id=" + id;
		});

		
		//수정클릭
		$(".absoluteValue_menu.absoluteValue_update").click(function() {
			$(this).css('display','none');
			$('.absoluteValue_menu.absoluteValue_update_on').css('display','block');
			$('.item-delete').css('display','block');
			$('.absoluteValue-item-container').off('click');
			$('.absoluteValue-item-container').click(function() {
				$('.absoluteValue_form.absoluteValue_update').css('display','block');
				$('.absoluteValue_form.absoluteValue_add').css('display','none');
				$('.layerpop_area .title').text('수정');
				var id = $(this).attr("id");
				goDetail(id);
			});
		});
		
		// x 클릭
		$(".absoluteValue_menu.absoluteValue_update_on").click(function() {
			$(this).css('display','none');
			$('.absoluteValue_menu.absoluteValue_update').css('display','block');
			$('.item-delete').css('display','none');
			$('.absoluteValue-item-container').off('click');
			$('.absoluteValue-item-container').click(function() {
				var id = $(this).attr("id");
				location.href="${contextPath }/absoluteValue/absoluteValueView.do?id=" + id;
			});

		});
		
		// 추가버튼
		$('.absoluteValue_menu.absoluteValue_add').click(function() {
			$('.absoluteValue_form.absoluteValue_update').css('display','none');
			$('.absoluteValue_form.absoluteValue_add').css('display','block');
			var id = $(this).attr("id");
			$('.layerpop_area .title').text('추가');
			goDetail(id);
		});
		

		

		
		
	</script>
</body>
</html>