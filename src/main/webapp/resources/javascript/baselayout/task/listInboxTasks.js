/**
 * 
 */

	function inboxTask_complete(taskId){
		var url = contextPath + "weplan/task/completeTask.do?id=" + taskId;
		$(`#inboxTask_${taskId} .inboxTask_completed`).css("display",'none');
		$(`#inboxTask_${taskId} .inboxTask_completed_on`).css("display",'block');
		$(`#inboxTask_${taskId} .inboxTask_title`).css("text-decoration","line-through");
		location.href = url;
	}	
	function inboxTask_notComplete(taskId){
		
	}