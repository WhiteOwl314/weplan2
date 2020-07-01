package com.whiteowl.weplan.task.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
	        	cal.add(Calendar.DATE, -6);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +0);
				day2 = dateFormat.format(cal2.getTime());
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
	        	cal.add(Calendar.DATE, -2);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +4);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	        case 5:
	        	cal.add(Calendar.DATE, -3);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +3);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	        case 6:
	        	cal.add(Calendar.DATE, -4);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +2);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	        case 7:
	        	cal.add(Calendar.DATE, -5);
	        	day = dateFormat.format(cal.getTime());
				cal2.add(Calendar.DATE, +1);
				day2 = dateFormat.format(cal2.getTime());
	            break ;
	             
	    }

		return taskDAO.weelkyTaskList(member_id, day, day2);
	}
	
	@Override
	public Map returnMondaySunday(String date) throws Exception {
		
		String monday = "";
		String tuesday = "";
		String wednesday = "";
		String thursday = "";
		String friday = "";
		String saturday = "";
		String sunday = "";
		
		Map<String, String> dayList = new HashMap<String, String>();
	     
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate = dateFormat.parse(date);
		 
		Calendar calMon = Calendar.getInstance();
		calMon.setTime(nDate);
		
		Calendar calTue = Calendar.getInstance();
		calTue.setTime(nDate);
		
		Calendar calWed = Calendar.getInstance();
		calWed.setTime(nDate);
	
		Calendar calThu = Calendar.getInstance();
		calThu.setTime(nDate);
		
		Calendar calFri = Calendar.getInstance();
		calFri.setTime(nDate);

		Calendar calSat = Calendar.getInstance();
		calSat.setTime(nDate);

		Calendar calSun = Calendar.getInstance();
		calSun.setTime(nDate);

		
		int dayNum = calMon.get(Calendar.DAY_OF_WEEK);
		
		 switch(dayNum){
	        case 1:
	        	calMon.add(Calendar.DATE, -6);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, -5);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, -4);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, -3);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, -2);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, -1);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +0);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 2:
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, +1);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, +2);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, +3);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, +4);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +5);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +6);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 3:
	        	calMon.add(Calendar.DATE, -1);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, +0);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, +1);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, +2);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, +3);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +4);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +5);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 4:
	        	calMon.add(Calendar.DATE, -2);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, -1);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, +0);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, +1);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, +2);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +3);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +4);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 5:
	        	calMon.add(Calendar.DATE, -3);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, -2);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, -1);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, +0);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, +1);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +2);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +3);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 6:
	        	calMon.add(Calendar.DATE, -4);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, -3);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, -2);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, -1);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, +0);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +1);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +2);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	        case 7:
	        	calMon.add(Calendar.DATE, -5);
	        	monday = dateFormat.format(calMon.getTime());
	        	calTue.add(Calendar.DATE, -4);
	        	tuesday = dateFormat.format(calTue.getTime());
	        	calWed.add(Calendar.DATE, -3);
	        	wednesday = dateFormat.format(calWed.getTime());
	        	calThu.add(Calendar.DATE, -2);
	        	thursday = dateFormat.format(calThu.getTime());
	        	calFri.add(Calendar.DATE, -1);
	        	friday = dateFormat.format(calFri.getTime());
	        	calSat.add(Calendar.DATE, +0);
	        	saturday = dateFormat.format(calSat.getTime());
	        	calSun.add(Calendar.DATE, +1);
	        	sunday = dateFormat.format(calSun.getTime());
	            break ;
	    }
		 
		dayList.put("monday", monday);
		dayList.put("tuesday", tuesday);
		dayList.put("wednesday", wednesday);
		dayList.put("thursday", thursday);
		dayList.put("friday", friday);
		dayList.put("saturday", saturday);
		dayList.put("sunday", sunday);
		
		return dayList;

	}
	

}
