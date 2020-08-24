package com.whiteowl.weplan.task.dao;


import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskDAO {


	public int insertInboxTask(TaskVO task) throws DataAccessException;

	public int insertInboxTaskNullDate(TaskVO taskVO) throws DataAccessException;

	public TaskVO selectTask(int taskNO) throws DataAccessException;

	public void updateTaskNullDate(Map<String, Object> taskMap) throws DataAccessException;

	public void removeTask(int taskNO) throws DataAccessException;

	public List selectAllInboxTaskList(String member_id) throws DataAccessException;

	public List weelkyTaskList(String member_id, String date, String day2) throws DataAccessException;

	public void moveDate(int task_id, String newLimitDate) throws DataAccessException;

	public JSONObject popUpTaskView(String task_id) throws DataAccessException;

	public JSONArray getTaskListByMonthAndWeek(Map<String, Object> map) throws DataAccessException;

	public void notCompleteTask(Map<String, Object> map) throws DataAccessException;

	public JSONObject popUpGetTask(Map<String, Object> map) throws DataAccessException;

	public void updateTask(TaskVO taskVO) throws DataAccessException;

	public void addTaskWithYearlyPlanId(TaskVO taskVO) throws DataAccessException;

	public void updateTaskWithYearlyPlanId(TaskVO taskVO) throws DataAccessException;

	public void moveTaskAjax(TaskVO taskVO) throws DataAccessException;

	public void completeTask(Map<String, Object> map) throws DataAccessException;

}
