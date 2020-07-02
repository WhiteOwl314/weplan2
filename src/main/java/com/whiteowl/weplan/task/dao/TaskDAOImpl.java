package com.whiteowl.weplan.task.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.task.vo.TaskVO;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllInboxTaskList(String member_id) throws DataAccessException {
		List<TaskVO> inboxTasksList = null;
		inboxTasksList = sqlSession.selectList("mapper.task.selectAllInboxTaskList", member_id);
		return inboxTasksList;
	}

	@Override
	public int insertInboxTask(TaskVO task) throws DataAccessException {
		int task_NO = selectNewTask_NO();
		task.setId(task_NO);
		sqlSession.insert("mapper.task.insertInboxTask" ,task);
		return task_NO;
	}

	@Override
	public int insertInboxTaskNullDate(TaskVO taskVO) throws DataAccessException {
		int task_NO = selectNewTask_NO();
		taskVO.setId(task_NO);
		sqlSession.insert("mapper.task.insertInboxTaskNullDate" ,taskVO);
		return task_NO;
	}

	private int selectNewTask_NO() throws DataAccessException{
		return sqlSession.selectOne("mapper.task.selectNewInboxTaskNO");
	}

	@Override
	public TaskVO selectTask(int taskNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.task.selectTask", taskNO);
	}

	@Override
	public void updateTask(Map<String, Object> taskMap) throws DataAccessException {
		sqlSession.update("mapper.task.updateTask", taskMap);
		
	}

	@Override
	public void updateTaskNullDate(Map<String, Object> taskMap) throws DataAccessException {
		sqlSession.update("mapper.task.updateTaskNullDate", taskMap);
		
	}

	@Override
	public void removeTask(int taskNO) throws DataAccessException {
		sqlSession.update("mapper.task.removeTask", taskNO);
		
	}

	@Override
	public void completeTask(int taskNO) throws DataAccessException {
		sqlSession.update("mapper.task.completeTask", taskNO);
	}

	@Override
	public List weelkyTaskList(String member_id, String day, String day2) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("day", day);
		map.put("day2", day2);
		return sqlSession.selectList("mapper.task.weeklyTaskList", map);
	}

	@Override
	public void moveDate(int task_id, String newLimitDate) throws DataAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("newLimitDate", newLimitDate);
		sqlSession.update("mapper.task.moveDate", map);
		
	}

	@Override
	public JSONObject popUpTaskView(String task_id) throws DataAccessException {

		JSONObject data = new JSONObject();
		String taskNO = task_id;
		
		TaskVO task = sqlSession.selectOne("mapper.task.selectTask", taskNO);
		int id = task.getId();
		int level = task.getLevel();
		String title = task.getTitle();
		String content = task.getContent();
		String isCompleted = task.getIsCompleted();
		int importance = task.getImportance();
		String isTask = task.getIsTask();
		String limitDate = task.getLimitDate();

		data.put("id", id);
		data.put("level", level);
		data.put("title", title);
		data.put("content", content);
		data.put("isCompleted", isCompleted);
		data.put("importance", importance);
		data.put("isTask",isTask);
		data.put("limitDate", limitDate);
		
		return data;
	}


}
