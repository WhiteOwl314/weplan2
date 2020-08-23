package com.whiteowl.weplan.goal.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.whiteowl.weplan.goal.vo.GoalVO;

public interface GoalService {

	public List goalList(String member_id) throws Exception;

	public void addGoal(GoalVO goalVO) throws Exception;

	public JSONObject popUpGoalView(Map<String, Object> map) throws Exception;

	public void updateGoal(GoalVO goalVO) throws Exception;

	public void updateGoalNullDate(GoalVO goalVO) throws Exception;

	public void deleteGoal(Map<String, Object> map) throws Exception;

	public JSONArray yearlyPlanList(Map<String, Object> map) throws Exception;

	public void completeGoal(Map<String, Object> map) throws Exception;

	public void addGoalNullAbsoluteValue(GoalVO goalVO) throws Exception;

	public void updateGoalWithAbsoluteValue(GoalVO goalVO) throws Exception;

	public JSONArray getGoalAllList(Map<String, Object> map) throws Exception;

}
