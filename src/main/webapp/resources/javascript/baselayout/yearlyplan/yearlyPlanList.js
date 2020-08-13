
function getMonthlyPlanList(year) {
		let data;
		//ajax 호출
		var url = contextPath + "weplan/monthlyPlan/getMonthlyPlanListByYear.do";
		$.ajax({
			url : url,
			dataType :"json",
			type : "POST",
			data : {
				year : year
			},
			async: false,
			success : function(result) {
				data = result;
			},
		});
		//ajax 호출
		console.log(data);
		return data;
}

$(document).ready(function() {
	
	let year = $.trim($('#yearlyView_header_title').text());
	
	//month 생성
	for(let i=0; i<12; i++){
		let month = "";
		if(i+1 < 10){
			month = "0" + (i+1);
		} else {
			month = String(i+1);
		}
		
		$(`#yearlyView_month_container`).append(
				`<div
					id="yearlyView_month_${year}-${month}"
					class="yearlyView_month"
				>
				</div>`
		);
	}
	//month 생성
	
	//monthlyPlan 생성
		//List 받아오기
		let monthlyPlanList = getMonthlyPlanList(2020);
		//List 받아오기
		//위치시키기
		//위치시키기
	//monthlyPlan 생성
	
}); 

