package com.whiteowl.weplan.task.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskService {

	public List listInboxTasks() throws DataAccessException;

	public int addInboxTask(TaskVO task) throws DataAccessException;

	public int addInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO viewTask(int taskNO) throws Exception;

}
