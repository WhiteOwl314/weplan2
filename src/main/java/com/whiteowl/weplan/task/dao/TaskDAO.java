package com.whiteowl.weplan.task.dao;


import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskDAO {

	public List selectAllInboxTaskList() throws DataAccessException;

	public int insertInboxTask(TaskVO task) throws DataAccessException;

	public int insertInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO selectTask(int taskNO) throws DataAccessException;

	public void updateTask(Map<String, Object> taskMap) throws DataAccessException;

	public void updateTaskNullDate(Map<String, Object> taskMap) throws DataAccessException;

	public void removeTask(int taskNO) throws DataAccessException;

}
