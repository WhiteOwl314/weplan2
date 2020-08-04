/**
 * 
 */

var contextPath = window.location.protocol + "//" + window.location.host + "/";

//update popUP 세팅
function absoluteValue_update_setPopUP(absoluteValueID){
	var url = contextPath + "weplan/absoluteValue/popUpAbsoluteValueView.do";
	$.ajax({
		url : url,
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
}
//update popUP 세팅

//update popUp 띄우기
function absoluteValue_update_popUP_Open(absoluteValueID) {
	
	//ajax호출하고 값 넣기
	absoluteValue_update_setPopUP(absoluteValueID)
	//ajax호출하고 값 넣기
	
	//중요도를 바꾸는데 시간이 걸려서 0.1초 늦게 띄움
	setTimeout(function() {
		popupOpen(); //레이어 팝업창 오픈 
	}, 100);
	
	wrapWindowByMask(); //화면 마스크 효과 
}
//update popUp 띄우기



// 처음 클릭
$('.absoluteValue-item-container').click(function() {
	var id = $(this).attr("id");
	location.href=`${contextPath }weplan/absoluteValue/absoluteValueView.do?id=` + id;
});


//수정클릭
$(".content .header .menu .update").click(function() {
	popupReset();
	$(this).css('display','none');
	$('.content .header .menu .update_on').css('display','block');
	$('.item-delete').css('display','block');
	$('.absoluteValue-item-container').off('click');
	
	//item 클릭
	$('.absoluteValue-item-container').click(function() {
		popupReset();
		$('.layerpop .layerpop_area .content .absoluteValue_update').css('display','block');
		$('.layerpop .layerpop_area .content .add').css('display','none');
		$('.layerpop_area .title').text('수정');
		var absoluteValueID = $(this).attr("id");
		absoluteValue_update_popUP_Open(absoluteValueID);
	});
	//item 클릭
});

// x 클릭
$(".content .header .menu .update_on").click(function() {
	popupReset();
	$(this).css('display','none');
	$('.content .header .menu .update').css('display','block');
	$('.item-delete').css('display','none');
	$('.absoluteValue-item-container').off('click');
	$('.absoluteValue-item-container').click(function() {
		var id = $(this).attr("id");
		location.href=`${contextPath }weplan/absoluteValue/absoluteValueView.do?id=` + id;
	});

});

// 추가버튼
$('.content .header .menu .add').click(function() {
	popupReset();
	$('.absoluteValue_form.absoluteValue_update').css('display','none');
	$('.layerpop .layerpop_area .content .add').css('display','block');
	$('.layerpop_area .title').text('추가');
	goDetail();
});
		