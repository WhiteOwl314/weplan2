package com.whiteowl.weplan.goal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface GoalDAO {

	public List goalList(String member_id) throws DataAccessException;

}
