package com.whiteowl.weplan.task.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.task.vo.TaskVO;

@Repository("taskDAO")
public class TaskDAOImpl implements TaskDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllInboxTaskList() throws DataAccessException {
		List<TaskVO> inboxTasksList = null;
		inboxTasksList = sqlSession.selectList("mapper.task.selectAllInboxTaskList");
		return inboxTasksList;
	}

	@Override
	public int insertInboxTask(TaskVO task) throws DataAccessException {
		int task_NO = selectNewTask_NO();
		task.setId(task_NO);
		sqlSession.insert("mapper.task.insertInboxTask" ,task);
		return task_NO;
	}

	private int selectNewTask_NO() throws DataAccessException{
		return sqlSession.selectOne("mapper.task.selectNewInboxTaskNO");
	}

}
