package com.whiteowl.weplan.task.dao;


import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskDAO {


	public int insertInboxTask(TaskVO task) throws DataAccessException;

	public int insertInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO selectTask(int taskNO) throws DataAccessException;

	public void updateTask(Map<String, Object> taskMap) throws DataAccessException;

	public void updateTaskNullDate(Map<String, Object> taskMap) throws DataAccessException;

	public void removeTask(int taskNO) throws DataAccessException;

	public void completeTask(int taskNO) throws DataAccessException;

	public List selectAllInboxTaskList(String member_id) throws DataAccessException;

	public List weelkyTaskList(String member_id, String date, String day2) throws DataAccessException;

	public void moveDate(int task_id, String newLimitDate) throws DataAccessException;

	public JSONObject popUpTaskView(String task_id) throws DataAccessException;

}
