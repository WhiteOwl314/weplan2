package com.whiteowl.weplan.goal.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.whiteowl.weplan.goal.vo.GoalVO;

public interface GoalService {

	public List goalList(String member_id) throws Exception;

	public void addGoalNullDate(GoalVO goalVO) throws Exception;

	public void addGoal(GoalVO goalVO) throws Exception;

	public JSONObject popUpGoalView(int goal_id) throws Exception;

	public void updateGoal(GoalVO goalVO) throws Exception;

	public void updateGoalNullDate(GoalVO goalVO) throws Exception;

	public void deleteGoal(Map<String, Object> map) throws Exception;

}
