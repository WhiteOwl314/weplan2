package com.whiteowl.weplan.goal.dao;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.goal.vo.GoalVO;

public interface GoalDAO {

	public List goalList(String member_id) throws DataAccessException;

	public void addGoalNullDate(GoalVO goalVO) throws DataAccessException;

	public void addGoal(GoalVO goalVO) throws DataAccessException;

	public JSONObject popUpGoalView(int goal_id) throws DataAccessException;

	public void updateGoal(GoalVO goalVO) throws DataAccessException;

	public void updateGoalNullDate(GoalVO goalVO) throws DataAccessException;

}
