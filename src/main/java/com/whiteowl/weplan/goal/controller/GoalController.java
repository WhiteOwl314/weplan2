package com.whiteowl.weplan.goal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface GoalController {

	public ModelAndView goalList(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity addGoal(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String popUpGoalView(int goal_id) throws Exception;

	public ResponseEntity updateGoal(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity deleteGoal(int goal_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String yearlyPlanList(int goal_id, HttpServletRequest request) throws Exception;

	public ResponseEntity completeGoal(int goal_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
