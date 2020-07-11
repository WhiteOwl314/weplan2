package com.whiteowl.weplan.yearlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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
	

}
