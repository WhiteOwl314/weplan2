package com.whiteowl.weplan.task.service;


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
	public List listInboxTasks() throws DataAccessException {
		List inboxTasksList = null;
		inboxTasksList = taskDAO.selectAllInboxTaskList();
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
	
	

}
