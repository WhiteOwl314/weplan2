package com.whiteowl.weplan.task.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.task.dao.TaskDAO;

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

}
