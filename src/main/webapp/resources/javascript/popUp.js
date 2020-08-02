/**
 * 
 */


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
	$('.layerpop .inbox_add').css('display','none');
	$('.layerpop .layerpop_area .content .absoluteValue_update').css('display','none');
	$('.layerpop .layerpop_area .content .add').css('display','none');
	
}


function goDetail() {
	//중요도를 바꾸는데 시간이 걸려서 0.1초 늦게 띄움
	setTimeout(function() {
		popupOpen(); //레이어 팝업창 오픈 
	}, 100);
	
	wrapWindowByMask(); //화면 마스크 효과 
};