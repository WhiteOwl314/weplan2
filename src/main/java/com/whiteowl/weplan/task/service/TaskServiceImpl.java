package com.whiteowl.weplan.task.service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.task.dao.TaskDAO;
import com.whiteowl.weplan.task.vo.TaskVO;

@Service("taskService")
@Transactional(propagation = Propagation.REQUIRED)
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDAO taskDAO;

	@Override
	public List listInboxTasks(String member_id) throws DataAccessException {
		List inboxTasksList = null;
		inboxTasksList = taskDAO.selectAllInboxTaskList(member_id);
		return inboxTasksList;
	}

	@Override
	public int addInboxTask(TaskVO task) throws DataAccessException {
		return taskDAO.insertInboxTask(task);
	}

	@Override
	public int addInboxTaskNullDate(TaskVO taskVO) throws DataAccessException {
		return taskDAO.insertInboxTaskNullDate(taskVO);
	}

	@Override
	public TaskVO viewTask(int taskNO) throws Exception {
		TaskVO taskVO = taskDAO.selectTask(taskNO);
		return taskVO;
	}

	@Override
	public void updateTask(Map<String, Object> taskMap) throws Exception {
		taskDAO.updateTask(taskMap);
	}

	@Override
	public void updateTaskNullDate(Map<String, Object> taskMap) throws Exception {
		taskDAO.updateTaskNullDate(taskMap);
		
	}

	@Override
	public void removeTask(int taskNO) throws Exception {
		taskDAO.removeTask(taskNO);
		
	}

	@Override
	public void completeTask(int taskNO) throws Exception {
		taskDAO.completeTask(taskNO);
		
	}

	@Override
	public List weeklyTaskList(String member_id, String date) throws Exception {
		
		String day = "" ;
		String day2 = "" ;
	     
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate = dateFormat.parse(date);
		 
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(nDate);
		 
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		
		 switch(dayNum){
	        case 1:
	            break ;
	        case 2:
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +6);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	        case 3:
	        	cal.add(Calendar.DATE, -1);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +5);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	        case 4:
	            day = "수";
	            break ;
	        case 5:
	            day = "목";
	            break ;
	        case 6:
	            day = "금";
	            break ;
	        case 7:
	            day = "토";
	            break ;
	             
	    }

		return taskDAO.weelkyTaskList(member_id, day, day2);
	}
	
	

}
