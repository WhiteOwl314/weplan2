package com.whiteowl.weplan.task.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskDAO {

	public List selectAllInboxTaskList() throws DataAccessException;

	public int insertInboxTask(TaskVO task) throws DataAccessException;

	public int insertInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO selectTask(int taskNO) throws DataAccessException;

}
