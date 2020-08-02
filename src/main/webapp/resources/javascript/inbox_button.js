/**
 * 
 */


$(document).ready(function(){
	
	/* inbox 버튼 */
	$('.inbox_button').click(function(){
		$(this).css('display','none')

		$('.layerpop_area .title').text('Task 추가');
		$('.inbox_button_on').css('display','block')
		popupReset();
		
		$('.layerpop .inbox_add').css('display','block');
		goDetail();
	});
	/* inbox 버튼 */

	/* inbox_on 버튼 */
	$('.inbox_button_on').click(function(){
		$(this).css('display','none')
		$('.inbox_button').css('display','block')
		$('#layerbox').hide();
		$('.mask').hide();
		popupReset();
	});
	/* inbox_on 버튼 */
	
	//inbox cancle 버튼
	$('.inbox_add .cancle').click(function() {
		$('.inbox_button_on').css('display','none')
		$('.inbox_button').css('display','block')
		$('#layerbox').hide();
		$('.mask').hide();
		popupReset();
	})
	//inbox cancle 버튼
	
	/* due 버튼 */
	$('#due').click(function(){
		$("#date").attr("type","date");
		$("#time").attr("type","time");
		/* 현재시간으로 */
		document.getElementById('date').valueAsDate = new Date();
		$("#time").attr("value","18:00");
		$("#due").attr("type","hidden");
		$("#nullDate").attr("type","button");
	});
	
	
	/* nullDate 버튼 */
	$('#nullDate').click(function(){
		$("#date").attr("type","hidden");
		$("#time").attr("type","hidden");
		$("#date").attr("value","0000-00-00");
		$("#time").attr("value","00:00");
		$("#due").attr("type","button");
		$("#nullDate").attr("type","hidden");

	});

});