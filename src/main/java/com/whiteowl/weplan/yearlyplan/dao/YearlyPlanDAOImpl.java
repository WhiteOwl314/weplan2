package com.whiteowl.weplan.yearlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;
import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

@Repository("yearlyPlanDAO")
public class YearlyPlanDAOImpl implements YearlyPlanDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List yearlyPlanList(
			Map<String, Object> map
	) throws DataAccessException {

		return sqlSession.selectList(
				"mapper.yearlyPlan.yearlyPlanList",
				map
		);
	}

	@Override
	public void addYearlyPlan(
			YearlyPlanVO yearlyPlanVO
	) throws DataAccessException {
		int yearlyPlan_NO = selectNewYearlyPlan_NO();
		yearlyPlanVO.setId(yearlyPlan_NO);
		
		sqlSession.insert(
				"mapper.yearlyPlan.addYearlyPlan",
				yearlyPlanVO
		);
	}

	private int selectNewYearlyPlan_NO() 
	throws DataAccessException{
		
		return sqlSession.selectOne(
					"mapper.yearlyPlan.selectNewYearlyPlan_NO"
				);
	}

	@Override
	public JSONObject popUpYearlyPlanView(
			Map<String, Object> map
	) throws DataAccessException {
		JSONObject data = new JSONObject();
		
		YearlyPlanVO yearlyPlanVO = sqlSession.selectOne(
				"mapper.yearlyPlan.popUpYearlyPlanView", 
				map
		);
		int id = yearlyPlanVO.getId();
		String title = yearlyPlanVO.getTitle();
		String content = yearlyPlanVO.getContent();
		int importance = yearlyPlanVO.getImportance();
		String startDate = yearlyPlanVO.getStartDate();
		String limitDate = yearlyPlanVO.getLimitDate();
		int goal_id = yearlyPlanVO.getGoal_id();

		data.put("id", id);
		data.put("title", title);
		data.put("content", content);
		data.put("importance", importance);
		data.put("startDate", startDate);
		data.put("limitDate", limitDate);
		data.put("goal_id", goal_id);
		
		return data;
	}

	@Override
	public void updateYearlyPlan(
			YearlyPlanVO yearlyPlanVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.yearlyPlan.updateYearlyPlan",
				yearlyPlanVO
		);
	}

	@Override
	public void deleteYearlyPlan(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.yearlyPlan.deleteYearlyPlan",
				map
		);
		
	}

	@Override
	public void completeYearlyPlan(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.yearlyPlan.completeYearlyPlan",
				map
		);
	}

	@Override
	public JSONArray monthlyPlanList(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		List<MonthlyPlanVO> monthlyPlanList = sqlSession.selectList(
				"mapper.yearlyPlan.monthlyPlanList",
				map
		);
		
		for (MonthlyPlanVO monthlyPlanVO : monthlyPlanList) {
			JSONObject data = new JSONObject();
			
			int id = monthlyPlanVO.getId();
			String title = monthlyPlanVO.getTitle();
			String content = monthlyPlanVO.getContent();
			String isCompleted = monthlyPlanVO.getIsCompleted();
			int importance = monthlyPlanVO.getImportance();
			String month = monthlyPlanVO.getMonth();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("month", month);
			
			ja.add(data);
		}
		return ja;
	}

	@Override
	public JSONArray getMonthList(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		List<String> monthList = sqlSession.selectList(
				"mapper.yearlyPlan.getMonthList",
				map
		);
		
		for (String month : monthList) {
			ja.add(month);
		}

		return ja;
	}

	@Override
	public void addYearlyPlanNullGoalId(
			YearlyPlanVO yearlyPlanVO
	) throws DataAccessException {
		int yearlyPlan_NO = selectNewYearlyPlan_NO();
		yearlyPlanVO.setId(yearlyPlan_NO);
		
		sqlSession.insert(
				"mapper.yearlyPlan.addYearlyPlanNullGoalId",
				yearlyPlanVO
		);
	}

	@Override
	public void updateYearlyPlanWithGoalId(
			YearlyPlanVO yearlyPlanVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.yearlyPlan.updateYearlyPlanWithGoalId",
				yearlyPlanVO
		);
	}

	@Override
	public JSONArray getYearlyPlanAllList(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		
		List<YearlyPlanVO> yearlyPlanList = sqlSession.selectList(
			"mapper.yearlyPlan.getYearlyPlanAllList", 
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
			int goal_id = yearlyPlanVO.getGoal_id();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("startDate", startDate);
			data.put("limitDate", limitDate);
			data.put("goal_id", goal_id);
			
			ja.add(data);
		}
		return ja;
	}
	

}
