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
			let title = decodeURIComponent( result.title );
			let content = decodeURIComponent( result.content );
			let importance = decodeURIComponent( result.importance );
			$('.layerpop .layerpop_id').val(result.id);
			$('.layerpop .layerpop_title').val(title);
			$('.layerpop .layerpop_content').val(content);
			
			/* 중요도 초기값 */
			if(importance=='1'){
				$('.layerpop .importance1').prop("checked", true);
			} else if (importance == '2'){
				$('.layerpop .importance2').prop("checked", true);
			} else if (importance == '3'){
				$('.layerpop .importance3').prop("checked", true);
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

	//수정 버튼
	$(this).css('display','none');
	$('.content .header .menu .update_on').css('display','block');
	$('.item-delete').css('display','block');
	$('.absoluteValue-item-container').off('click');
	//수정 버튼
	
	//item 클릭
	$('.absoluteValue-item-container').click(function() {
		let absoluteValueID = $(this).attr("id");
		absoluteValue_update_popUP_Open(absoluteValueID);
		let title = "Absolute Value 수정";
		let url = contextPath + "weplan/absoluteValue/updateAbsoluteValue.do";
		popupReset();	
		$('.layerpop .startDate_container').css('display','none');
		$('.layerpop .limitDate_container').css('display','none');
		popUpSetting(title, url);
		});
	//item 클릭
});
//수정클릭

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
	let title = "Absolute Value 추가";
	let url = contextPath + "weplan/absoluteValue/addAbsoluteValue.do";
	popupReset();	
	checkInitialImportance();
	$('.layerpop .startDate_container').css('display','none');
	$('.layerpop .limitDate_container').css('display','none');
	popUpSetting(title, url);
});
		