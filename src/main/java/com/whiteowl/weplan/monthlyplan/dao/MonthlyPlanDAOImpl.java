package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

@Repository("monthlyPlanDAO")
public class MonthlyPlanDAOImpl implements MonthlyPlanDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addMonthlyPlan(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		
		int id = sqlSession.selectOne(
				"mapper.monthlyPlan.selectNewMonthlyPlan_NO");
		monthlyPlanVO.setId(id);
		
		sqlSession.insert("mapper.monthlyPlan.addMonthlyPlan", monthlyPlanVO);
	}

	@Override
	public void completeMonthlyPlan(
			Map<String, Object> map
	) throws DataAccessException {
		
		sqlSession.update(
				"mapper.monthlyPlan.completeMonthlyPlan", 
				map
		);
	}

	@Override
	public void notCompleteMonthlyPlan(
			Map<String, Object> map
	) throws DataAccessException {
		sqlSession.update(
				"mapper.monthlyPlan.notCompleteMonthlyPlan", 
				map
		);
	}

	@Override
	public JSONObject getMonthlyPlan(
			Map<String, Object> map
	) throws DataAccessException {
		
		JSONObject data = new JSONObject();
		MonthlyPlanVO monthlyPlanVO = sqlSession.selectOne(
				"mapper.monthlyPlan.getMonthlyPlan", 
				map
		);
		
		int id = monthlyPlanVO.getId();
		String title = monthlyPlanVO.getTitle();
		String content = monthlyPlanVO.getContent();
		int importance = monthlyPlanVO.getImportance();
		String month = monthlyPlanVO.getMonth();
		String isCompleted = monthlyPlanVO.getIsCompleted(); 
		int yearlyPlan_id = monthlyPlanVO.getYearlyPlan_id();
		
		data.put("id", id);
		data.put("title", title);
		data.put("content", content);
		data.put("importance", importance);
		data.put("month", month);
		data.put("isCompleted", isCompleted);
		data.put("yearlyPlan_id", yearlyPlan_id);
		
		return data;
	}

	@Override
	public void updateMonthlyPlan(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.monthlyPlan.updateMonthlyPlan", 
				monthlyPlanVO
		);
	}

	@Override
	public List<Map<String, String>> monthlyPlanListByMonth(
			Map<String, Object> tempMap
	) throws DataAccessException {

		return sqlSession.selectList(
				"mapper.monthlyPlan.monthlyPlanListByMonth", 
				tempMap
		);
	}

	@Override
	public JSONArray getMonthlyPlanListByYear(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		
		List<MonthlyPlanVO> monthlyPlanList = sqlSession.selectList(
			"mapper.monthlyPlan.getMonthlyPlanListByYear", 
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
			int yearlyPlan_id = monthlyPlanVO.getYearlyPlan_id();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("month", month);
			data.put("yearly_plan_id", yearlyPlan_id);
			
			ja.add(data);
		}
		return ja;
	}

	@Override
	public void addMonthlyPlanNullYearlyId(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		int id = sqlSession.selectOne(
				"mapper.monthlyPlan.selectNewMonthlyPlan_NO");
		monthlyPlanVO.setId(id);
		
		sqlSession.insert("mapper.monthlyPlan.addMonthlyPlanNullYearlyId", monthlyPlanVO);
	}

	@Override
	public void moveMonth(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.monthlyPlan.moveMonth", 
				monthlyPlanVO
		);
	}

	@Override
	public JSONArray getMonthlyPlanListByMonth(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		
		List<MonthlyPlanVO> monthlyPlanList = sqlSession.selectList(
			"mapper.monthlyPlan.getMonthlyPlanListByMonth", 
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
			int yearlyPlan_id = monthlyPlanVO.getYearlyPlan_id();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("month", month);
			data.put("yearly_plan_id", yearlyPlan_id);
			
			ja.add(data);
		}
		return ja;
	}

	@Override
	public void updateMonthlyPlanWithYearlyPlanId(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		sqlSession.update(
				"mapper.monthlyPlan.updateMonthlyPlanWithYearlyPlanId", 
				monthlyPlanVO
		);
		
	}



}
