/**
 * 
 */

var contextPath = window.location.protocol + "//" + window.location.host + "/";

	function addZero(number) {
		
		if(number < 10){
			number = String(0) + String(number);
		}
		
		return number;
	}


	function getWeekByMonth(month) {
		let monthArray = month.split('-');
		let cYear = monthArray[0];
		let cMonth = monthArray[1];
		let date = new Date(cYear, cMonth-1);
		
	   // 월요일을 중심으로한 주차 구하기( JS기준 : 일요일 0 월요일 1 ~ 토요일 6 )

        let firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        let lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);

        //첫번째 월요일
        let firstMonday = new Date(
        		firstDay.getFullYear(), 
        		firstDay.getMonth(), 
        		firstDay.getDate()
		);
        let firstMondayGetDay = firstMonday.getDay();
        
        if(firstMondayGetDay == 0){
        	firstMonday.setDate(firstMonday.getDate() + 1);
        } else if (firstMondayGetDay == 1){
        } else {
        	while(!(firstMondayGetDay == 8)){
        		firstMonday.setDate(firstMonday.getDate() + 1);
				firstMondayGetDay++
        	}
        }
        
        //주가 몇개인지 구하기
        let weekNumber = 0;
        
        let lastMonday = new Date(
        		firstMonday.getFullYear(), 
        		firstMonday.getMonth(), 
        		firstMonday.getDate()
		);
        
        while(lastMonday <= lastDay){
        	lastMonday.setDate(lastMonday.getDate() + 7);
        	weekNumber++;
        }
        lastMonday.setDate(lastMonday.getDate() - 7);

        
        //반환 객체 만들기
        
        let weekList = new Array();
        
        for(let i=0 ; i < weekNumber; i++){
			let map = new Map();
        	let weekFirstDay = new Date(
            		firstMonday.getFullYear(), 
            		firstMonday.getMonth(), 
            		firstMonday.getDate()
    		);
        	weekFirstDay.setDate(weekFirstDay.getDate() + 7*i);
        	let weekLastDay = new Date(
        			weekFirstDay.getFullYear(), 
        			weekFirstDay.getMonth(), 
        			weekFirstDay.getDate()
    		);
        	weekLastDay.setDate(weekLastDay.getDate() + 6);
        	
        	
        	
        	let weekFirstDayString = 
        		weekFirstDay.getFullYear() + "-" 
        		+ addZero(weekFirstDay.getMonth()) + "-"
        		+ addZero(weekFirstDay.getDate());
        	let weekLastDayString = 
        		weekLastDay.getFullYear() + "-" 
        		+ addZero(weekLastDay.getMonth()) + "-"
        		+ addZero(weekLastDay.getDate());
        	
        	map.set("week",i+1);
        	map.set("weekFirstDay",weekFirstDayString);
        	map.set("weekLastDay",weekLastDayString);
        	
        	weekList.push(map);
        	
        }
        
		return weekList;
	}