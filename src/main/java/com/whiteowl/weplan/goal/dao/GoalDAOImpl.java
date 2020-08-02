package com.whiteowl.weplan.goal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.absolutevalue.vo.AbsoluteValueVO;
import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

@Repository("goalDAO")
public class GoalDAOImpl implements GoalDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List goalList(
			String member_id
	) throws DataAccessException {
		List<GoalVO> goalList = 
				sqlSession.selectList(
						"mapper.goal.goalList",
						member_id
				);
		return goalList;
	}

	@Override
	public void addGoalNullDate(
			GoalVO goalVO
	) throws DataAccessException {
		int goal_NO = selectNewGoal_NO();
		goalVO.setId(goal_NO);
		sqlSession.insert(
				"mapper.goal.addGoalNullDate" ,
				goalVO
		);
	}


	@Override
	public void addGoal(
			GoalVO goalVO
	) throws DataAccessException {
		int goal_NO = selectNewGoal_NO();
		goalVO.setId(goal_NO);
		sqlSession.insert(
				"mapper.goal.addGoal" ,
				goalVO
		);
	
	}

	private int selectNewGoal_NO() 
	throws DataAccessException{
		return sqlSession.selectOne(
				"mapper.goal.selectNewGoal_NO"
		);
	}

	@Override
	public JSONObject popUpGoalView(
			int goal_id
	) throws DataAccessException {
		JSONObject data = new JSONObject();
		
		GoalVO goalVO = sqlSession.selectOne(
				"mapper.goal.popUpGoalView", 
				goal_id
		);
		int id = goalVO.getId();
		String title = goalVO.getTitle();
		String content = goalVO.getContent();
		int importance = goalVO.getImportance();
		String limitDate = goalVO.getLimitDate();
		String startDate = goalVO.getStartDate();

		data.put("id", id);
		data.put("title", title);
		data.put("content", content);
		data.put("importance", importance);
		data.put("limitDate", limitDate);
		data.put("startDate", startDate);
		
		return data;
	}

	@Override
	public void updateGoal(
			GoalVO goalVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.goal.updateGoal",
				goalVO
		);
	}

	@Override
	public void updateGoalNullDate(
			GoalVO goalVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.goal.updateGoalNullDate",
				goalVO
		);
		
	}

	@Override
	public void deleteGoal(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.goal.deleteGoal",
				map
		);
		
	}

	@Override
	public JSONArray yearlyPlanList(
			Map<String, Object> map
	) throws DataAccessException {
		
		JSONArray ja = new JSONArray();
		
		
		List<YearlyPlanVO> yearlyPlanList = sqlSession.selectList(
			"mapper.goal.yearlyPlanList", 
			map
		);
		
		for (YearlyPlanVO yearlyPlanVO : yearlyPlanList) {
			JSONObject data = new JSONObject();
			
			int id = yearlyPlanVO.getId();
			String title = yearlyPlanVO.getTitle();
			String content = yearlyPlanVO.getContent();
			String isCompleted = yearlyPlanVO.getIsCompleted();
			int importance = yearlyPlanVO.getImportance();
			String startDate = yearlyPlanVO.getStartDate();
			String limitDate = yearlyPlanVO.getLimitDate();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("startDate", startDate);
			data.put("limitDate", limitDate);
			
			ja.add(data);
		}
		return ja;
	}

	@Override
	public void completeGoal(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.goal.completeGoal",
				map
		);
	}

}
