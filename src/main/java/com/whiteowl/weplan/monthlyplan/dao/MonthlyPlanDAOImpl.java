package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("monthlyPlanDAO")
public class MonthlyPlanDAOImpl implements MonthlyPlanDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List monthlyPlanList(
			Map<String, Object> map
	) throws DataAccessException {
		return sqlSession.selectList(
				"mapper.monthlyPlan.monthlyPlanList",
				map
		);
	}

}
