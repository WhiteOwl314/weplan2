/**
 * 
 */


$(document).ready(function(){
	
	/* inbox 버튼 */
	$('.inbox_button').click(function(){
		$(this).css('display','none')

		$('.inbox_button_on').css('display','block')
		popUpTaskAddWithoutDay();
	});
	/* inbox 버튼 */

	/* inbox_on 버튼 */
	$('.inbox_button_on').click(function(){
		$(this).css('display','none')
		$('.inbox_button').css('display','block')
		$('#layerbox').hide();
		$('.mask').hide();
		offTimeForm();
		popupReset();
	});
	/* inbox_on 버튼 */
	
	//inbox cancle 버튼
	$('.layerpop .cancle').click(function() {
		$('.inbox_button_on').css('display','none')
		$('.inbox_button').css('display','block')
		$('#layerbox').hide();
		$('.mask').hide();
		offTimeForm();
		popupReset();
	})
	//inbox cancle 버튼
	
});