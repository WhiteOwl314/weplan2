package com.whiteowl.weplan.task.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskService {

	public int addInboxTask(TaskVO task) throws DataAccessException;

	public int addInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO viewTask(int taskNO) throws Exception;

	public void updateTask(Map<String, Object> taskMap) throws Exception;

	public void updateTaskNullDate(Map<String, Object> taskMap) throws Exception;

	public void removeTask(int taskNO) throws Exception;

	public void completeTask(int taskNO) throws Exception;

	public List listInboxTasks(String member_id) throws DataAccessException;

	public List weeklyTaskList(String member_id, String date) throws Exception;

	public Map returnMondaySunday(String date) throws Exception;

	public void moveDate(int task_id, String newLimitDate) throws Exception;

	public void popUpTaskView(String task_id, HttpServletResponse response) throws Exception;

}
